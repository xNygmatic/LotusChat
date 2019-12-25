package com.nygmatic.core.lotuschat.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (Chatter.isNewPlayer(player)) {
            String firstName, lastName;

            //Conversation for players first and last name starts here




//            Chatter.setupPlayer(player, firstName, lastName);
        }
    }
}
