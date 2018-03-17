package se.kindak.darkgame.dungeon.util;

import org.bukkit.configuration.file.FileConfiguration;
import se.kindak.darkgame.dungeon.DungeonArena;
import se.kindak.darkgame.dungeon.essentials.Gate;
import se.kindak.darkgame.dungeon.essentials.Message;
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
            for (String gateId : configuration.getConfigurationSection(path + ".Trigger.Gates").getKeys(false)) {
                int id = Integer.parseInt(gateId);
                if (arena.getGate(id) != null) {
                    gates.add(arena.getGate(id));
                }
            }
        }

        if (configuration.getConfigurationSection(path + ".Trigger.Mobs") != null) {
            for (String mobId : configuration.getConfigurationSection(path + ".Trigger.Mobs").getKeys(false)) {
                int id = Integer.parseInt(mobId);
                if (arena.getMobPack(id) != null) {
                    mobPacks.add(arena.getMobPack(id));
                }
            }
        }

        if (configuration.getConfigurationSection(path + ".Trigger.Messages") != null) {

        }
    }

    public void run() {

    }
}
