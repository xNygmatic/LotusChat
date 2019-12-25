package com.nygmatic.core.lotuschat.chat;

import com.nygmatic.core.lotuschat.util.DatabaseUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class ChatManager {

  //TODO: Have your list of channels here

  // All those chatter instances go somewhere >:V
  private Set<Chatter> chatters = new HashSet<>();

  public void setupPlayer(Player player, String firstName, String lastName) {

    String path = "players." + player.getUniqueId();
    DatabaseUtils.set(path + ".first-name", firstName);
    DatabaseUtils.set(path + ".last-name", lastName);
    DatabaseUtils.set(path + ".chat-color", ChatColor.WHITE.name());
    DatabaseUtils.set(path + ".name-color", ChatColor.GRAY.name());

    addChatter(new Chatter(player));
  }

  public void addChatter(Chatter chatter) {
    chatters.add(chatter);
  }

  public void removeChatter(Player player) {
    chatters.removeIf(chatter -> chatter.getPlayer().equals(player));
  }

  public boolean isNewPlayer(Player player) {
    return DatabaseUtils.get("players." + player.getUniqueId()) == null;
  }

}
