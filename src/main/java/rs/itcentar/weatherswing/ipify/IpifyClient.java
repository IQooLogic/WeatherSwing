package rs.itcentar.weatherswing.ipify;

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
public class IpifyClient {

    private static final String URL = "https://api.ipify.org";

    public String ip() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(HttpUrl.get(URL)).build();

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
