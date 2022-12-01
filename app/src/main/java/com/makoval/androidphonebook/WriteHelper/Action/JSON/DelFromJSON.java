package com.makoval.androidphonebook.WriteHelper.Action.JSON;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makoval.androidphonebook.Users.DataUsers;
import com.makoval.androidphonebook.WriteHelper.IWriteHelper;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Является реализации интерфейса IWriteHelper
 * Выполняет действие удаления пользователя из json файла
 */
public class DelFromJSON implements IWriteHelper {
    /**
     * Контекст приложения
     */
    private Context context;
    /**
     * Путь к файлу с данными
     */
    private String path;
    /**
     * Позиция пользователя в списке
     */
    private int position;

    public DelFromJSON(Context context, String path, int position) {
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
        FileOutputStream fOut = null;
        try {
            fOut = context.openFileOutput(path, Context.MODE_PRIVATE);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(fOut, DataUsers.getUsersList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
