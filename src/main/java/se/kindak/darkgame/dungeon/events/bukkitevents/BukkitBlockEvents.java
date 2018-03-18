package se.kindak.darkgame.dungeon.events.bukkitevents;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import se.kindak.darkgame.dungeon.events.dungeonevents.BlockTriggerEvent;
import se.kindak.darkgame.dungeon.util.BlockAction;
import se.kindak.darkgame.playerdata.PlayerHandler;

public class BukkitBlockEvents implements Listener {

    @EventHandler
    public void playerJumpOnBlockEvenr(PlayerJumpEvent e) {
        if (PlayerHandler.instance().getPlayerdata(e.getPlayer()).getCurrentArena() == null)
            return;

        Bukkit.getPluginManager().callEvent(new BlockTriggerEvent(BlockAction.JUMP_ON_BLOCK, e.getTo().getBlock(), e.getPlayer()));
    }


    @EventHandler
    public void playerStepOnBlockEvenr(PlayerMoveEvent e) {
        if (PlayerHandler.instance().getPlayerdata(e.getPlayer()).getCurrentArena() == null)
            return;

        Bukkit.getPluginManager().callEvent(new BlockTriggerEvent(BlockAction.WALK_ON_BLOCK, e.getTo().getBlock(), e.getPlayer()));
    }

    @EventHandler
    public void playerSneakOnBlockEvenr(PlayerToggleSneakEvent e) {
        if (PlayerHandler.instance().getPlayerdata(e.getPlayer()).getCurrentArena() == null)
            return;

        Bukkit.getPluginManager().callEvent(new BlockTriggerEvent(BlockAction.SNEAK_ON_BLOCK, e.getPlayer().getLocation().getBlock(), e.getPlayer()));
    }

    @EventHandler
    public void playerClickOnBlockEvenr(PlayerInteractEvent e) {
        if (PlayerHandler.instance().getPlayerdata(e.getPlayer()).getCurrentArena() == null)
            return;

        switch (e.getAction()) {
            case LEFT_CLICK_BLOCK:
                Bukkit.getPluginManager().callEvent(new BlockTriggerEvent(BlockAction.LEFT_CLICK_BLOCK, e.getClickedBlock(), e.getPlayer()));
                break;
            case RIGHT_CLICK_BLOCK:
                Bukkit.getPluginManager().callEvent(new BlockTriggerEvent(BlockAction.RIGHT_CLICK_BLOCK, e.getClickedBlock(), e.getPlayer()));
                break;
            case PHYSICAL:
                Bukkit.getPluginManager().callEvent(new BlockTriggerEvent(BlockAction.ACTIVATE_REDSTONE, e.getClickedBlock(), e.getPlayer()));
                break;
            default:
                return;
        }
    }
}
