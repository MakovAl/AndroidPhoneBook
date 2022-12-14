package com.makoval.androidphonebook.Reader;

import static androidx.core.content.FileProvider.getUriForFile;

import android.content.Context;
import android.net.Uri;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makoval.androidphonebook.Formatter.UserList;

import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Класс реализующий интерфейс IReader для записи в json файл
 *
 * @see IReader
 */
@NoArgsConstructor
public class ReadFromJson implements IReader {

    /**
     * Контекст приложения
     */
    Context context;
    /**
     * Uri файла
     */
    Uri uriFile;

    /**
     * Конструктор принимающий строку к файлу и преобразующий в uri
     *
     * @param pathFile Путь к файлу
     * @param context  Контекст
     */
    public ReadFromJson(String pathFile, Context context) {
        this.uriFile = getUriForFile(context,
                "com.makoval.androidphonebook.provider",
                new File(context.getFilesDir(), pathFile));
        this.context = context;
    }

    public ReadFromJson(Uri uriFile, Context context) {
        this.uriFile = uriFile;
        this.context = context;
    }

    /**
     * Метод считывает данные из json файла и помещает в список
     * Открывает и закрывает файл для чтения.
     *
     * @return Список пользоватей считаных из файла
     * @throws IOException Если при чтении из файл что-то пошло не так, вызывается исключение
     */
    @Override
    public UserList read() throws IOException {
        UserList users = null;
        InputStream inputStream = context.getContentResolver().openInputStream(uriFile);
        ObjectMapper mapper = new ObjectMapper();
        users = mapper.readValue(inputStream, UserList.class);
        return users;
    }
}
