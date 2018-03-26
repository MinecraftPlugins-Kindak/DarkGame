package se.kindak.darkgame.game;

import se.kindak.darkgame.game.util.Category;

import java.util.Set;

public class GameHandler {
    private static GameHandler instance;
    private Set<Category> categories;

    public GameHandler(Set<Category> categories) {
        this.categories = categories;

    }

    public static GameHandler getInstance() {
        return instance;
    }

    public Category getCategory(String category) {
        try {
            return (Category) categories.stream().filter(category1 -> category1.getCategory().equalsIgnoreCase(category)).toArray()[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }


    // Getters & Setters
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
