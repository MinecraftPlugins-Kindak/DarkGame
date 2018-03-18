package se.kindak.darkgame.util;

import org.bukkit.configuration.file.FileConfiguration;

public class settings {
    private final int MAX_DUNGEONS_RUNNING;

    public settings(FileConfiguration settings) {
        this.MAX_DUNGEONS_RUNNING = settings.getInt("");
    }
}
