package se.kindak.darkgame.playerdata.util;

import se.kindak.darkgame.playerdata.PlayerData;
import se.kindak.darkgame.util.Settings;

import java.util.HashSet;
import java.util.Set;

public class Party {
    private Set<PlayerData> players;
    private PlayerData creator;

    public Party(Set<PlayerData> players, PlayerData creator) {
        this.players = players;
        this.creator = creator;
        players.add(creator);
    }

    public Party(PlayerData creator) {
        this.creator = creator;
        this.players = new HashSet<>();
        players.add(creator);

    }

    public int getPartySize() {
        return players.size();
    }

    public boolean addPlayer(PlayerData player) {
        if (players.size() == Settings.instance().getMAX_PARTY_SIZE())
            return false;

        return players.add(player);
    }

    public boolean removePlayer(PlayerData player) {
        if (player == creator)
            return false;

        return players.remove(player);
    }

    public Set<PlayerData> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerData> players) {
        this.players = players;
    }

    public PlayerData getCreator() {
        return creator;
    }

    public void setCreator(PlayerData creator) {
        this.creator = creator;
    }
}
