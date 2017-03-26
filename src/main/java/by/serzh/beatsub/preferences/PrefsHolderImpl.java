package by.serzh.beatsub.preferences;

import by.serzh.beatsub.ui.Dimens;
import by.serzh.beatsub.ui.Main;

import java.util.prefs.Preferences;

public class PrefsHolderImpl implements PrefsHolder {
    private static final String KEY_DIMENS_WIDTH = "DWIDTH_";
    private static final String KEY_DIMENS_HEIGHT = "DHEIGHT_";

    private final Preferences prefs;

    public PrefsHolderImpl() {
        prefs = Preferences.userNodeForPackage(Main.class);
    }

    @Override
    public void saveDimens(String key, Dimens dimens) {
        prefs.putDouble(KEY_DIMENS_WIDTH + key, dimens.getWidth());
        prefs.putDouble(KEY_DIMENS_HEIGHT + key, dimens.getHeight());
    }

    @Override
    public Dimens readDimens(String key) {
        double width = prefs.getDouble(KEY_DIMENS_WIDTH + key, 800);
        double height = prefs.getDouble(KEY_DIMENS_HEIGHT + key, 600);
        return new Dimens(width, height);
    }
}
