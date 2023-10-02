package me.Navoei.customdiscsplugin.command;

import java.util.List;

import org.bukkit.entity.Player;

public abstract class SubCommand {

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract void perform(Player player, String[] args);

    public abstract List<String> onTabComplete(String[] args);

}
