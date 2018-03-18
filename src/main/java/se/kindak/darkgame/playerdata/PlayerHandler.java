package se.kindak.darkgame.playerdata;

import org.bukkit.entity.Player;

import java.util.Set;

public class PlayerHandler {
    private static PlayerHandler instance;
    public Set<PlayerData> players;

    public static PlayerHandler instance() {
        return instance;
    }

    public PlayerData getPlayerdata(Player player) {
        return (PlayerData) players.stream().filter(p -> p.getPlayer() == player.getUniqueId()).toArray()[0];
    }

    public Set<PlayerData> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerData> players) {
        this.players = players;
    }
}
