package se.kindak.darkgame;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DarkGameMain extends JavaPlugin {
    private static DarkGameMain instance;

    public static DarkGameMain getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {

    }

    public void log(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
}
