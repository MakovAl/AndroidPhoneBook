package com.makoval.androidphonebook.Writer;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makoval.androidphonebook.Formatter.UserList;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Класс реализующий интерфейс
 *
 * @see IWriter
 */
public class WriteToJson implements IWriter {

    /**
     * Список пользователей
     */
    UserList users;
    /**
     * Путь к файлу
     */
    String path;

    Context context;

    public WriteToJson(String path, UserList users, Context context) {
        this.path = path;
        this.users = users;
        this.context = context;
    }


    /**
     * Метод интерфейса IWriter записывающий данные в json файл
     *
     * @throws IOException Если при записи в файл что-то пошло не так, вызывается исключение
     */
    @Override
    public void write() throws IOException {
        FileOutputStream fOut = context.openFileOutput(path, Context.MODE_PRIVATE);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(fOut, users);
    }
}
