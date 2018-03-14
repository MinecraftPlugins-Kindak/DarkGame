package se.kindak.darkgame.dungeon.util;

import org.bukkit.configuration.file.FileConfiguration;
import se.kindak.darkgame.dungeon.DungeonArena;
import se.kindak.darkgame.dungeon.essentials.Gate;
import se.kindak.darkgame.dungeon.essentials.MobPack;

import java.util.HashSet;
import java.util.Set;

public class Trigger {
    private Set<Gate> gates;
    private Set<MobPack> mobPacks;
    private Set<Message> messages;

    public Trigger(FileConfiguration configuration, String path, DungeonArena arena) {
        this.gates = new HashSet<>();
        this.mobPacks = new HashSet<>();
        this.messages = new HashSet<>();

        if (configuration.getConfigurationSection(path + ".Trigger") == null)
            return;

        if (configuration.getConfigurationSection(path + ".Trigger.Gates") != null) {
            for (String gateName : configuration.getConfigurationSection(path + ".Trigger.Gates").getKeys(false)) {
                int id = Integer.parseInt(gateName);
                if (arena.getGate(id) != null) {
                    gates.add(arena.getGate(id));
                }
            }
        }

        if (configuration.getConfigurationSection(path + ".Trigger.Mobs") != null) {

        }

    }
}
