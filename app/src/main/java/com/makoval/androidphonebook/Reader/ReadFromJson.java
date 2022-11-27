package com.makoval.androidphonebook.Reader;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makoval.androidphonebook.Formatter.UserList;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;

/**
 * Класс реализующий интерфейс IReader для записи в json файл
 * @see IReader
 */
@NoArgsConstructor
public class ReadFromJson implements IReader {

    /**
     * Путь к файлу json
     */
    String pathFile;
    /**
     * Контекст приложения
     */
    Context context;

    public ReadFromJson(String pathFile, Context context) {
        this.pathFile = pathFile;
        this.context = context;
    }

    /**
     *
     * Метод считывает данные из json файла и помещает в список
     * Открывает и закрывает файл для чтения.
     * @return Список пользоватей считаных из файла
     * @throws IOException Если при чтении из файл что-то пошло не так, вызывается исключение
     */
    @Override
    public UserList read() throws IOException {
        UserList users = null;
        InputStream inputStream = context.openFileInput(pathFile);
        ObjectMapper mapper = new ObjectMapper();
        users = mapper.readValue(inputStream, UserList.class);
        return users;
    }
}
