package com.makoval.androidphonebook.UserPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.makoval.androidphonebook.R;
import com.makoval.androidphonebook.Users.DataUsers;
import com.makoval.androidphonebook.Users.Developer;
import com.makoval.androidphonebook.Users.User;

public class EditDeveloperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_developer);
        Intent dataDeveloper = getIntent();
        int position = dataDeveloper.getIntExtra("@position", -1);

        Developer dev = (Developer) DataUsers.getUsersList().get(position);

        ((EditText) findViewById(R.id.name)).setText(dev.getName());
        ((EditText) findViewById(R.id.surname)).setText(dev.getSurname());
        ((EditText) findViewById(R.id.address)).setText(dev.getAddress());
        ((EditText) findViewById(R.id.position)).setText(dev.getPosition());
        ((EditText) findViewById(R.id.programmingLanguages)).setText(dev.getProgrammingLanguages());
        ((EditText) findViewById(R.id.phone)).setText(dev.getUserPhoneNumber());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_user_menu, menu);
        return true;
    }


    /**
     * Изменение выбраного разработчика из списка
     */
    private void editUser(int listPosition) {
        User user = DataUsers.getUsersList().get(listPosition);
        String name = ((EditText) findViewById(R.id.name)).getText().toString();
        String surname = ((EditText) findViewById(R.id.surname)).getText().toString();
        String position = ((EditText) findViewById(R.id.position)).getText().toString();
        String address = ((EditText) findViewById(R.id.address)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
        String programmingLanguages = ((EditText) findViewById(R.id.programmingLanguages)).getText().toString();
        User editUser = new Developer(user.getId(), "developer", phone, address, name,
                surname, position, programmingLanguages);
        DataUsers.replaceUser(listPosition, editUser);
    }


    /**
     * Обробатывает нажатия на кнопки меню:
     * Сохранить изменения разработчика (возврат на страницу просмотра данных разработчика с
     * сохранением изменений)
     * Отменить изменения разработчика (возврат к списку разработчика без сохранения изменений)
     *
     * @param item Элементы меню
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent dataDeveloper = getIntent();
        int listPosition = dataDeveloper.getIntExtra("@position", -1);
        switch (item.getItemId()) {
            case R.id.action_save:
                Intent editActivity = new Intent(getApplicationContext(), DeveloperPageActivity.class);
                editUser(listPosition);
                editActivity.putExtra("@action", "edit");
                editActivity.putExtra("@position", listPosition);
                startActivity(editActivity);
                return true;
            case R.id.action_cancel:
                Intent cancelDeveloper = new Intent(getApplicationContext(), DeveloperPageActivity.class);
                cancelDeveloper.putExtra("@action", "cancel");
                cancelDeveloper.putExtra("@position", listPosition);
                startActivity(cancelDeveloper);
                return true;
            default:
                return true;
        }
    }
}
