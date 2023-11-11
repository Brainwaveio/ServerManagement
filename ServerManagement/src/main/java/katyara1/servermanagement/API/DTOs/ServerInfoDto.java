package katyara1.servermanagement.API.DTOs;

import java.util.Set;

public class ServerInfoDto {
    public String name;
    public String coreVersion;
    public String bukkitVersion;
    public String ip;
    public int port;
    public int maxPlayers;
    public Set<String> ipBans;
    public boolean allowEnd;
    public boolean allowNether;
    public boolean allowFlight;

    public ServerInfoDto(
            String name,
            String coreVersion,
            String bukkitVersion,
            String ip,
            int port,
            int maxPlayers,
            Set<String> ipBans,
            boolean allowEnd,
            boolean allowNether,
            boolean allowFlight) {
        this.name = name;
        this.coreVersion = coreVersion;
        this.bukkitVersion = bukkitVersion;
        this.ip = ip;
        this.port = port;
        this.maxPlayers = maxPlayers;
        this.ipBans = ipBans;
        this.allowEnd = allowEnd;
        this.allowNether = allowNether;
        this.allowFlight = allowFlight;
    }
}
