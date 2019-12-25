package com.nygmatic.core.lotuschat;

import com.nygmatic.core.lotuschat.chat.Channel;
import com.nygmatic.core.lotuschat.chat.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public final class LotusChat extends JavaPlugin {

    // Configuration Serializable objects have to be registered *before* the plugin is loaded.
    static {
        ConfigurationSerialization.registerClass(Channel.class);
    }

    private static LotusChat instance;

    @Override
    public void onEnable() {
        instance = this;
        FileManager.setup();
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "LotusChat enabled!");
    }

    @Override
    public void onDisable() {
        FileManager.savePlayerData();
    }

    static LotusChat getInstance() {
        return instance;
    }
}
