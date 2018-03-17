package se.kindak.darkgame.dungeon.essentials;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import se.kindak.darkgame.dungeon.util.Prize;
import se.kindak.kindaklib.item.ItemBuilder;

import java.util.*;
import java.util.stream.Collectors;

public class PrizePool {
    private final int MAX_REWARDS;
    private List<Prize> prizes;
    private FileConfiguration config;

    public PrizePool(FileConfiguration config) {
        this.config = config;
        this.loadPrizes();
        this.MAX_REWARDS = config.getInt("Max_Rewards");
    }

    private void loadPrizes() {
        if (config.getKeys(false).isEmpty())
            return;

        for (String key : config.getKeys(false)) {
            int chance = config.getInt(key);
            ItemBuilder item = ItemBuilder.deSerilize(key);
            Prize prize = new Prize(item.build(), chance);
            prizes.add(prize);
        }
    }


    public List<Prize> prizesWithChance(int chance) {
        return this.prizes.stream().filter(prize -> prize.getDropChance() >= chance).collect(Collectors.toList());
    }


    public ItemStack[] lootToSend() {
        List<ItemStack> selection = new ArrayList<>();
        Arrays.asList(gatherLoot()).forEach(prize -> selection.add(prize.build()));
        Collections.shuffle(selection);

        int amountOfRewards;
        if (MAX_REWARDS > selection.size() - 1)
            return (ItemStack[]) selection.toArray();
        else
            amountOfRewards = selection.size() - 1;
        ItemStack[] prizes = new ItemStack[amountOfRewards];
        for (int i = 0; i > amountOfRewards; i++) {
            prizes[i] = selection.get(i);
        }

        return prizes;
    }

    public Prize[] gatherLoot() {
        return (Prize[]) prizesWithChance(rollChance()).toArray();
    }

    public int rollChance() {
        int random = new Random().nextInt() * 100;
        return random;
    }

}
