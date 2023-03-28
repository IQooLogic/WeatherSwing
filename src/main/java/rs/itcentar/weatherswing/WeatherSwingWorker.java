package rs.itcentar.weatherswing;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import rs.itcentar.weatherswing.ipapi.GeoIPData;
import rs.itcentar.weatherswing.ipapi.IPApiClient;
import rs.itcentar.weatherswing.ipify.IpifyClient;
import rs.itcentar.weatherswing.openweather.OpenWeatherClient;
import rs.itcentar.weatherswing.openweather.WeatherResponse;

/**
 *
 * @author Miloš Stojković <milos@ast.co.rs>
 */
public class WeatherSwingWorker extends SwingWorker<WeatherData, Void> {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final IpifyClient ipifyClient = new IpifyClient();
    private final IPApiClient iPApiClient = new IPApiClient();
    private final OpenWeatherClient openWeatherClient = new OpenWeatherClient();

    private final JLabel lCurrentTemp;
    private final JLabel lMaxTemp;
    private final JLabel lMinTemp;

    public WeatherSwingWorker(JLabel lCurrentTemp, JLabel lMaxTemp, JLabel lMinTemp) {
        this.lCurrentTemp = lCurrentTemp;
        this.lMaxTemp = lMaxTemp;
        this.lMinTemp = lMinTemp;
    }

    @Override
    protected WeatherData doInBackground() throws Exception {
        System.out.print("WORKING ...");
        String ip = ipifyClient.ip();

        String geoip = iPApiClient.geoip(ip);

        GeoIPData geoObj = mapper.readValue(geoip, GeoIPData.class);

        String weather = openWeatherClient.weather(geoObj.getLat(), geoObj.getLon(), OpenWeatherClient.Unit.METRIC);

        WeatherResponse weatherObj = mapper.readValue(weather, WeatherResponse.class);

        return new WeatherData(weatherObj.getMain().getTemp(),
                weatherObj.getMain().getTemp_min(),
                weatherObj.getMain().getTemp_max());
    }

    @Override
    protected void done() {
        try {
            System.out.print("... DONE");
            WeatherData weatherData = get(10, TimeUnit.SECONDS);
            lCurrentTemp.setText(weatherData.getTemp() + "");
            lMinTemp.setText(weatherData.getMinTemp() + "");
            lMaxTemp.setText(weatherData.getMaxTemp() + "");
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
