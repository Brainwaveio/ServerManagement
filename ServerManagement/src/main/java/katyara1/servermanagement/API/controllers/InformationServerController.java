package katyara1.servermanagement.API.controllers;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import com.google.gson.Gson;
import katyara1.servermanagement.API.utils.HttpHelper;
import katyara1.servermanagement.services.ServerService;

public class InformationServerController implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        ServerService serverService = new ServerService();

        String jsonResponse = gson.toJson(serverService.getInfoServer());

        HttpHelper.write(exchange, jsonResponse);
    }
}
