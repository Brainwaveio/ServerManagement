package katyara1.servermanagement.services;

import katyara1.servermanagement.API.DTOs.ServerInfoDto;
import katyara1.servermanagement.ServerManagement;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.Collection;

public class ServerService {
    private final Server _server;

    public ServerService() {
        _server = ServerManagement.getServerPlugin();
    }

    public ServerInfoDto getInfoServer() {
        String ip = _server.getIp();
        
        if (ip.isEmpty()) {
            ip = "127.0.0.1";
        }

        return new ServerInfoDto(
            _server.getName(),
            _server.getVersion(),
            _server.getBukkitVersion(),
            ip,
            _server.getPort(),
            _server.getMaxPlayers(),
            _server.getIPBans(),
            _server.getAllowEnd(),
            _server.getAllowNether(),
            _server.getAllowFlight()
        );
    }

    public Collection<? extends Player> getOnlinePlayers() {
        return _server.getOnlinePlayers();
    }
}
