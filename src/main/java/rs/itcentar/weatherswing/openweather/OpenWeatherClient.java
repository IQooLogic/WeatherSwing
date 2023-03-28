package rs.itcentar.weatherswing.openweather;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *
 * @author Miloš Stojković <milos@ast.co.rs>
 */
public class OpenWeatherClient {

    private static final String APIKEY = "17e5838ec41f0fb4012d6026dde579ea";
    private static final String URL = "https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s&units=%s";

    public enum Unit {
        STANDARD("standard"), IMPERIAL("imperial"), METRIC("metric");

        private final String name;

        private Unit(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public String weather(float lat, float lon, Unit unit) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = String.format(URL, lat, lon, APIKEY, unit.getName());
        Request request = new Request.Builder().url(HttpUrl.get(url)).build();

        Call call = client.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            final ResponseBody body = response.body();
            if (body != null) {
                return body.string();
            }
        }

        return null;
    }
}
