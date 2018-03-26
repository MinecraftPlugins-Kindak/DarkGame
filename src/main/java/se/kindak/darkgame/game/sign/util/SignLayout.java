package se.kindak.darkgame.game.sign.util;

import org.bukkit.configuration.file.FileConfiguration;

public class SignLayout {
    private SignLines
            lineOne = SignLines.LINE_ONE,
            lineTwo = SignLines.LINE_TWO,
            lineThree = SignLines.LINE_THREE,
            lineFour = SignLines.LINE_FOUR;

    public SignLayout() {
    }

    public SignLayout(FileConfiguration configuration, String path) {
        this.lineOne.setLineString(configuration.getString(path + ".SignLines.LINE_ONE"));
        this.lineTwo.setLineString(configuration.getString(path + ".SignLines.LINE_TWO"));
        this.lineThree.setLineString(configuration.getString(path + ".SignLines.LINE_THREE"));
        this.lineFour.setLineString(configuration.getString(path + ".SignLines.LINE_FOUR"));
    }

    //Getters & Setters
    public SignLines getLineOne() {
        return lineOne;
    }

    public void setLineOne(SignLines lineOne) {
        this.lineOne = lineOne;
    }

    public SignLines getLineTwo() {
        return lineTwo;
    }

    public void setLineTwo(SignLines lineTwo) {
        this.lineTwo = lineTwo;
    }

    public SignLines getLineThree() {
        return lineThree;
    }

    public void setLineThree(SignLines lineThree) {
        this.lineThree = lineThree;
    }

    public SignLines getLineFour() {
        return lineFour;
    }

    public void setLineFour(SignLines lineFour) {
        this.lineFour = lineFour;
    }
}
