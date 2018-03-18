package se.kindak.darkgame.dungeon.events.dungeonevents;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import se.kindak.darkgame.dungeon.util.BlockAction;

public class BlockTriggerEvent extends Event {
    public static HandlerList handlerList = new HandlerList();
    private BlockAction usedAction;
    private Block block;
    private Player whoActivated;

    public BlockTriggerEvent(BlockAction usedAction, Block block, Player whoActivated) {
        this.usedAction = usedAction;
        this.block = block;
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

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
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
