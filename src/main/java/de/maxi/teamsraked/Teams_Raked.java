package de.maxi.teamsraked;

import de.maxi.teamsraked.rank.RankCommand;
import de.maxi.teamsraked.rank.RankEvents;
import de.maxi.teamsraked.sql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Teams_Raked extends JavaPlugin {
    private Teams_Raked plugin;
    @Override
    public void onEnable() {
        plugin = this;
        try {
            MySQL.connect();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new RankEvents(), plugin);
        Objects.requireNonNull(getCommand("rank")).setExecutor(new RankCommand());
    }

    @Override
    public void onDisable() {
        MySQL.disconnect();
    }

    public Teams_Raked getPlugin() {
        return plugin;
    }
}
