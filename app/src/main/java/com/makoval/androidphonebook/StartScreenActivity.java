package com.makoval.androidphonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.preference.PreferenceManager;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.makoval.androidphonebook.Formatter.UserList;
import com.makoval.androidphonebook.Reader.IReader;
import com.makoval.androidphonebook.Reader.ReadFromDB;
import com.makoval.androidphonebook.Reader.ReadFromFile;
import com.makoval.androidphonebook.Reader.ReadFromJson;
import com.makoval.androidphonebook.Users.DataUsers;
import com.makoval.androidphonebook.Users.IOUser;
import com.makoval.androidphonebook.Writer.IWriter;
import com.makoval.androidphonebook.Writer.WriteToDB;
import com.makoval.androidphonebook.Writer.WriteToFile;
import com.makoval.androidphonebook.Writer.WriteToJson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс вызываемый при открытии приложения
 */
public class StartScreenActivity extends AppCompatActivity {

    /**
     * Получение данных для заполнения списка
     */
    private UserList readData(IReader reader) {
        UserList userList = new UserList();
        try {
            userList = reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }


    /**
     * Устанавливает настройки приложения полученые из SharedPreferences
     * Настраивает где будут храниться данные пользователя и в каком типе сохраняются перед отправкой
     */
    private void setSetting() {
        Map<String, IWriter> writers = new HashMap<>();
        writers.put("DB", new WriteToDB(this));
        writers.put("File", new WriteToFile("data.txt", this));
        writers.put("Json", new WriteToJson("data.json", this));

        Map<String, IReader> readers = new HashMap<>();
        readers.put("DB", new ReadFromDB(this));
        readers.put("File", new ReadFromFile("data.txt", this));
        readers.put("Json", new ReadFromJson("data.json", this));

        Map<String, IWriter> writersSend = new HashMap<>();
        writersSend.put("File", new WriteToFile("contact_send.txt", this));
        writersSend.put("Json", new WriteToJson("contact_send.json", this));

        Map<String, String> nameFile = new HashMap<>();
        nameFile.put("File", "contact_send.txt");
        nameFile.put("Json", "contact_send.json");

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        IOUser.setWriter(writers.get(sharedPreferences.getString("listMethods", "DB")));
        IOUser.setReader(readers.get(sharedPreferences.getString("listMethods", "DB")));
        IOUser.setWriterSend(writersSend.get(sharedPreferences.getString("listFormat", "Json")));
        IOUser.setFileSend(nameFile.get(sharedPreferences.getString("listFormat", "Json")));
    }


    /**
     * Считывает в список данные из хранилища и если активность была вызвана получением файла,
     * то добавляет в список считаные данные.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR
        }, 1);

        setSetting();

        DataUsers.setUsersList(readData(IOUser.getReader()));

        Intent main = new Intent(getApplicationContext(), MainActivity.class);
        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            IOUser.setReaderSend("application/octet-stream", new ReadFromJson(uri, this));
            IOUser.setReaderSend("application/*", new ReadFromJson(uri, this));
            IOUser.setReaderSend("text/plain", new ReadFromFile(uri, this));

            DataUsers.addUser(readData(IOUser.getReaderSend(intent.getType())).getLast());
            main.putExtra("@action", "add");
        } else {
            main.putExtra("@action", "cancel");
        }

        this.startActivity(main);
        finish();

    }
}
