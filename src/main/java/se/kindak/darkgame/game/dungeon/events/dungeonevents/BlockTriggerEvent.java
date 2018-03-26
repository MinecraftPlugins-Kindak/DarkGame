package se.kindak.darkgame.game.dungeon.events.dungeonevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import se.kindak.darkgame.game.dungeon.essentials.BlockTrigger;
import se.kindak.darkgame.game.dungeon.util.BlockAction;

public class BlockTriggerEvent extends Event {
    public static HandlerList handlerList = new HandlerList();
    private BlockAction usedAction;
    private BlockTrigger blockTrigger;
    private Player whoActivated;


    public BlockTriggerEvent(BlockAction usedAction, BlockTrigger blockTrigger, Player whoActivated) {
        this.usedAction = usedAction;
        this.blockTrigger = blockTrigger;
        this.whoActivated = whoActivated;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public static void setHandlerList(HandlerList handlerList) {
        BlockTriggerEvent.handlerList = handlerList;
    }

    public BlockAction getUsedAction() {
        return usedAction;
    }

    public void setUsedAction(BlockAction usedAction) {
        this.usedAction = usedAction;
    }

    public BlockTrigger getBlockTrigger() {
        return blockTrigger;
    }

    public void setBlockTrigger(BlockTrigger blockTrigger) {
        this.blockTrigger = blockTrigger;
    }

    public Player getWhoActivated() {
        return whoActivated;
    }

    public void setWhoActivated(Player whoActivated) {
        this.whoActivated = whoActivated;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
