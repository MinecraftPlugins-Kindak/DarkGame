package se.kindak.darkgame.game.dungeon.essentials;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import se.kindak.darkgame.game.dungeon.DungeonArena;
import se.kindak.darkgame.game.dungeon.util.Trigger;
import se.kindak.kindaklib.location.Cuboid;
import se.kindak.kindaklib.location.LocationFormater;

import java.util.HashMap;

// DOne
public class Gate extends Trigger {
    private final int id;
    private final int MAX_LOCKS;
    public boolean isOpen;
    public boolean isFirstGate;
    private int locks;
    private HashMap<Location, Material> materialMap;
    private Cuboid cuboid;

    public Gate(FileConfiguration configuration, int id, DungeonArena arena) {
        super(configuration, id + "", arena);
        this.id = id;
        this.MAX_LOCKS = configuration.getInt(id + ".Locks");
        this.locks = MAX_LOCKS;
        this.cuboid = new Cuboid(
                LocationFormater.format(configuration.getString(id + ".Point_One")),
                LocationFormater.format(configuration.getString(id + ".Point_Two")));
        this.isFirstGate = configuration.getBoolean(id + ".Is_First_Gate");
        this.materialMap = new HashMap<>();
    }

    public boolean open(boolean force) {
        if (isOpen)
            return false;
        if (force == false && locks > 0)
            return false;

        World w = cuboid.getLocationOne().getWorld();
        for (int x = cuboid.getMinPoints()[0]; x < cuboid.getMaxPoints()[0]; x++) {
            for (int y = cuboid.getMinPoints()[1]; y < cuboid.getMaxPoints()[1]; y++) {
                for (int z = cuboid.getMinPoints()[2]; z < cuboid.getMaxPoints()[2]; z++) {
                    materialMap.put(new Location(w, x, y, z), w.getBlockAt(x, y, z).getType());
                    w.getBlockAt(x, y, z).setType(Material.AIR);
                }
            }
        }

        this.locks = 0;
        this.isOpen = true;
        this.run();
        return true;
    }

    public boolean close() {
        if (!isOpen)
            return false;
        for (Location location : materialMap.keySet()) {
            location.getBlock().setType(materialMap.get(location));
        }
        this.isOpen = false;
        materialMap.clear();
        this.locks = MAX_LOCKS;
        return false;
    }

    public void addLocks(int amount) {
        this.locks += amount;

        if (this.locks > MAX_LOCKS)
            this.locks = MAX_LOCKS;
    }

    public void subLocks(int amount) {
        this.locks -= amount;

        if (this.locks < 0)
            this.locks = 0;
    }

    public void resetLocks() {
        this.locks = MAX_LOCKS;
    }

    //Getters & Setters

    public boolean isFirstGate() {
        return isFirstGate;
    }

    public void setFirstGate(boolean firstGate) {
        isFirstGate = firstGate;
    }

    public Cuboid getCuboid() {
        return cuboid;
    }

    public void setCuboid(Cuboid cuboid) {
        this.cuboid = cuboid;
    }

    public int getId() {
        return id;
    }

    public int getMAX_LOCKS() {
        return MAX_LOCKS;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getLocks() {
        return locks;
    }

    public void setLocks(int locks) {
        this.locks = locks;
    }

    public HashMap<Location, Material> getMaterialMap() {
        return materialMap;
    }

    public void setMaterialMap(HashMap<Location, Material> materialMap) {
        this.materialMap = materialMap;
    }
}
