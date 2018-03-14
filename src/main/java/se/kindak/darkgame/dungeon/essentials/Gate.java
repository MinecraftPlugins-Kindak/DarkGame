package se.kindak.darkgame.dungeon.essentials;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import se.kindak.kindaklib.location.Cuboid;

import java.util.HashMap;

public class Gate extends Cuboid {
    private final int id;
    private final int MAX_LOCKS;
    public boolean isOpen;
    private int locks;
    private HashMap<Location, Material> materialMap;

    public Gate(Location locationOne, Location locationTwo, int id, int MAX_LOCKS) {
        super(locationOne, locationTwo);
        this.id = id;
        this.MAX_LOCKS = MAX_LOCKS;
        this.locks = MAX_LOCKS;
        this.materialMap = new HashMap<>();
    }

    public boolean open(boolean force) {
        if (isOpen)
            return false;
        if (force == false && locks > 0)
            return false;
        World w = this.getLocationOne().getWorld();
        for (int x = this.getMinPoints()[0]; x < this.getMaxPoints()[0]; x++) {
            for (int y = this.getMinPoints()[1]; y < this.getMaxPoints()[1]; y++) {
                for (int z = this.getMinPoints()[2]; z < this.getMaxPoints()[2]; z++) {
                    materialMap.put(new Location(w, x, y, z), w.getBlockAt(x, y, z).getType());
                    w.getBlockAt(x, y, z).setType(Material.AIR);
                }
            }
        }

        this.locks = 0;
        this.isOpen = true;
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
