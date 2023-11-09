package katyara1.servermanagement.API.controllers;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import katyara1.servermanagement.API.DTOs.ErrorDto;
import katyara1.servermanagement.API.utils.HttpHelper;
import katyara1.servermanagement.ServerManagement;
import katyara1.servermanagement.services.ServerService;

public class InformationServerController extends HttpHelper implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
        String token = params.get("token");

        if (!Objects.equals(token, ServerManagement.getConfigPlugin().getString("apiserver.token"))) {
            ErrorDto errorDto = new ErrorDto("token is invalid");

            write(exchange, _gson.toJson(errorDto));
        }

        ServerService serverService = new ServerService();

        write(exchange, _gson.toJson(serverService.getInfoServer()));
    }
}
