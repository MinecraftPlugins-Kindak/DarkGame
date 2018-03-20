package se.kindak.darkgame.dungeon;

import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import se.kindak.darkgame.dungeon.essentials.*;
import se.kindak.darkgame.dungeon.util.GameState;
import se.kindak.darkgame.playerdata.PlayerData;
import se.kindak.darkgame.playerdata.util.Party;
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
    private final int MAX_PLAYERS;
    private final int MIN_PLAYERS;
    //Locations
    private final Location spawnPoint;
    private final Location deathPoint;
    private HashSet<PlayerData> players;
    //State
    private GameState state;
    //Essentials
    private PrizePool prizePool;
    private Gate firstGate;
    private Set<MobPack> mobPacks;
    private Set<Gate> gates;
    private Set<DungeonMessage> dungeonMessages;
    private Set<BlockTrigger> blockTriggers;
    //File managment
    private File folder;
    private FileConfiguration gateC, prizeC, mobC, basicC;

    public DungeonArena(File folder) {
        this.state = GameState.LOADING;
        this.setFolder(folder);
        loadConfigs();
        this.name = this.getBasicC().getString("Name");
        this.category = this.getBasicC().getString("Category");

        this.MAX_PLAYERS = this.getBasicC().getInt("Max_Players");
        this.MIN_PLAYERS = this.getBasicC().getInt("Min_Players");

        this.spawnPoint = LocationFormater.format(this.getBasicC().getString("Spawn_Point"));
        this.deathPoint = LocationFormater.format(this.getBasicC().getString("Death_Point"));

        this.loadMobs();
        this.loadGates();
        this.loadPrizes();
        this.loadMessages();
        this.state = GameState.FINDING_SIGN;
    }

    // Load up
    private void loadPrizes() {
        this.prizePool = new PrizePool(prizeC);
    }

    private void loadGates() {
        this.gates = new HashSet<>();
    }

    private void loadMobs() {
        this.mobPacks = new HashSet<>();
    }

    private void loadMessages() {
        this.dungeonMessages = new HashSet<>();
    }

    private void loadConfigs() {
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

    // Player Managment
    public boolean addParty(Party party) {
        if (players.containsAll(party.getPlayers()))
            return false;
        if (party.getPartySize() + players.size() > MAX_PLAYERS)
            return false;


        return players.addAll(party.getPlayers());
    }

    public boolean removePlayer(PlayerData playerData) {
        return players.remove(playerData);
    }

    public void broadcast(String message) {
        for (PlayerData player : players)
            player.msg(message);
    }

    // Dungeon Managment


    // Returners
    public Gate getGate(int id) {
        try {
            return (Gate) this.gates.stream().filter(gate -> gate.getId() == id).toArray()[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public MobPack getMobPack(int id) {
        try {
            return (MobPack) this.mobPacks.stream().filter(mobPack -> mobPack.getId() == id).toArray()[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public DungeonMessage getMessage(int id) {
        try {
            return (DungeonMessage) this.dungeonMessages.stream().filter(dungeonMessage -> dungeonMessage.getId() == id).toArray()[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public BlockTrigger getBlockTrigger(int id) {
        try {
            return (BlockTrigger) this.blockTriggers.stream().filter(blockTrigger -> blockTrigger.getId() == id).toArray()[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }


    // Getters & Setters
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

    public int getMAX_PLAYERS() {
        return MAX_PLAYERS;
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

    public Set<MobPack> getMobPacks() {
        return mobPacks;
    }

    public void setMobPacks(Set<MobPack> mobPacks) {
        this.mobPacks = mobPacks;
    }

    public Set<Gate> getGates() {
        return gates;
    }

    public void setGates(Set<Gate> gates) {
        this.gates = gates;
    }

    public int getMIN_PLAYERS() {
        return MIN_PLAYERS;
    }

    public Set<DungeonMessage> getDungeonMessages() {
        return dungeonMessages;
    }

    public void setDungeonMessages(Set<DungeonMessage> dungeonMessages) {
        this.dungeonMessages = dungeonMessages;
    }

    public Gate getFirstGate() {
        return firstGate;
    }

    public void setFirstGate(Gate firstGate) {
        this.firstGate = firstGate;
    }

}
