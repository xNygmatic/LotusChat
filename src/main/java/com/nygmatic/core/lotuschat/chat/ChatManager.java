package com.nygmatic.core.lotuschat.chat;

import com.nygmatic.core.lotuschat.LotusChat;
import com.nygmatic.core.lotuschat.util.PlayerDataUtil;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ChatManager implements Listener {

  private Configuration config = LotusChat.getInstance().getConfig();

  private List<Channel> channels = (List<Channel>) config.getList("channels");
  private Set<Chatter> chatters = new HashSet<>();
  private Channel local = (Channel) config.get("default_channel");


  public void setupPlayer(Player player, String firstName, String lastName) {
    String path = "players." + player.getUniqueId();
    PlayerDataUtil.set(path + ".first-name", firstName);
    PlayerDataUtil.set(path + ".last-name", lastName);
    PlayerDataUtil.set(path + ".chat-color", ChatColor.WHITE.name());
    PlayerDataUtil.set(path + ".name-color", ChatColor.GRAY.name());
    addChatter(new Chatter(player));
  }

  public Optional<Chatter> getChatter(Player player) {
    return chatters.stream().filter(chatter -> chatter.getPlayer().equals(player)).findAny();
  }

  public Set<Chatter> getChatters() {
    return chatters;
  }

  public void addChatter(Chatter chatter) {
    chatters.add(chatter);
  }

  public void removeChatter(Player player) {
    chatters.removeIf(chatter -> chatter.getPlayer().equals(player));
  }

  public boolean isNewPlayer(Player player) {
    return PlayerDataUtil.get("players." + player.getUniqueId()) == null;
  }

  public List<Channel> getChannels() {
    return channels;
  }

  public Channel getChannel(char symbol) {
    for (int i = 0; i < channels.size(); i++) {
      if (symbol == channels.get(i).getPrefix()) {
        return channels.get(i);
      }
    }
    return local;
  }

  public Channel getLocal() {
    return local;
  }

  public void addChannel(Channel channel) {
    channels.add(channel);
  }
}
