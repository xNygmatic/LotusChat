package com.nygmatic.core.lotuschat.chat;

import com.nygmatic.core.lotuschat.util.PlayerDataUtil;
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
        this.firstName = PlayerDataUtil.get(path + ".first-name");
        this.lastName = PlayerDataUtil.get(path + ".last-name");
        this.chatColor = ChatColor.valueOf(PlayerDataUtil.get(path + ".chat-color"));
        this.nameColor = ChatColor.valueOf(PlayerDataUtil.get(path + ".name-color"));
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

    public Player getPlayer() {
        return player;
    }

    public void setFirstName(String name) {
        PlayerDataUtil.set(path + ".first-name", name);
        firstName = name;
    }

    public void setLastName(String name) {
        PlayerDataUtil.set(path + ".last-name", name);
        lastName = name;
    }

    public void setChatColor(ChatColor color) {
        PlayerDataUtil.set(path + ".chat-color", color.name());
        chatColor = color;
    }

    public void setNameColor(ChatColor color) {
        PlayerDataUtil.set(path + ".name-color", color.name());
        nameColor = color;
    }
}
