package katyara1.servermanagement;

import katyara1.servermanagement.API.APIServer;
import katyara1.servermanagement.commands.ServerCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;
import java.util.Objects;

public final class ServerManagement extends JavaPlugin {
    public static ServerManagement instance;
    public static FileConfiguration config;

    private Thread _apiServerThread;

    @Override
    public void onEnable() {
        instance = this;
        config = instance.getConfig();

        saveDefaultConfig();

        // add Command
        new ServerCommand();

        // start API Server in new Thread
        _apiServerThread = new Thread(() -> {
            try {
                APIServer.startServer(
                        Integer.parseInt(
                                Objects.requireNonNull(
                                        config.getString("apiserver.port")
                                )
                        )
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void onDisable() {}

    public static ServerManagement getInstance() {
        return instance;
    }

    private void LoadAPI() {

    }
}

