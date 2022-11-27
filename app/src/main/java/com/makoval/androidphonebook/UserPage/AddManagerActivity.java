package com.makoval.androidphonebook.UserPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.makoval.androidphonebook.MainActivity;
import com.makoval.androidphonebook.R;
import com.makoval.androidphonebook.Users.DataUsers;
import com.makoval.androidphonebook.Users.Manager;
import com.makoval.androidphonebook.Users.User;

/**
 * Активность добавления нового пользователя с типом данных Manager
 */
public class AddManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.users_menu, menu);
        return true;
    }


    /**
     * обавление менеджера в список
     */
    private void addUser() {
        String name = ((EditText) findViewById(R.id.name)).getText().toString();
        String surname = ((EditText) findViewById(R.id.surname)).getText().toString();
        String position = ((EditText) findViewById(R.id.position)).getText().toString();
        String address = ((EditText) findViewById(R.id.address)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
        User newUser = new Manager(DataUsers.getSize() + 1, "manager", phone, address, name,
                surname, position);
        DataUsers.addUser(newUser);
    }


    /**
     * Обробатывает нажатия на кнопки меню:
     * Добавить пользователя (переход на страницу добавления пользователя)
     * Отменить добавление пользователя (возврат к списку пользователей)
     *
     * @param item Элементы меню
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent addManager = new Intent(getApplicationContext(), MainActivity.class);
                addUser();
                addManager.putExtra("@action", "add");
                startActivity(addManager);
                return true;
            case R.id.action_cancel:
                Intent cancelManager = new Intent(getApplicationContext(), MainActivity.class);
                cancelManager.putExtra("@action", "cancel");
                startActivity(cancelManager);
                return true;
            default:
                return true;
        }
    }

}