package rs.itcentar.weatherswing.openweather;

/**
 *
 * @author Miloš Stojković <milos@ast.co.rs>
 */
public class Coord {

    private float lat;
    private float lon;

    public Coord() {
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
