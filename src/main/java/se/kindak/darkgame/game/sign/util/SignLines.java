package se.kindak.darkgame.game.sign.util;

public enum SignLines {
    LINE_ONE("[DARKGAME]"), LINE_TWO("%ARENA%"), LINE_THREE("%CURRENT_PLAYERS% / %MAX_PLAYERS%"), LINE_FOUR("%CATEGORY%");

    private String lineString;

    SignLines(String lineString) {
        this.lineString = lineString;
    }

    public String getLineString() {
        return lineString;
    }

    public void setLineString(String lineString) {
        this.lineString = lineString;
    }
}
