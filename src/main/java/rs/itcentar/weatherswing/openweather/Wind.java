package rs.itcentar.weatherswing.openweather;

/**
 *
 * @author Miloš Stojković <milos@ast.co.rs>
 */
public class Wind {

    private float speed;
    private int deg;
    private float gust;

    public Wind() {
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public float getGust() {
        return gust;
    }

    public void setGust(float gust) {
        this.gust = gust;
    }
}
