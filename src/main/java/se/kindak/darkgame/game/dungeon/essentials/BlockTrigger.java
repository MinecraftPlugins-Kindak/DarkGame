package se.kindak.darkgame.game.dungeon.essentials;

import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.metadata.FixedMetadataValue;
import se.kindak.darkgame.DarkGameMain;
import se.kindak.darkgame.game.dungeon.DungeonArena;
import se.kindak.darkgame.game.dungeon.util.BlockAction;
import se.kindak.darkgame.game.dungeon.util.Trigger;

//DONE
public class BlockTrigger extends Trigger {
    private int id;
    private BlockAction action;
    private Block block;

    public BlockTrigger(FileConfiguration configuration, String path, DungeonArena arena, Block block) {
        super(configuration, path, arena);
        this.block = block;
        this.block.setMetadata("BlockTrigger", new FixedMetadataValue(DarkGameMain.getInstance(), this));
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public BlockAction getAction() {
        return action;
    }

    public void setAction(BlockAction action) {
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
