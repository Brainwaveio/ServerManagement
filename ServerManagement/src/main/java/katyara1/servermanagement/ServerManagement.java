package katyara1.servermanagement;

import katyara1.servermanagement.API.APIServer;
import katyara1.servermanagement.commands.ServerCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;

public final class ServerManagement extends JavaPlugin {
    public static ServerManagement instance;
    public static Server server;
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        instance = this;
        server = instance.getServer();
        config = instance.getConfig();

        saveDefaultConfig();

        // add Command
        new ServerCommand();

        // start API Server in new Thread
        loadAPI();

        server.getConsoleSender().sendMessage(ChatColor.GREEN + "ServerManagement started");
    }

    @Override
    public void onDisable() {
        APIServer.stopServer();
    }

    private void loadAPI() {
        Bukkit.getScheduler().runTaskAsynchronously(instance, () -> {
            try {
                APIServer.startServer(config.getInt("apiserver.port"));
                server.getConsoleSender().sendMessage(ChatColor.GOLD + "API Server started");
            } catch (IOException e) {
                server.getConsoleSender().sendMessage(ChatColor.RED + "API Server error");
                throw new RuntimeException(e);
            }
        });
    }
}