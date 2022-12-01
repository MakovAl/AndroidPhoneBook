package com.makoval.androidphonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.makoval.androidphonebook.Adapter.PhoneBookAdapter;
import com.makoval.androidphonebook.DBHelper.DBHelper;
import com.makoval.androidphonebook.Reader.IReader;
import com.makoval.androidphonebook.Reader.ReadFromDB;
import com.makoval.androidphonebook.Reader.ReadFromFile;
import com.makoval.androidphonebook.Reader.ReadFromJson;
import com.makoval.androidphonebook.UserPage.AddUserActivity;
import com.makoval.androidphonebook.Users.DataUsers;
import com.makoval.androidphonebook.Users.Developer;
import com.makoval.androidphonebook.Users.IOUser;
import com.makoval.androidphonebook.Users.Manager;
import com.makoval.androidphonebook.Writer.IWriter;
import com.makoval.androidphonebook.Writer.WriteToDB;
import com.makoval.androidphonebook.Writer.WriteToFile;
import com.makoval.androidphonebook.Writer.WriteToJson;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    /**
     * Получение данных для заполнения списка
     */
    private void readData() {
        if (DataUsers.getUsersList().isEmpty()) {
            try {
                DataUsers.setUsersList(IOUser.getReader().read());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Создает адаптер и устанавливает отображение для выведение списка пользователей
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView mainList = (ListView) findViewById(R.id.main_list);

        IOUser.setWriter(new WriteToDB(this));
        IOUser.setReader(new ReadFromDB(this));

        readData();

        PhoneBookAdapter phoneAdapter = new PhoneBookAdapter(this, DataUsers.getUsersList());
        mainList.setAdapter(phoneAdapter);
    }


    /**
     * При возвращении на основной экран в зависимости от выполненого действия на прошлом экране
     * выполняется либо удаление пользователя из списка, либо добавление пользователя в список
     * сохраненый на устройстве, либо изменение сохраненных данных.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Intent dataUser = getIntent();
        String actionUser = dataUser.getStringExtra("@action");
        if (actionUser != null && !actionUser.equals("cancel")) {
            try {
                IOUser.getWriter().write(dataUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            default:
                return true;
        }
    }

}
