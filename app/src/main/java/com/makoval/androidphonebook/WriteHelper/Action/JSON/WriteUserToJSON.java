package com.makoval.androidphonebook.WriteHelper.Action.JSON;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makoval.androidphonebook.Formatter.UserList;
import com.makoval.androidphonebook.Users.DataUsers;
import com.makoval.androidphonebook.WriteHelper.IWriteHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteUserToJSON implements IWriteHelper {
    /**
     * Контекст приложения
     */
    Context context;
    /**
     * Путь к файлу
     */
    String path;
    /**
     * Позиция в списке
     */
    int position;

    public WriteUserToJSON(Context context, String path, int position) {
        this.path = path;
        this.context = context;
        this.position = position;
    }

    /**
     * Выполняет запись одного пользователя в json файл и преобразует данные в соответсвующий формат
     */
    @Override
    public void write() {
        UserList userList = new UserList();
        userList.add(DataUsers.getUsersList().get(position));
        FileOutputStream fOut = null;
        try {
            File file = new File(context.getFilesDir() + "/" + path);
            file.createNewFile();
            fOut = new FileOutputStream(file, false);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(fOut, userList);
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
