package me.Navoei.customdiscsplugin.command.SubCommands;

import me.Navoei.customdiscsplugin.CustomDiscs;
import me.Navoei.customdiscsplugin.command.SubCommand;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class SetChannelCommand extends SubCommand {

    @Override
    public String getName() {
        return "channel";
    }

    @Override
    public String getDescription() {
        return ChatColor.GRAY + "Set channel.";
    }

    @Override
    public String getSyntax() {
        return ChatColor.GREEN + "/customdisc channel <1-3>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (CustomDiscs.isMusicDisc(player)) {
            if (args.length >= 2) {

                if (!player.hasPermission("customdiscs.channel")) {
                    player.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
                    return;
                }

                // /cd range 0
                // [0] [1]
                // Find file, if file not there then say "file not there"
                Float range = Float.valueOf(args[1]);

                if (range < 1 || range > 3) {
                    player.sendMessage(ChatColor.RED + "You need to select a ranger between 1 and 3 ");
                    return;
                }

                // Sets the lore of the item to the quotes from the command.
                ItemStack disc = new ItemStack(player.getInventory().getItemInMainHand());
                ItemMeta meta = disc.getItemMeta();

                PersistentDataContainer data = meta.getPersistentDataContainer();
                data.set(new NamespacedKey(CustomDiscs.getInstance(), "CustomSoundChannel"), PersistentDataType.FLOAT,
                        range);

                player.getInventory().getItemInMainHand().setItemMeta(meta);

                player.sendMessage("Your channel is set to: " + ChatColor.GRAY + range);

            } else {
                player.sendMessage(ChatColor.RED + "Insufficient arguments! ( /customdisc channel <range>");
            }
        } else {
            player.sendMessage(ChatColor.RED + "You are not holding a music disc in your main hand!");
        }
    }

    @Override
    public List<String> onTabComplete(String[] args) {

        List<String> arguments = new ArrayList<>();
        arguments.add("1");
        arguments.add("2");
        arguments.add("3");
        return arguments;

    }
}
