package katyara1.servermanagement.API.controllers;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import katyara1.servermanagement.API.utils.HttpHelper;
import katyara1.servermanagement.services.ServerService;

public class InformationServerController extends HttpHelper implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        ServerService serverService = new ServerService();

        write(exchange, _gson.toJson(serverService.getInfoServer()));
    }
}
