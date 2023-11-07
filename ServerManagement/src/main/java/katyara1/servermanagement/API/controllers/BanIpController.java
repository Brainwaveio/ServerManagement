package katyara1.servermanagement.API.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import katyara1.servermanagement.API.DTOs.BanDto;
import katyara1.servermanagement.API.utils.HttpHelper;
import katyara1.servermanagement.ServerManagement;
import java.util.Map;
import java.io.IOException;

public class BanIpController extends HttpHelper implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
        BanDto dto = new BanDto(true);
        String ip = params.get("ip");

        for (String ipBan:
        if (!ip.equals("127.0.0.1") && !ip.equals("localhost") && !ip.equals(ServerManagement.server.getIp())) {
            ServerManagement.server.banIP(ip);
        }

        for (String ipBan : ServerManagement.server.getIPBans()) {
            if (!ipBan.equals(params.get("ip"))) {
                dto = new BanDto(false);
                return;
            }
        }

        write(exchange, _gson.toJson(dto));
    }
}