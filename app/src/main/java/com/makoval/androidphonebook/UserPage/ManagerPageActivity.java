package com.makoval.androidphonebook.UserPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.makoval.androidphonebook.MainActivity;
import com.makoval.androidphonebook.R;
import com.makoval.androidphonebook.Users.DataUsers;
import com.makoval.androidphonebook.Users.Manager;

/**
 * Активность отображающая страницу информации о выбранном менеджере
 */
public class ManagerPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_page);

        Intent dataDeveloper = getIntent();
        int position = dataDeveloper.getIntExtra("@position", -1);

        Manager manager = (Manager) DataUsers.getUsersList().get(position);

        ((TextView)findViewById(R.id.name)).setText(manager.getName());
        ((TextView)findViewById(R.id.surname)).setText(manager.getSurname());
        ((TextView)findViewById(R.id.address)).setText(manager.getAddress());
        ((TextView)findViewById(R.id.position)).setText(manager.getPosition());
        ((TextView)findViewById(R.id.phone)).setText(manager.getUserPhoneNumber());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_page_menu, menu);
        return true;
    }

    /**
     * Обробатывает нажатия на кнопки меню:
     * Удалить менеджера
     * Отменить закрыть страницу с информацией (возврат к списку пользователей)
     *
     * @param item Элементы меню
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                return true;
            case R.id.action_del:
                Intent dataDeveloper = getIntent();
                Intent delDeveloper = new Intent(getApplicationContext(), MainActivity.class);
                delDeveloper.putExtra("@action", "del");
                delDeveloper.putExtra("@position", dataDeveloper.getIntExtra("@position", -1));
                startActivity(delDeveloper);
                return true;
            case R.id.action_save:
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