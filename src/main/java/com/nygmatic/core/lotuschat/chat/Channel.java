package com.nygmatic.core.lotuschat.chat;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Channel implements ConfigurationSerializable {

  private final List<String> permissions; // Only players with these permissions can use this chat
  private final String msgPrefix; // The String that will come in front of every message
  private final char prefix; // Character the player must use to access this chat

  // We'll add this one in case you want to make a command for creating channels.
  public Channel(char prefix, String msgPrefix, String... permissions) {
    this.prefix = prefix;
    this.msgPrefix = msgPrefix;
    this.permissions = Arrays.asList(permissions);
  }

  // This constructor is required for deserialization
  public Channel(Map<String, Object> map) {
    this.prefix = (char) map.get("prefix");
    this.msgPrefix = (String) map.get("message_prefix");
    this.permissions = (List<String>) map.get("permissions");
  }

  @Override
  public Map<String, Object> serialize() {

    // Key to value, just as it would appear in a YAML file
    LinkedHashMap<String, Object> map = new LinkedHashMap<>();

    // These have to match the keys and value types in the deserialization constructor.
    map.put("prefix", this.prefix);
    map.put("message_prefix", msgPrefix);
    map.put("permissions", permissions);

    /*
     * Example YAML Output:
     *
     * random_key:
     *    prefix: '@'
     *    message_prefix: "[Lame Channel]"
     *    permissions:
     *      - "random.permission.is.lame"
     *      - "lots.of.lame.permissions.gross"
     *
     */

    return map;
  }

  public List<String> getPermissions() {
    return permissions;
  }

  public String getMessagePrefix() {
    return msgPrefix;
  }

  public char getPrefix() {
    return prefix;
  }

}
