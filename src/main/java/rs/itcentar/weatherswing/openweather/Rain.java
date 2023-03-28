package rs.itcentar.weatherswing.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Miloš Stojković <milos@ast.co.rs>
 */
public class Rain {

    @JsonProperty("1h")
    private String _1h;
    @JsonProperty("3h")
    private String _3h;

    public Rain() {
    }

    public String get1h() {
        return _1h;
    }

    public void set1h(String _1h) {
        this._1h = _1h;
    }

    public String get3h() {
        return _3h;
    }

    public void set3h(String _3h) {
        this._3h = _3h;
    }
}
