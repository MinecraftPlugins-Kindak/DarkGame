package se.kindak.darkgame.dungeon.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import se.kindak.kindaklib.item.ItemBuilder;

public class Prize extends ItemBuilder {
    private int dropChance;

    public Prize(Material type, int amount, short dataId, int dropChance) {
        super(type, amount, dataId);
        this.dropChance = dropChance;
    }

    public Prize(ItemStack itemStack, int dropChance) {
        super(itemStack);
        this.dropChance = dropChance;
    }

    public int getDropChance() {
        return dropChance;
    }

    public void setDropChance(int dropChance) {
        this.dropChance = dropChance;
    }
}

