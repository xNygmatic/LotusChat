package com.nygmatic.core.lotuschat;

import com.nygmatic.core.lotuschat.chat.Channel;
import com.nygmatic.core.lotuschat.chat.ChatListener;
import com.nygmatic.core.lotuschat.chat.ChatManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.plugin.java.JavaPlugin;

public final class LotusChat extends JavaPlugin {

    private static LotusChat instance;
    private static ConversationFactory conversationFactory;
    private static ChatManager chatManager;

    // Configuration Serializable objects have to be registered *before* the plugin is loaded.
    static {
        ConfigurationSerialization.registerClass(Channel.class);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        conversationFactory = new ConversationFactory(this);
        chatManager = new ChatManager();
        FileManager.setup();
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "LotusChat enabled!");
    }

    @Override
    public void onDisable() {
        FileManager.savePlayerData();
        saveConfig();
    }

    public static LotusChat getInstance() {
        return instance;
    }

    public static ConversationFactory getConversationFactory() {
        return conversationFactory;
    }

    public static ChatManager getChatManager() {
        return chatManager;
    }

}
