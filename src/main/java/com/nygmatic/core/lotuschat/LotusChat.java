package com.nygmatic.core.lotuschat;

import org.bukkit.plugin.java.JavaPlugin;

public final class LotusChat extends JavaPlugin {

    private static LotusChat instance;

    @Override
    public void onEnable() {
        instance = this;
        FileManager.setup();

    }

    @Override
    public void onDisable() {
        FileManager.savePlayerData();
    }

    static LotusChat getInstance() {
        return instance;
    }
}
