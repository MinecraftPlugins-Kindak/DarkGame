package se.kindak.darkgame.game.util;

import org.bukkit.configuration.file.FileConfiguration;
import se.kindak.darkgame.game.dungeon.DungeonArena;
import se.kindak.darkgame.game.sign.DungeonSign;
import se.kindak.darkgame.game.sign.util.SignLayout;

import java.util.HashSet;
import java.util.Set;

public class Category {
    private String category;
    private Set<DungeonArena> arenas;
    private Set<DungeonSign> signs;
    private SignLayout signLayout;

    public Category(FileConfiguration configuration, String path) {
        this.category = configuration.getString(path + ".Category");
        this.arenas = new HashSet<>();
        this.signs = new HashSet<>();
        this.signLayout = new SignLayout(configuration, path);
    }

    public Category(String category) {
        this.category = category;
        this.arenas = new HashSet<>();
        this.signs = new HashSet<>();
        this.signLayout = new SignLayout();
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<DungeonArena> getArenas() {
        return arenas;
    }

    public void setArenas(Set<DungeonArena> arenas) {
        this.arenas = arenas;
    }

    public Set<DungeonSign> getSigns() {
        return signs;
    }

    public void setSigns(Set<DungeonSign> signs) {
        this.signs = signs;
    }

    public SignLayout getSignLayout() {
        return signLayout;
    }

    public void setSignLayout(SignLayout signLayout) {
        this.signLayout = signLayout;
    }
}
