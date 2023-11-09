package katyara1.servermanagement.utils;

import katyara1.servermanagement.ServerManagement;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Storage {
    private File _file;
    private FileConfiguration _config;
    private String _filename;

    public Storage(String name) {
        _filename = name;
        _file = new File(ServerManagement.getInstancePlugin().getDataFolder(), name);

        try {
            if (!_file.exists() && !_file.createNewFile()) {
               throw new IOException();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to create " + _filename);
        }

        _config = YamlConfiguration.loadConfiguration(_file);
    }

    public FileConfiguration getConfig() {
        return _config;
    }

    public void save() {
        try {
            _config.save(_file);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to save " + _filename);
        }
    }
}
