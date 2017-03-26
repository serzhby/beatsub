package by.serzh.beatsub.preferences;

import by.serzh.beatsub.ui.Dimens;

public interface PrefsHolder {
    void saveDimens(String key, Dimens dimens);
    Dimens readDimens(String key);
}
