package se.kindak.darkgame.game.dungeon.util;

import org.bukkit.configuration.file.FileConfiguration;
import se.kindak.darkgame.DarkGameMain;
import se.kindak.darkgame.game.dungeon.DungeonArena;
import se.kindak.darkgame.game.dungeon.essentials.DungeonMessage;
import se.kindak.darkgame.game.dungeon.essentials.Gate;
import se.kindak.darkgame.game.dungeon.essentials.MobPack;

import java.util.HashSet;
import java.util.Set;

//todo - Done
public class Trigger {
    public boolean hasRunned;
    public boolean shouldEndGame;
    private Set<Gate> gates;
    private Set<MobPack> mobPacks;
    private Set<DungeonMessage> dungeonMessages;
    private DungeonArena arena;

    public Trigger(FileConfiguration configuration, String path, DungeonArena arena) {
        if (configuration.getConfigurationSection(path + ".Trigger") == null)
            return;
        this.gates = new HashSet<>();
        this.mobPacks = new HashSet<>();
        this.dungeonMessages = new HashSet<>();
        this.arena = arena;
        this.hasRunned = false;
        this.shouldEndGame = configuration.getBoolean(path + ".Trigger.End_Game");

        if (shouldEndGame)
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
            for (String mobId : configuration.getConfigurationSection(path + ".Trigger.Mobs").getKeys(false)) {
                int id = Integer.parseInt(mobId);
                if (arena.getMessage(id) != null) {
                    dungeonMessages.add(arena.getMessage(id));
                }
            }
        }
    }

    public boolean run() {
        if (!hasRunned)
            return false;
        this.gates.forEach(gate -> gate.subLocks(1));
        this.mobPacks.forEach(mobPack -> mobPack.spawn());
        this.dungeonMessages.forEach(dungeonMessage -> dungeonMessage.runTaskTimer(DarkGameMain.getInstance(), 1, 1));
        this.hasRunned = true;
        return true;
    }

    public boolean isShouldEndGame() {
        return shouldEndGame;
    }

    public void setShouldEndGame(boolean shouldEndGame) {
        this.shouldEndGame = shouldEndGame;
    }

    public Set<Gate> getGates() {
        return gates;
    }

    public void setGates(Set<Gate> gates) {
        this.gates = gates;
    }

    public Set<MobPack> getMobPacks() {
        return mobPacks;
    }

    public void setMobPacks(Set<MobPack> mobPacks) {
        this.mobPacks = mobPacks;
    }

    public boolean isHasRunned() {
        return hasRunned;
    }

    public void setHasRunned(boolean hasRunned) {
        this.hasRunned = hasRunned;
    }

    public DungeonArena getArena() {
        return arena;
    }

    public void setArena(DungeonArena arena) {
        this.arena = arena;
    }

    public Set<DungeonMessage> getDungeonMessages() {
        return dungeonMessages;
    }

    public void setDungeonMessages(Set<DungeonMessage> dungeonMessages) {
        this.dungeonMessages = dungeonMessages;
    }
}
