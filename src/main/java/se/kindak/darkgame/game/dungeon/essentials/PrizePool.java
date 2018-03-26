package se.kindak.darkgame.game.dungeon.essentials;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import se.kindak.darkgame.game.dungeon.util.Prize;

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
        this.prizes = new ArrayList<>();

/*        for (String key : config.getKeys(false)) {
            //TODO - Need update
            int chance = config.getInt(key);
            ItemBuilder item = ItemBuilder.deSerilize(key);
            Prize prize = new Prize(item.build(), chance);
            prizes.add(prize);
        }*/
    }

    public List<Prize> prizesWithChance(int chance) {
        return this.prizes.stream().filter(prize -> prize.getDropChance() >= chance).collect(Collectors.toList());
    }

    public ItemStack[] lootToSend() {
        int chance = rollChance();
        List<Prize> selection = Arrays.asList(gatherLoot(chance));
        selection.sort(Comparator.comparing(prize -> prize.getDropChance()));
        int amount = prizes.size() > MAX_REWARDS ? MAX_REWARDS : prizes.size();
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < amount - 1; i++) {
            items.add(selection.get(i).build());
        }
        return (ItemStack[]) items.toArray();
    }

    public Prize[] gatherLoot(int chance) {
        return (Prize[]) prizesWithChance(chance).toArray();
    }

    public int rollChance() {
        int random = new Random().nextInt() * 100;
        return random;
    }

}
