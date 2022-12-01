package com.makoval.androidphonebook.WriteHelper.Action.JSON;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makoval.androidphonebook.Users.DataUsers;
import com.makoval.androidphonebook.WriteHelper.IWriteHelper;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Является реализации интерфейса IWriteHelper
 * Выполняет действие записи пользователя в json файл
 */
public class WriteToJSON implements IWriteHelper {

    /**
     * Контекст приложения
     */
    Context context;

    /**
     * Путь к файлу
     */
    String path;

    public WriteToJSON(Context context, String path) {
        this.path = path;
        this.context = context;
    }


    /**
     * Выполняет запись в json файл и преобразует данные в соответсвующий формат
     */
    @Override
    public void write() {
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
