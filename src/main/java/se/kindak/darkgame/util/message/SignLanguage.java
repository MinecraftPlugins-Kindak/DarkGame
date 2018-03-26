package se.kindak.darkgame.util.message;

public class SignLanguage {
    private static SignLanguage instance;
    public final String INVALID_SIGN;

    public SignLanguage(String INVALID_SIGN) {
        this.INVALID_SIGN = INVALID_SIGN;
        instance = this;
    }

    public static SignLanguage getInstance() {
        return instance;
    }
}
