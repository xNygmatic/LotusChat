package com.nygmatic.core.lotuschat.chat;

import com.nygmatic.core.lotuschat.util.DatabaseUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Chatter {

    private String firstName, lastName;
    private ChatColor chatcolor, nameColor;
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
        chatcolor = ChatColor.getByChar(DatabaseUtils.get(path + ".chat-color"));
        nameColor = ChatColor.getByChar(DatabaseUtils.get(path + ".name-color"));
    }

    private boolean isNewPlayer() {
        return DatabaseUtils.get(path + uuid) != null;
    }

    private void setupPlayer() {
        DatabaseUtils.set(path + ".first-name", "John");
        DatabaseUtils.set(path + ".last-name", "Smith");
        DatabaseUtils.set(path + ".chat-color", ChatColor.WHITE.toString());
        DatabaseUtils.set(path + ".name-color", ChatColor.GRAY.toString());
    }

}
