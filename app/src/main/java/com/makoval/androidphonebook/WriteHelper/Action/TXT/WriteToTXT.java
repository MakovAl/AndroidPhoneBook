package com.makoval.androidphonebook.WriteHelper.Action.TXT;

import android.content.Context;

import com.makoval.androidphonebook.Formatter.UserList;
import com.makoval.androidphonebook.Users.DataUsers;
import com.makoval.androidphonebook.Users.User;
import com.makoval.androidphonebook.WriteHelper.IWriteHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Является реализации интерфейса IWriteHelper
 * Выполняет действие записи пользователя в txt файл
 */
public class WriteToTXT implements IWriteHelper {

    /**
     * Контекст приложения
     */
    private Context context;
    /**
     * Путь к файлу
     */
    private String path;


    public WriteToTXT(Context context, String path) {
        this.path = path;
        this.context = context;
    }

    /**
     * Выполняет запись в json файл и преобразует данные в соответсвующий формат
     */
    @Override
    public void write() {
        String data = "";
        UserList userList = DataUsers.getUsersList();
        for (User user : userList) {
            data += user.getUserData() + "\n";
        }
        try {
            FileOutputStream fOut = context.openFileOutput(path, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            osw.write(data);

            osw.flush();
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
