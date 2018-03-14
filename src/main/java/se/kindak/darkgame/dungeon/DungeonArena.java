package se.kindak.darkgame.dungeon;

import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import se.kindak.darkgame.dungeon.essentials.Gate;
import se.kindak.darkgame.dungeon.essentials.MobPack;
import se.kindak.darkgame.dungeon.essentials.PrizePool;
import se.kindak.darkgame.dungeon.util.GameState;
import se.kindak.darkgame.playerdata.PlayerData;
import se.kindak.kindaklib.location.LocationFormater;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class DungeonArena {
    //basic info
    private final String name;
    private final String category;
    //Players
    private final int maxPlayers;
    //Locations
    private final Location spawnPoint;
    private final Location deathPoint;
    private HashSet<PlayerData> players;
    //State
    private GameState state;
    //Essentials
    private PrizePool prizePool;
    private Set<MobPack> mobPack;
    private Set<Gate> gates;
    //File managment
    private File folder;
    private FileConfiguration gateC, prizeC, mobC, basicC;

    public DungeonArena(File folder) {
        this.state = GameState.LOADING;
        this.setFolder(folder);
        loadConfigs();
        this.name = this.getBasicC().getString("Name");
        this.category = this.getBasicC().getString("Category");
        this.maxPlayers = this.getBasicC().getInt("Max_Players");

        this.spawnPoint = LocationFormater.format(this.getBasicC().getString("Spawn_Point"));
        this.deathPoint = LocationFormater.format(this.getBasicC().getString("Death_Point"));

        this.loadMobs();
        this.loadGates();
        this.loadPrizes();
        this.state = GameState.FINDING_SIGN;
    }

    private void loadPrizes() {

    }

    private void loadGates() {

    }

    private void loadMobs() {

    }

    public void loadConfigs() {
        this.gateC = new YamlConfiguration();
        this.prizeC = new YamlConfiguration();
        this.mobC = new YamlConfiguration();
        this.basicC = new YamlConfiguration();

        try {
            gateC.load(new File(this.getFolder().getParent() + File.separator + "Gates.yml"));
            prizeC.load(new File(this.getFolder().getParent() + File.separator + "Prizes.yml"));
            mobC.load(new File(this.getFolder().getParent() + File.separator + "Mobs.yml"));
            basicC.load(new File(this.getFolder().getParent() + File.separator + "Basic.yml"));
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public File getFolder() {

        return folder;
    }

    public void setFolder(File folder) {
        this.folder = folder;
    }

    public FileConfiguration getGateC() {
        return gateC;
    }

    public void setGateC(FileConfiguration gateC) {
        this.gateC = gateC;
    }

    public FileConfiguration getPrizeC() {
        return prizeC;
    }

    public void setPrizeC(FileConfiguration prizeC) {
        this.prizeC = prizeC;
    }

    public FileConfiguration getMobC() {
        return mobC;
    }

    public void setMobC(FileConfiguration mobC) {
        this.mobC = mobC;
    }

    public FileConfiguration getBasicC() {
        return basicC;
    }

    public void setBasicC(FileConfiguration basicC) {
        this.basicC = basicC;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public HashSet<PlayerData> getPlayers() {
        return players;
    }

    public void setPlayers(HashSet<PlayerData> players) {
        this.players = players;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Location getSpawnPoint() {
        return spawnPoint;
    }

    public Location getDeathPoint() {
        return deathPoint;
    }

    public PrizePool getPrizePool() {
        return prizePool;
    }

    public void setPrizePool(PrizePool prizePool) {
        this.prizePool = prizePool;
    }

    public Set<MobPack> getMobPack() {
        return mobPack;
    }

    public void setMobPack(Set<MobPack> mobPack) {
        this.mobPack = mobPack;
    }

    public Set<Gate> getGates() {
        return gates;
    }

    public void setGates(Set<Gate> gates) {
        this.gates = gates;
    }

    public Gate getGate(int id) {
        return (Gate) this.gates.stream().filter(gate -> gate.getId() == id).toArray()[0];
    }
}
