package com.nygmatic.core.lotuschat.chat;

import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.LinkedHashMap;
import java.util.Map;

public class Channel implements ConfigurationSerializable {

  private final String sendPermission;
  private final String receivePermission;
  private final String msgPrefix; // The String that will come in front of every message
  private final char prefix; // Character the player must use to access this chat
  private final int radius;

  // We'll add this one in case you want to make a command for creating channels.
  public Channel(char prefix, String msgPrefix, String sendPermission, String receivePermission, int radius) {
    this.prefix = prefix;
    this.msgPrefix = msgPrefix;
    this.sendPermission = sendPermission;
    this.receivePermission = receivePermission;
    this.radius = radius;
  }

  // This constructor is required for deserialization
  public Channel(Map<String, Object> map) {
    this.prefix = ((String) map.get("prefix")).charAt(0);
    this.msgPrefix = (String) map.get("message_prefix");
    this.sendPermission = (String) map.get("send_permission");
    this.receivePermission = (String) map.get("receive_permission");
    this.radius = (int) map.get("radius");
  }

  @Override
  public Map<String, Object> serialize() {

    // Key to value, just as it would appear in a YAML file
    LinkedHashMap<String, Object> map = new LinkedHashMap<>();

    // These have to match the keys and value types in the deserialization constructor.
    map.put("prefix", this.prefix);
    map.put("message_prefix", msgPrefix);
    map.put("send_permission", sendPermission);
    map.put("receive_permission", receivePermission);
    map.put("radius", radius);

    /*
     * Example YAML Output:
     * random_key:
     *    prefix: '@'
     *    message_prefix: "[Lame Channel]"
     *    send_permission: "permission.gross"
     *    receive_permission: "another.perm.gross"
     *    radius: -1
     */

    return map;
  }

  public String getSendPermission() {
    return sendPermission;
  }

  public String getReceivePermission() {
    return receivePermission;
  }

  public String getMessagePrefix() {
    return ChatColor.translateAlternateColorCodes('&', msgPrefix);
  }

  public char getPrefix() {
    return prefix;
  }

  public int getRadius() {
    return radius;
  }

}
