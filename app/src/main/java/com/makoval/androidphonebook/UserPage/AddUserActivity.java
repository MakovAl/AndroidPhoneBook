package com.makoval.androidphonebook.UserPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.makoval.androidphonebook.R;

/**
 * Активность с двумя кнопками для выбора типа пользователя которого нужно добавить
 */
public class AddUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Button addDeveloperButton = findViewById(R.id.developer_button);
        Button addManagerButton = findViewById(R.id.manager_button);

        addDeveloperButton.setOnClickListener(view -> {
            Intent addDeveloper = new Intent(getApplicationContext(), AddDeveloperActivity.class);
            startActivity(addDeveloper);
        });

        addManagerButton.setOnClickListener(view -> {
            Intent addManager = new Intent(getApplicationContext(), AddManagerActivity.class);
            startActivity(addManager);
        });

    }
}