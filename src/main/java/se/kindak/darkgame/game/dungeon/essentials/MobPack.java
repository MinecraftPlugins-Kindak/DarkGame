package se.kindak.darkgame.game.dungeon.essentials;

import io.lumine.xikage.mythicmobs.MythicMobs;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;
import se.kindak.darkgame.DarkGameMain;
import se.kindak.darkgame.game.dungeon.DungeonArena;
import se.kindak.darkgame.game.dungeon.util.Trigger;
import se.kindak.kindaklib.location.LocationFormater;

import java.util.HashSet;
import java.util.Set;

public class MobPack extends Trigger {
    private final int id;
    private final String type;
    private final int amount;
    private final Location spawnpoint;
    private final Set<Entity> mobs;
    private final boolean spawnAtStart;
    public boolean hasSpawned;

    public MobPack(FileConfiguration configuration, String id, DungeonArena arena) {
        super(configuration, id, arena);
        this.id = Integer.parseInt(id);
        this.type = configuration.getString(id + ".Type");
        this.amount = configuration.getInt(id + ".Amount");
        this.spawnpoint = LocationFormater.format(configuration.getString(id + ".Location"));
        this.spawnAtStart = configuration.getBoolean(id + "Spawn_At_Start");
        this.hasSpawned = false;
        this.mobs = new HashSet<>();
    }

    public boolean spawn() {
        if (hasSpawned)
            return false;
        int i = 1;
        while (i < amount) {
            MythicMobs.inst()
                    .getMobManager()
                    .spawnMob(type, spawnpoint)
                    .getEntity()
                    .getBukkitEntity()
                    .setMetadata("Mob", new FixedMetadataValue(DarkGameMain.getInstance(), this));
            i++;
        }
        this.hasSpawned = true;
        return true;
    }

    public boolean kill() {
        if (!hasSpawned)
            return false;
        for (Entity entity : mobs) {
            if (!entity.isDead()) {
                entity.remove();
            }
        }
        mobs.clear();
        this.hasSpawned = false;
        return true;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public Location getSpawnpoint() {
        return spawnpoint;
    }
}
