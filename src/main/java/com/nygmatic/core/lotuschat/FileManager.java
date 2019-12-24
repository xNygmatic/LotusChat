package com.nygmatic.core.lotuschat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private static LotusChat instance = LotusChat.getInstance();

    private static FileConfiguration playerDataConfig;
    private static File playerDataFile;

    static void setup() {
        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdir();
        }

        playerDataFile = new File(instance.getDataFolder(), "player-data.yml");

        if(!playerDataFile.exists()) {
            try {
               playerDataFile.createNewFile();
            } catch (IOException error) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Could not create the player-data.yml file");
            }
        }
        playerDataConfig = YamlConfiguration.loadConfiguration(playerDataFile);
    }

    public static FileConfiguration getPlayerData() {
        return playerDataConfig;
    }

    public static void savePlayerData() {
        try {
            playerDataConfig.save(playerDataFile);
        } catch (IOException error) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Could not save the player-data.yml file");
        }
    }

    public static void reloadPlayerData() {
        playerDataConfig = YamlConfiguration.loadConfiguration(playerDataFile);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "player-data.yml has been reloaded");
    }
}
