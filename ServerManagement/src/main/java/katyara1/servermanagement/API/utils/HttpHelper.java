package katyara1.servermanagement.API.utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HttpHelper {
    public static void write(HttpExchange exchange, String jsonResponse) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(200, jsonResponse.getBytes(StandardCharsets.UTF_8).length);

        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(jsonResponse.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }
}
