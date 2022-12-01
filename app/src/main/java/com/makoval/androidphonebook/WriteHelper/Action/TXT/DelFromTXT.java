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
 * Выполняет действие удаления пользователя из txt файла
 */
public class DelFromTXT implements IWriteHelper {
    /**
     * Контекст приложения
     */
    Context context;
    /**
     * Путь к файлу
     */
    String path;
    /**
     * Позиция пользователя в списке
     */
    int position;

    public DelFromTXT(Context context, String path, int position) {
        this.context = context;
        this.path = path;
        this.position = position;
    }


    /**
     * Удаляет данные пользователя по указанной позиции
     * и перезаписывает файл с новыми данными.
     */
    @Override
    public void write() {
        DataUsers.delUser(position);
        UserList userList = DataUsers.getUsersList();
        String data = "";
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
