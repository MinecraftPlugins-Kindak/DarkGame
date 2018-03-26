package se.kindak.darkgame.game.sign;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import se.kindak.darkgame.DarkGameMain;
import se.kindak.darkgame.game.GameHandler;
import se.kindak.darkgame.game.dungeon.DungeonArena;
import se.kindak.darkgame.game.util.Category;
import se.kindak.darkgame.util.message.SignLanguage;
import se.kindak.kindaklib.location.LocationFormater;

public class DungeonSign {
    private Sign sign;
    private Category category;
    private DungeonArena currentArena;

    public DungeonSign(FileConfiguration configuration, String path) {
        Location signLocation = LocationFormater.format(configuration.getString(path + ".Location"));
        if (!(signLocation.getBlock() instanceof Sign)) {
            DarkGameMain.getInstance().log(SignLanguage.getInstance().INVALID_SIGN);
            return;
        }
        this.sign = (Sign) signLocation.getBlock();
        String categoryName = configuration.getString(path + ".Category");
        if (GameHandler.getInstance().getCategory(categoryName) == null) {
            Category category = new Category(categoryName);
        }
    }
}
