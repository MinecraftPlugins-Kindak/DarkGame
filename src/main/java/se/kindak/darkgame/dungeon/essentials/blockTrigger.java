package se.kindak.darkgame.dungeon.essentials;

import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import se.kindak.darkgame.dungeon.DungeonArena;
import se.kindak.darkgame.dungeon.util.BlockAction;
import se.kindak.darkgame.dungeon.util.Trigger;

public class blockTrigger extends Trigger {
    private int id;
    private BlockAction action;
    private Block block;

    public blockTrigger(FileConfiguration configuration, String path, DungeonArena arena, Block block) {
        super(configuration, path, arena);
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
