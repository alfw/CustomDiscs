package me.Navoei.customdiscsplugin.command.SubCommands;

import me.Navoei.customdiscsplugin.CustomDiscs;
import me.Navoei.customdiscsplugin.command.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class LoggingCommand extends SubCommand {

    @Override
    public String getName() {
        return "logging";
    }

    @Override
    public String getDescription() {
        return ChatColor.GRAY + "Set logging";
    }

    @Override
    public String getSyntax() {
        return ChatColor.GREEN + "/customdisc range <true/false>";
    }

    @Override
    public void perform(Player player, String[] args) {

            if (args.length >= 2) {

                if (!player.hasPermission("customdiscs.logging")) {
                    player.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
                    return;
                }

                // /cd range 0
                //      [0]  [1]
                //Find file, if file not there then say "file not there"
                Boolean logging = false;

                if (args[1].equals("true")) {
                    logging = true;
                }else if (args[1].equals("false")) {
                    logging = false;
                }

                CustomDiscs.getInstance().logLocation = logging;

                player.sendMessage("Logging is set to: " + ChatColor.GRAY + logging);

            } else {
                player.sendMessage(ChatColor.RED + "Insufficient arguments! ( /customdisc logging <range>");
            }
    }
}
