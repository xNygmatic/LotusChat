package com.nygmatic.core.lotuschat.commands;

import com.nygmatic.core.lotuschat.LotusChat;
import com.nygmatic.core.lotuschat.chat.Channel;
import com.nygmatic.core.lotuschat.chat.ChatManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChannelCreateCommand implements CommandExecutor {

    private ChatManager chatManager = LotusChat.getChatManager();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        //only like this because later on it's prolly gonna be a convo for prompt confirming and such (also lazy atm :>)
        //Once it is a convo, *might* have channel creation available from console if entered how it is now;
        //(/createchannel <symbol> <prefix> <send-permission> <receive-permission> <radius>)
        if (!(sender instanceof Player)) {
            System.out.println(ChatColor.DARK_RED + "Only players can create new channels");
            return true;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("lotuschat.channel.create")) {
            System.out.println(ChatColor.DARK_RED + "You do not have permission to use this command!");
            return true;
        }

       if (args.length != 5) {
           player.sendMessage(ChatColor.DARK_RED + "Wrong amount of arguments!\n" + ChatColor.GOLD
                   + "/createchannel " + ChatColor.RED + "<symbol> <prefix> <send_permission> <receive_permission> <radius>");
           return true;
       }

       char symbol = args[0].charAt(0);

       if (!(chatManager.getChannel(symbol).equals(chatManager.getLocal()))) {
           player.sendMessage(ChatColor.DARK_RED + "A channel with that symbol already exists!");
           return true;
       }

       String prefix = args[1];
       String sendPermission = args[2];
       String receivePermission = args[3];
       int radius = Integer.parseInt(args[4]);

       LotusChat.getChatManager().addChannel(new Channel(symbol, prefix, sendPermission, receivePermission, radius));
       player.sendMessage(ChatColor.GREEN + "Channel created!");
       return true;
    }
}
