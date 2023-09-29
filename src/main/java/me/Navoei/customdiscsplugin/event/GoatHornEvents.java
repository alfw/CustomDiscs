package me.Navoei.customdiscsplugin.event;

import me.Navoei.customdiscsplugin.CustomDiscs;
import me.Navoei.customdiscsplugin.PlayerManager;
import me.Navoei.customdiscsplugin.VoicePlugin;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.nio.file.Path;

public class GoatHornEvents implements Listener {

    private static final NamespacedKey CUSTOM_DATA_KEY = new NamespacedKey(CustomDiscs.getInstance(), "customdisc");

    @EventHandler(ignoreCancelled = true)
    public void onClick(PlayerInteractEvent event) {
        System.out.print("interact");
        event.getPlayer().sendMessage("Ran actioon " + event.getAction());
        event.getPlayer().sendMessage("Ran event:  " + event);
        event.getPlayer().sendMessage("----");
        if (event.getAction() != Action.RIGHT_CLICK_AIR) {
            return;
        }

        ItemStack item = event.getItem();
        event.getPlayer().sendMessage("Ran2 " + item);
        event.getPlayer().sendMessage("Ran2 getType" + item.getType());

        if (item != null && item.getType() == Material.GOAT_HORN) {
            event.getPlayer().sendMessage("Ran3 " + item);
            if (this.playHorn(event.getPlayer(), item)) {
                event.setCancelled(true);
            }
        }
    }

    private boolean playHorn(Player user, ItemStack horn) {

        var data = horn.getItemMeta().getPersistentDataContainer();
        if (!data.has(CUSTOM_DATA_KEY, PersistentDataType.STRING)) {
            return false;
        }

        String soundFileName = data.get(CUSTOM_DATA_KEY, PersistentDataType.STRING);

        Path soundFilePath = Path.of(CustomDiscs.getInstance().getDataFolder().getPath(), "musicdata", soundFileName);

        if (soundFilePath.toFile().exists() && VoicePlugin.voicechatServerApi != null) {
            return PlayerManager.instance().playGoatHorn(VoicePlugin.voicechatServerApi, soundFilePath, user) != null;
        }

        return false;
    }
}