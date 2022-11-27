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
import com.makoval.androidphonebook.Users.Developer;
import com.makoval.androidphonebook.Users.User;

/**
 * Активность добавления нового пользователя с типом данных Developer
 */
public class AddDeveloperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_developer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.users_menu, menu);
        return true;
    }


    /**
     * Добавление разработчика в список
     */
    private void addUser() {
        String name = ((EditText) findViewById(R.id.name)).getText().toString();
        String surname = ((EditText) findViewById(R.id.surname)).getText().toString();
        String position = ((EditText) findViewById(R.id.position)).getText().toString();
        String address = ((EditText) findViewById(R.id.address)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
        String programmingLanguages = ((EditText) findViewById(R.id.programmingLanguages)).getText().toString();
        User newUser = new Developer(DataUsers.getSize() + 1, "developer", phone, address, name,
                surname, position, programmingLanguages);
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
                Intent addDeveloper = new Intent(getApplicationContext(), MainActivity.class);
                addUser();
                addDeveloper.putExtra("@action", "add");
                startActivity(addDeveloper);
                return true;
            case R.id.action_cancel:
                Intent cancelDeveloper = new Intent(getApplicationContext(), MainActivity.class);
                cancelDeveloper.putExtra("@action", "cancel");
                startActivity(cancelDeveloper);
                return true;
            default:
                return true;
        }
    }


}