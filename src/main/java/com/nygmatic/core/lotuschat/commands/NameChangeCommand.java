package com.nygmatic.core.lotuschat.commands;

import com.nygmatic.core.lotuschat.LotusChat;
import com.nygmatic.core.lotuschat.chat.ChatManager;
import com.nygmatic.core.lotuschat.chat.Chatter;
import com.nygmatic.core.lotuschat.util.PlayerDataUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class NameChangeCommand implements CommandExecutor {

    private ChatManager chatManager = LotusChat.getChatManager();

    // /namechange <first/last> <new_name> (target_username)
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        //I'M LAZZZYYYY, I'll maybe make it console executable later
        if (!(sender instanceof Player)) {
            System.out.println(ChatColor.DARK_RED + "Only players can change names!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("lotuschat.names.change")) {
            System.out.println(ChatColor.DARK_RED + "You do not have permission to use this command!");
            return true;
        }

        if (args.length < 2 || args.length > 3 || !(args[0].equalsIgnoreCase("first") ||
                args[0].equalsIgnoreCase("last"))) {

            player.sendMessage(ChatColor.DARK_RED + "Incorrect Arguments!\n" + ChatColor.GOLD
                    + "/namechange " + ChatColor.RED + "<first/last> <new_name> (player_username)");
            return true;
        }

        String firstOrLast = args[0].toLowerCase();
        String newName = args[1];

        if(!newName.matches("[a-zA-Z]+")) {
            player.sendMessage(ChatColor.DARK_RED + "Names can only be alphanumeric!");
            return true;
        }

        Optional<Chatter> chatter;
        String successfulChange = firstOrLast + "-name was successfully changed!";

        if (args.length == 2) {
            chatter = chatManager.getChatter(player);
            successfulChange = ChatColor.GREEN + "Your " + successfulChange;

        } else {

            if (!player.hasPermission("lotuschat.names.change.others")) {
                System.out.println(ChatColor.DARK_RED + "You do not have permission to use this command!");
                return true;
            }

            Player target = Bukkit.getServer().getPlayer(args[2]);
            chatter = chatManager.getChatter(target);
            successfulChange = ChatColor.GREEN + "Players' " + successfulChange;

            //offline-player name changing
            if (target == null) {
                UUID targetUUID = Bukkit.getOfflinePlayer(args[2]).getUniqueId();
                if (PlayerDataUtil.get("players." + targetUUID).isEmpty()) {
                    player.sendMessage(ChatColor.DARK_RED + "Player not found!");
                    return true;
                }
                PlayerDataUtil.set("players." + targetUUID + "." + firstOrLast + "-name", newName);
                player.sendMessage(successfulChange);
                return true;
            }
        }

        if (!chatter.isPresent()) {
            player.sendMessage(ChatColor.DARK_RED + "Player not found!");
            return true;
        }

        if (firstOrLast.equals("first")) {
            chatter.get().setFirstName(newName);
        } else {
            chatter.get().setLastName(newName);
        }

        player.sendMessage(successfulChange);
        return true;
    }
}
