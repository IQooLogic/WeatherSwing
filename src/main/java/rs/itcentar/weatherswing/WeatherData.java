package rs.itcentar.weatherswing;

/**
 *
 * @author Miloš Stojković <milos@ast.co.rs>
 */
public class WeatherData {

    private float temp;
    private float minTemp;
    private float maxTemp;

    public WeatherData(float temp, float minTemp, float maxTemp) {
        this.temp = temp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public float getTemp() {
        return temp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }
}
