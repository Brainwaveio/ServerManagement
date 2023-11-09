package katyara1.servermanagement;

import katyara1.servermanagement.API.APIServer;
//import katyara1.servermanagement.API.utils.TokenHelper;
import katyara1.servermanagement.commands.ServerCommand;
import katyara1.servermanagement.utils.Storage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;

public final class ServerManagement extends JavaPlugin {
    private static ServerManagement _instance;
    private Server _server;
    private FileConfiguration _config;
//    private Storage _storage;

    @Override
    public void onEnable() {
        _instance = this;
        _server = _instance.getServer();
        _config = _instance.getConfig();
//        _storage = new Storage("token.yml");

        saveDefaultConfig();

        // add Command
        new ServerCommand();

        // generate token
//        TokenHelper.createToken();

        // start API Server in new Thread
        loadAPI();

        _server.getConsoleSender().sendMessage(ChatColor.GREEN + "ServerManagement started");
    }

    @Override
    public void onDisable() {
        APIServer.stopServer();
    }

    private void loadAPI() {
        Bukkit.getScheduler().runTaskAsynchronously(_instance, () -> {
            try {
                APIServer.startServer(_config.getInt("apiserver.port"));
                _server.getConsoleSender().sendMessage(ChatColor.GOLD + "API Server started");
            } catch (IOException e) {
                _server.getConsoleSender().sendMessage(ChatColor.RED + "API Server error");
                throw new RuntimeException(e);
            }
        });
    }

    public static ServerManagement getInstancePlugin() {
        return _instance;
    }

    public static Server getServerPlugin() {
        return _instance._server;
    }

    public static FileConfiguration getConfigPlugin() {
        return _instance._config;
    }

//    public static Storage getStoragePlugin() {
//        return  _instance._storage;
//    }
}