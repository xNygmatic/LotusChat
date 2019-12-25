package com.nygmatic.core.lotuschat.chat;

import com.nygmatic.core.lotuschat.LotusChat;
import org.bukkit.ChatColor;
import org.bukkit.conversations.*;
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


            Conversation conversation = LotusChat.getConversationFactory()
                    .withModality(true)
                    .withLocalEcho(false)
                    .withPrefix(context -> ChatColor.GOLD + "Carter The Hoe " + ChatColor.DARK_GRAY + "> " + ChatColor.RESET)
                    .withTimeout(120)
                    .withFirstPrompt(new FirstNamePrompt()) //ill add prompt in a second
                    .buildConversation(player);

            player.beginConversation(conversation);
        }
    }

    private static class FirstNamePrompt extends ValidatingPrompt {

        @Override
        protected boolean isInputValid(ConversationContext context, String input) {
            return false;
        }

        @Override
        protected Prompt acceptValidatedInput(ConversationContext context, String input) {
            return null;
        }

        @Override
        public String getPromptText(ConversationContext context) {
            return null;
        }
    }


}
