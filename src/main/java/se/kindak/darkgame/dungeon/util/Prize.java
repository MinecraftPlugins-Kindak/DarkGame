package se.kindak.darkgame.dungeon.util;

import org.bukkit.Material;
import se.kindak.kindaklib.item.ItemBuilder;

public class Prize extends ItemBuilder {
    private int dropChance;

    public Prize(Material type, int amount, short dataId, int dropChance) {
        super(type, amount, dataId);
        this.dropChance = dropChance;
    }

}

