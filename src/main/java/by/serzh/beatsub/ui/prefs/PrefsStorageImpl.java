package by.serzh.beatsub.ui.prefs;

import by.serzh.beatsub.ui.Main;

import java.util.prefs.Preferences;

public class PrefsStorageImpl implements PrefsStorage {

    private static final String SELECTED_SERVER = "selected_server_id";

    @Override
    public void setSelectedServerId(Integer id) {
        getPrefs().putInt(SELECTED_SERVER, id);
    }

    @Override
    public Integer getSelectedServerId() {
        Integer value = getPrefs().getInt(SELECTED_SERVER, -1);
        return value >= 0 ? value : null;
    }

    private static Preferences getPrefs() {
        return Preferences.userNodeForPackage(Main.class);
    }
}
