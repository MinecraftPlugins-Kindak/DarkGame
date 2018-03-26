package se.kindak.darkgame.playerdata;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import se.kindak.darkgame.game.dungeon.DungeonArena;
import se.kindak.darkgame.util.message.MessageComponent;

import java.util.UUID;

public class PlayerData {
    private UUID playerUUID;
    private DungeonArena currentArena;


    public void msg(String message) {

    }

    public void msg(MessageComponent message) {
        switch (message.getType()) {
            case ACTION_BAR:
                getPlayer().spigot().sendMessage(new TextComponent());
                break;
            case TITLE:
                break;
            case CHAT:
                break;
            default:
                return;
        }
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(playerUUID);
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public DungeonArena getCurrentArena() {
        return currentArena;
    }

    public void setCurrentArena(DungeonArena currentArena) {
        this.currentArena = currentArena;
    }
}
