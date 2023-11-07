package katyara1.servermanagement.API.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import katyara1.servermanagement.API.DTOs.BanDto;
import katyara1.servermanagement.API.utils.HttpHelper;
import katyara1.servermanagement.ServerManagement;

import java.io.IOException;
import java.util.Map;

public class UnBanIpController extends HttpHelper implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
        BanDto dto = new BanDto(true);

        ServerManagement.server.unbanIP(params.get("ip"));

        for (String ip : ServerManagement.server.getIPBans()) {
            if (ip.equals(params.get("ip"))) {
                dto = new BanDto(false);
                return;
            }
        }

        write(exchange, _gson.toJson(dto));
    }
}