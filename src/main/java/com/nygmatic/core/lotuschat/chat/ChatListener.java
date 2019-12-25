package com.nygmatic.core.lotuschat.chat;

import com.nygmatic.core.lotuschat.LotusChat;
import org.bukkit.ChatColor;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collections;

public class ChatListener implements Listener, ConversationAbandonedListener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (LotusChat.getChatManager().isNewPlayer(player)) {

            Conversation conversation = LotusChat.getConversationFactory()
                    .withModality(true)
                    .withLocalEcho(false)
                    .withPrefix(context -> ChatColor.GOLD + "Carter The Hoe " + ChatColor.DARK_GRAY + "> " + ChatColor.RESET)
                    .withTimeout(120)
                    .withInitialSessionData(Collections.singletonMap("action", "first_name"))
                    .withFirstPrompt(new NamePrompt())
                    .addConversationAbandonedListener(this)
                    .buildConversation(player);

            player.beginConversation(conversation);

        } else LotusChat.getChatManager().addChatter(new Chatter(player));
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        LotusChat.getChatManager().removeChatter(event.getPlayer());
    }

    @Override
    public void conversationAbandoned(ConversationAbandonedEvent event) {

        Player player = (Player) event.getContext().getForWhom();

        if (!event.gracefulExit()) {
            player.kickPlayer("Answer the question, you idiot. - Carter The Hoe");
            return;
        }

        String first = (String) event.getContext().getSessionData("first_name");
        String last = (String) event.getContext().getSessionData("last_name");
        LotusChat.getChatManager().setupPlayer(player, first, last);

    }

    private static class NamePrompt extends ValidatingPrompt {

        @Override
        protected boolean isInputValid(ConversationContext context, String input) {
            // This is regex for only alphabetic characters.
            return input.matches("[a-zA-Z]+");
        }

        @Override
        protected Prompt acceptValidatedInput(ConversationContext context, String input) {

            String action = (String) context.getSessionData("action");

            if (action.equals("first_name")) {

                context.setSessionData("action", "last_name");
                context.setSessionData("first_name", input);

                return new NamePrompt();

            } else context.setSessionData("last_name", input);

            // I'm assuming you only want first and last name
            return Prompt.END_OF_CONVERSATION;
        }

        @Override
        public String getPromptText(ConversationContext context) {

            String action = (String) context.getSessionData("action");

            return action.equals("first_name") ? "Whatcha want your lsr first name to be?" :
                context.getSessionData("first_name") + "? What even... Whatever. What about your last name?";

        }
    }


}
