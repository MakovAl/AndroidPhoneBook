package com.makoval.androidphonebook.Settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.makoval.androidphonebook.R;

/**
 * Содержит экран настроек
 */
public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference_screen, rootKey);
    }
}
