package rs.itcentar.weatherswing.ipapi;

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
public class IPApiClient {

    private static final String URL = "http://ip-api.com/json/";

    public String geoip(String ip) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(HttpUrl.get(URL + ip)).build();

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
