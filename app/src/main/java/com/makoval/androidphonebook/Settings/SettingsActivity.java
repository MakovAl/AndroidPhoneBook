package com.makoval.androidphonebook.Settings;

import androidx.appcompat.app.AppCompatActivity;;

import android.content.Intent;
import android.os.Bundle;

import com.makoval.androidphonebook.R;
import com.makoval.androidphonebook.StartScreenActivity;

/**
 * Установливает экран настроек
 */
public class SettingsActivity extends AppCompatActivity {
    public SettingsActivity() {
        super(R.layout.activity_settings);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.settings_container, SettingsFragment.class, null)
                    .commit();
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), StartScreenActivity.class);
        this.startActivity(intent);
    }
}
