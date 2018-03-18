package se.kindak.darkgame.playerdata;

import se.kindak.darkgame.dungeon.DungeonArena;

import java.util.UUID;

public class PlayerData {
    private UUID player;
    private DungeonArena currentArena;


    public void msg(String message) {

    }

    public UUID getPlayer() {
        return player;
    }

    public void setPlayer(UUID player) {
        this.player = player;
    }

    public DungeonArena getCurrentArena() {
        return currentArena;
    }

    public void setCurrentArena(DungeonArena currentArena) {
        this.currentArena = currentArena;
    }
}
