package se.kindak.darkgame;

import org.bukkit.plugin.java.JavaPlugin;

public final class DarkGameMain extends JavaPlugin {
    public static DarkGameMain instance;
    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {

    }


}
