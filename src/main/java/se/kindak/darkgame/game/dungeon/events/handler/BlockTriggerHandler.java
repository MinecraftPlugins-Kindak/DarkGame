package se.kindak.darkgame.game.dungeon.events.handler;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import se.kindak.darkgame.game.dungeon.events.dungeonevents.BlockTriggerEvent;

public class BlockTriggerHandler implements Listener {
    @EventHandler
    public void blockTriggerEvent(BlockTriggerEvent e) {
        if (!e.getBlockTrigger().hasRunned)
            e.getBlockTrigger().run();
    }


}
