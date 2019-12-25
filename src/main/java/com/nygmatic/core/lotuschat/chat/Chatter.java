package com.nygmatic.core.lotuschat.chat;

import com.nygmatic.core.lotuschat.FileManager;
import com.nygmatic.core.lotuschat.util.DatabaseUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Chatter {

    private String firstName, lastName;
    private ChatColor chatColor, nameColor;
    private Player player;
    private UUID uuid;

    private String path;

    public Chatter(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.path = "players." + this.uuid;

        if (isNewPlayer()) {
            setupPlayer();
        }

        firstName = DatabaseUtils.get(path + ".first-name");
        lastName = DatabaseUtils.get(path + ".last-name");
        chatColor = ChatColor.valueOf(DatabaseUtils.get(path + ".chat-color"));
        nameColor = ChatColor.valueOf(DatabaseUtils.get(path + ".name-color"));
    }

    private boolean isNewPlayer() {
        return DatabaseUtils.get(path) == null;
    }

    private void setupPlayer() {
        DatabaseUtils.set(path + ".first-name", "John");
        DatabaseUtils.set(path + ".last-name", "Smith");
        DatabaseUtils.set(path + ".chat-color", ChatColor.WHITE.name());
        DatabaseUtils.set(path + ".name-color", ChatColor.GRAY.name());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public ChatColor getNameColor() {
        return nameColor;
    }

    public void setFirstName(String name) {
        DatabaseUtils.set(path + ".first-name", name);
        FileManager.savePlayerData();
        firstName = name;
    }

    public void setLastName(String name) {
        DatabaseUtils.set(path + ".last-name", name);
        FileManager.savePlayerData();
        lastName = name;
    }

    public void setChatColor(ChatColor color) {
        DatabaseUtils.set(path + ".chat-color", color.name());
        FileManager.savePlayerData();
        chatColor = color;
    }

    public void setNameColor(ChatColor color) {
        DatabaseUtils.set(path + ".name-color", color.name());
        FileManager.savePlayerData();
        nameColor = color;
    }
}
