package katyara1.servermanagement.API.utils;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public abstract class HttpHelper {
    protected Gson _gson = new Gson();

    protected void write(HttpExchange exchange, String jsonResponse) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(200, jsonResponse.getBytes(StandardCharsets.UTF_8).length);

        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(jsonResponse.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }

    protected Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        if (query != null) {
            for (String param : query.split("&")) {
                String[] pair = param.split("=");
                if (pair.length > 1) {
                    result.put(pair[0], pair[1]);
                } else {
                    result.put(pair[0], "");
                }
            }
        }
        return result;
    }
}
