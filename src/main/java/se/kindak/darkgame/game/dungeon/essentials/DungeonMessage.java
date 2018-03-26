package se.kindak.darkgame.game.dungeon.essentials;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import se.kindak.darkgame.DarkGameMain;
import se.kindak.darkgame.game.dungeon.DungeonArena;
import se.kindak.kindaklib.location.LocationFormater;

import java.util.List;

// Done

public class DungeonMessage extends BukkitRunnable {
    int delay = 0;
    private int id;
    private boolean sendAtStart;
    private boolean spawnHologram;
    private boolean sendMessage;
    private List<String> messages;
    private Hologram hologram;
    private DungeonArena arena;
    private int currentMessage = 0;

    public DungeonMessage(int id, FileConfiguration configuration, DungeonArena arena) {
        this.id = id;
        messages = configuration.getStringList(id + ".Messages");
        this.sendAtStart = configuration.getBoolean(id + ".Run_At_Start");
        this.spawnHologram = configuration.getBoolean(id + "Hologram.spawn");
        this.sendMessage = configuration.getBoolean(id + ".Message_Players");
        if (spawnHologram)
            this.hologram = HologramsAPI.createHologram(DarkGameMain.getInstance(), LocationFormater.format(configuration.getString(id + ".Hologram.location")));
        this.arena = arena;
    }

    @Override
    public void run() {
        if (currentMessage > messages.size() - 1) {
            cancel();
            return;
        }
        String message = messages.get(currentMessage);

        if (delay == 0) {
            if (message.split(":")[0].equalsIgnoreCase("delay")) {
                delay = Integer.parseInt(message.split(":")[1]);
                return;
            }
            if (sendMessage)
                arena.broadcast(this.messages.get(currentMessage));
            if (spawnHologram)
                hologram.appendTextLine(messages.get(this.currentMessage));
            currentMessage++;
        } else
            delay--;
    }

    public boolean shouldSendAtStart() {
        return sendAtStart;
    }

    public void setSendAtStart(boolean sendAtStart) {
        this.sendAtStart = sendAtStart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Hologram getHologram() {
        return hologram;
    }

    public void setHologram(Hologram hologram) {
        this.hologram = hologram;
    }
}
