package com.redafex.xpc.Utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    private static File file;
    private static FileConfiguration customFile;

    public static void setup() {
        Config.file = new File(Bukkit.getServer().getPluginManager().getPlugin("XPControl").getDataFolder(), "Userdata.yml");
        if (!Config.file.exists()) {
            try {
                Config.file.createNewFile();
            }
            catch (IOException e) {
                System.out.println("ERROR: A FATAL ERROR HAD HAPPENED WHILE TRYING TO CREATE A NEW FILE");
            }
        }
        Config.customFile = (FileConfiguration)YamlConfiguration.loadConfiguration(Config.file);
    }

    public static FileConfiguration get() {
        return Config.customFile;
    }

    public static void save() {
        try {
            Config.customFile.save(Config.file);
        }
        catch (IOException e) {
            System.out.println("ERROR: A FATAL ERROR HAD HAPPENED WHILE TRYING TO SAVE A FILE");
        }
    }

    public static void reload() {
        Config.customFile = (FileConfiguration) YamlConfiguration.loadConfiguration(Config.file);
    }
}
