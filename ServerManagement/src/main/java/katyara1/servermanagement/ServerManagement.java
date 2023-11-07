package katyara1.servermanagement;

import katyara1.servermanagement.API.APIServer;
import katyara1.servermanagement.commands.ServerCommand;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;

public final class ServerManagement extends JavaPlugin {
    public static ServerManagement instance;
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        instance = this;
        config = instance.getConfig();

        saveDefaultConfig();

        // add Command
        new ServerCommand();

        // start API Server in new Thread
        loadAPI();

        instance.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "ServerManagement started");
    }

    @Override
    public void onDisable() {
        APIServer.stopServer();
    }

    public static ServerManagement getInstance() {
        return instance;
    }

    private void loadAPI() {
        try {
            APIServer.startServer(config.getInt("apiserver.port"));
            instance.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "API Server started");
        } catch (IOException e) {
            instance.getServer().getConsoleSender().sendMessage(ChatColor.RED + "API Server error");
            throw new RuntimeException(e);
        }
    }
}