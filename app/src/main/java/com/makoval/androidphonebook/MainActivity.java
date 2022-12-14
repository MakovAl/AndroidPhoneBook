package com.makoval.androidphonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.makoval.androidphonebook.Adapter.PhoneBookAdapter;
import com.makoval.androidphonebook.Settings.SettingsActivity;
import com.makoval.androidphonebook.UserPage.AddUserActivity;
import com.makoval.androidphonebook.Users.DataUsers;
import com.makoval.androidphonebook.Users.IOUser;

import java.io.IOException;

/**
 * Основная активность выводящая список контактов
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Создает адаптер и устанавливает отображение для выведение списка пользователей
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * При возвращении на основной экран в зависимости от выполненого действия на прошлом экране
     * выполняется либо удаление пользователя из списка, либо добавление пользователя в список
     * сохраненый на устройстве, либо изменение сохраненных данных.
     */
    @Override
    protected void onResume() {
        super.onResume();

        ListView mainList = (ListView) findViewById(R.id.main_list);
        PhoneBookAdapter phoneAdapter = new PhoneBookAdapter(this, DataUsers.getUsersList());
        mainList.setAdapter(phoneAdapter);

        Intent dataUser = getIntent();
        String actionUser = dataUser.getStringExtra("@action");
        if (actionUser != null && !actionUser.equals("cancel")) {
            try {
                IOUser.getWriter().write(dataUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
            dataUser.putExtra("@action", "cancel");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * Обработка нажатий на кнопку добавление пользователя
     *
     * @param item Элементы меню
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent addUserIntent = new Intent(getApplicationContext(), AddUserActivity.class);
                startActivity(addUserIntent);
                return true;
            case R.id.action_settings:
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return true;
        }
    }
}
