package com.makoval.androidphonebook.UserPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.makoval.androidphonebook.R;
import com.makoval.androidphonebook.Users.DataUsers;
import com.makoval.androidphonebook.Users.Manager;
import com.makoval.androidphonebook.Users.User;

public class EditManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_manager);
        Intent dataManager = getIntent();
        int position = dataManager.getIntExtra("@position", -1);

        Manager dev = (Manager) DataUsers.getUsersList().get(position);

        ((EditText) findViewById(R.id.name)).setText(dev.getName());
        ((EditText) findViewById(R.id.surname)).setText(dev.getSurname());
        ((EditText) findViewById(R.id.address)).setText(dev.getAddress());
        ((EditText) findViewById(R.id.position)).setText(dev.getPosition());
        ((EditText) findViewById(R.id.phone)).setText(dev.getUserPhoneNumber());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_user_menu, menu);
        return true;
    }


    /**
     * Добавление менеджера в список
     */
    private void editUser(int listPosition) {
        User user = DataUsers.getUsersList().get(listPosition);
        String name = ((EditText) findViewById(R.id.name)).getText().toString();
        String surname = ((EditText) findViewById(R.id.surname)).getText().toString();
        String position = ((EditText) findViewById(R.id.position)).getText().toString();
        String address = ((EditText) findViewById(R.id.address)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
        User editUser = new Manager(user.getId(), "manager", phone, address, name,
                surname, position);
        DataUsers.replaceUser(listPosition, editUser);
    }


    /**
     * Обробатывает нажатия на кнопки меню:
     * Сохранить изменения менеджера (возврат на страницу просмотра данных менеджера с
     * сохранением изменений)
     * Отменить изменения менеджера (возврат к списку менеджера без сохранения изменений)
     *
     * @param item Элементы меню
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent dataManager = getIntent();
        int listPosition = dataManager.getIntExtra("@position", -1);
        switch (item.getItemId()) {
            case R.id.action_save:
                Intent editActivity = new Intent(getApplicationContext(), ManagerPageActivity.class);
                editUser(listPosition);
                editActivity.putExtra("@action", "edit");
                editActivity.putExtra("@position", listPosition);
                startActivity(editActivity);
                return true;
            case R.id.action_cancel:
                Intent cancelManager = new Intent(getApplicationContext(), ManagerPageActivity.class);
                cancelManager.putExtra("@action", "cancel");
                cancelManager.putExtra("@position", listPosition);
                startActivity(cancelManager);
                return true;
            default:
                return true;
        }
    }
}
