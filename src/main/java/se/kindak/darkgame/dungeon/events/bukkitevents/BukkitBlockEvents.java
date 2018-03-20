package se.kindak.darkgame.dungeon.events.bukkitevents;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import se.kindak.darkgame.dungeon.essentials.BlockTrigger;
import se.kindak.darkgame.dungeon.events.dungeonevents.BlockTriggerEvent;
import se.kindak.darkgame.dungeon.util.BlockAction;
import se.kindak.darkgame.playerdata.PlayerHandler;

public class BukkitBlockEvents implements Listener {

    @EventHandler
    public void playerJumpOnBlockEvenr(PlayerJumpEvent e) {
        if (PlayerHandler.instance().getPlayerdata(e.getPlayer()).getCurrentArena() == null)
            return;
        if (!e.getPlayer().getLocation().getBlock().hasMetadata("BlockTrigger"))
            return;

        BlockTrigger blockTrigger = null;
        for (Object obj : e.getPlayer().getLocation().getBlock().getMetadata("BlockTrigger")) {
            if (obj instanceof BlockTrigger)
                blockTrigger = (BlockTrigger) obj;
        }

        Bukkit.getPluginManager().callEvent(new BlockTriggerEvent(BlockAction.JUMP_ON_BLOCK, blockTrigger, e.getPlayer()));
    }


    @EventHandler
    public void playerStepOnBlockEvenr(PlayerMoveEvent e) {
        if (PlayerHandler.instance().getPlayerdata(e.getPlayer()).getCurrentArena() == null)
            return;
        if (!e.getPlayer().getLocation().getBlock().hasMetadata("BlockTrigger"))
            return;

        BlockTrigger blockTrigger = null;
        for (Object obj : e.getPlayer().getLocation().getBlock().getMetadata("BlockTrigger")) {
            if (obj instanceof BlockTrigger)
                blockTrigger = (BlockTrigger) obj;
        }
        Bukkit.getPluginManager().callEvent(new BlockTriggerEvent(BlockAction.WALK_ON_BLOCK, blockTrigger, e.getPlayer()));
    }

    @EventHandler
    public void playerSneakOnBlockEvenr(PlayerToggleSneakEvent e) {
        if (PlayerHandler.instance().getPlayerdata(e.getPlayer()).getCurrentArena() == null)
            return;
        if (!e.getPlayer().getLocation().getBlock().hasMetadata("BlockTrigger"))
            return;

        BlockTrigger blockTrigger = null;
        for (Object obj : e.getPlayer().getLocation().getBlock().getMetadata("BlockTrigger")) {
            if (obj instanceof BlockTrigger)
                blockTrigger = (BlockTrigger) obj;
        }
        Bukkit.getPluginManager().callEvent(new BlockTriggerEvent(BlockAction.SNEAK_ON_BLOCK, blockTrigger, e.getPlayer()));
    }

    @EventHandler
    public void playerClickOnBlockEvenr(PlayerInteractEvent e) {
        if (PlayerHandler.instance().getPlayerdata(e.getPlayer()).getCurrentArena() == null)
            return;
        if (!e.getPlayer().getLocation().getBlock().hasMetadata("BlockTrigger"))
            return;

        BlockTrigger blockTrigger = null;
        for (Object obj : e.getPlayer().getLocation().getBlock().getMetadata("BlockTrigger")) {
            if (obj instanceof BlockTrigger)
                blockTrigger = (BlockTrigger) obj;
        }

        BlockAction blockAction;
        switch (e.getAction()) {
            case LEFT_CLICK_BLOCK:
                blockAction = BlockAction.LEFT_CLICK_BLOCK;
                break;
            case RIGHT_CLICK_BLOCK:
                blockAction = BlockAction.RIGHT_CLICK_BLOCK;
                break;
            case PHYSICAL:
                blockAction = BlockAction.ACTIVATE_REDSTONE;
                break;
            default:
                return;
        }

        Bukkit.getPluginManager().callEvent(new BlockTriggerEvent(blockAction, blockTrigger, e.getPlayer()));

    }
}
