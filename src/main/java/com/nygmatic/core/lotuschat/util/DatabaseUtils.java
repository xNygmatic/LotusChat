package com.nygmatic.core.lotuschat.util;

import com.nygmatic.core.lotuschat.FileManager;
import org.bukkit.configuration.file.FileConfiguration;

public class DatabaseUtils {

    private static FileConfiguration playerData = FileManager.getPlayerData();

    public static String get(String path) {
        return playerData.getString(path);
    }

    public static void set(String path, Object object) {
        playerData.set(path, object);
        FileManager.savePlayerData();
    }
}
