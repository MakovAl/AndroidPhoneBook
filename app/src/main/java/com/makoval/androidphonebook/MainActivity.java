package com.makoval.androidphonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.makoval.androidphonebook.Adapter.PhoneBookAdapter;
import com.makoval.androidphonebook.Reader.IReader;
import com.makoval.androidphonebook.Reader.ReadFromJson;
import com.makoval.androidphonebook.UserPage.AddUserActivity;
import com.makoval.androidphonebook.Users.DataUsers;
import com.makoval.androidphonebook.Writer.IWriter;
import com.makoval.androidphonebook.Writer.WriteToJson;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    IWriter writer;
    IReader reader;


    /**
     * Получение данных для заполнения списка
     */
    private void readData() {
        if (DataUsers.getUsersList().isEmpty()) {
            reader = new ReadFromJson("data.txt", this);
            try {
                DataUsers.setUsersList(reader.read());
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
        readData();
        PhoneBookAdapter phoneAdapter = new PhoneBookAdapter(this, DataUsers.getUsersList());
        mainList.setAdapter(phoneAdapter);
    }


    /**
     * Удаляет пользователя из списка по позиции переданой через намерение
     *
     * @param dataUser Данные полученые через намерение
     * @throws IOException
     */
    private void delUser(Intent dataUser) throws IOException {
        int position = dataUser.getIntExtra("@position", -1);
        DataUsers.delUser(position);
    }

    /**
     * При возвращении на основной экран в зависимости от выполненого действия на прошлом экране
     * выполняется либо удаление пользователя из списка либо добавление пользователя в список сохраненый в файл на устройстве
     */
    @Override
    protected void onResume() {
        super.onResume();
        Intent dataUser = getIntent();
        String actionUser = dataUser.getStringExtra("@action");
        if (actionUser != null) {
            writer = new WriteToJson("data.txt", DataUsers.getUsersList(), this);
            try {
                if (dataUser.getStringExtra("@action").equals("del")) {
                    delUser(dataUser);
                }
                writer.write();
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
