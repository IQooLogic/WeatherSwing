package rs.itcentar.weatherswing;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import rs.itcentar.weatherswing.ipapi.GeoIPData;
import rs.itcentar.weatherswing.ipapi.IPApiClient;
import rs.itcentar.weatherswing.ipify.IpifyClient;
import rs.itcentar.weatherswing.openweather.OpenWeatherClient;
import rs.itcentar.weatherswing.openweather.WeatherResponse;

/**
 *
 * @author Miloš Stojković <milos@ast.co.rs>
 */
public class WeatherMainFrame extends javax.swing.JFrame implements Thread.UncaughtExceptionHandler {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final IpifyClient ipifyClient = new IpifyClient();
    private final IPApiClient iPApiClient = new IPApiClient();
    private final OpenWeatherClient openWeatherClient = new OpenWeatherClient();
    private final Timer timer = new Timer("MyTimer");

    public WeatherMainFrame() throws IOException {
        initComponents();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.print("WORKING ...");
                    String ip = ipifyClient.ip();

                    String geoip = iPApiClient.geoip(ip);

                    GeoIPData geoObj = mapper.readValue(geoip, GeoIPData.class);

                    String weather = openWeatherClient.weather(geoObj.getLat(), geoObj.getLon(), OpenWeatherClient.Unit.METRIC);

                    WeatherResponse weatherObj = mapper.readValue(weather, WeatherResponse.class);

                    WeatherData weatherData = new WeatherData(weatherObj.getMain().getTemp(),
                            weatherObj.getMain().getTemp_min(),
                            weatherObj.getMain().getTemp_max());

                    // done
                    SwingUtilities.invokeLater(() -> {
                        System.out.print("... DONE");
                        System.out.println();
                        lCurrentTemp.setText(weatherData.getTemp() + "");
                        lMinTemp.setText(weatherData.getMinTemp() + "");
                        lMaxTemp.setText(weatherData.getMaxTemp() + "");
                    });
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }, 0, 5 * 60 * 1000);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lCurrentTemp = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lMinTemp = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lMaxTemp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Current Temp:");

        lCurrentTemp.setText("0");

        jLabel2.setText("Min Temp:");

        lMinTemp.setText("0");

        jLabel3.setText("Max Temp:");

        lMaxTemp.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lCurrentTemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lMinTemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lMaxTemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(73, 73, 73))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lCurrentTemp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lMinTemp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lMaxTemp))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WeatherMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WeatherMainFrame window = new WeatherMainFrame();
                    window.setVisible(true);
                    Thread.setDefaultUncaughtExceptionHandler(window);
                } catch (IOException ex) {
                    Logger.getLogger(WeatherMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lCurrentTemp;
    private javax.swing.JLabel lMaxTemp;
    private javax.swing.JLabel lMinTemp;
    // End of variables declaration//GEN-END:variables

    @Override
    public void uncaughtException(Thread t, Throwable ex) {
        System.err.println(ex.getMessage());
        ex.printStackTrace();
    }
}
