package se.kindak.darkgame.dungeon.util;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import org.bukkit.Location;

public class Mob {
    private String mob;
    private Location spawn;

    public Mob(String mob, Location spawn) {
        this.mob = mob;
        this.spawn = spawn;
    }

    public AbstractEntity spawnMob() {
        return MythicMobs.inst().getMobManager().spawnMob(mob, spawn).getEntity();
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }
}
