package com.makoval.androidphonebook.WriteHelper.Action.TXT;

import android.content.Context;

import com.makoval.androidphonebook.Formatter.UserList;
import com.makoval.androidphonebook.Users.DataUsers;
import com.makoval.androidphonebook.Users.User;
import com.makoval.androidphonebook.WriteHelper.IWriteHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteUserToTXT implements IWriteHelper {
    /**
     * Контекст приложения
     */
    private Context context;
    /**
     * Путь к файлу
     */
    private String path;
    /**
     * Позиция в списке
     */
    int position;


    public WriteUserToTXT(Context context, String path, int position) {
        this.path = path;
        this.context = context;
        this.position = position;
    }

    /**
     * Выполняет запись одного пользователя в json файл и преобразует данные в соответсвующий формат
     */
    @Override
    public void write() {
        String data = "";
        UserList userList = new UserList();
        userList.add(DataUsers.getUsersList().get(position));
        for (User user : userList) {
            data += user.getUserData() + "\n";
        }
        try {
            File file = new File(context.getFilesDir() + "/" + path);
            file.createNewFile();
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
