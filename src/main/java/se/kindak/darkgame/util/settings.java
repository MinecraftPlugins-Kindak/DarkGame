package se.kindak.darkgame.util;

import org.bukkit.configuration.file.FileConfiguration;

public class settings {
    private static settings instance;
    private final int MAX_RUNNING_DUNGEONS;
    private final int MAX_PARTY_SIZE;

    public settings(FileConfiguration settings) {
        instance = this;
        this.MAX_RUNNING_DUNGEONS = settings.getInt("Max_Running_Dungeons");
        this.MAX_PARTY_SIZE = settings.getInt("Max_Party_Size");

    }

    public static settings instance() {
        return instance;
    }

    public int getMAX_RUNNING_DUNGEONS() {
        return MAX_RUNNING_DUNGEONS;
    }

    public int getMAX_PARTY_SIZE() {
        return MAX_PARTY_SIZE;
    }
}
