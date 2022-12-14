package com.makoval.androidphonebook.Reader;

import static androidx.core.content.FileProvider.getUriForFile;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.makoval.androidphonebook.Formatter.FormatUser;
import com.makoval.androidphonebook.Formatter.UserList;

import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Класс реализующий интерфейс IReader для записи в текстовый файл
 *
 * @see IReader
 */
@NoArgsConstructor
public class ReadFromFile implements IReader {

    /**
     * Контекст приложения
     */
    private Context context;


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
    public ReadFromFile(String pathFile, Context context) {
        this.uriFile = getUriForFile(context,
                "com.makoval.androidphonebook.provider",
                new File(context.getFilesDir(), pathFile));
        this.context = context;
    }

    /**
     * @param uriFile Uri файла
     * @param context Контекст
     */
    public ReadFromFile(Uri uriFile, Context context) {
        this.uriFile = uriFile;
        this.context = context;
    }

    /**
     * Метод считывает данные из файла и помещает в список
     *
     * @return Список пользоватей считаных из файла
     * @throws IOException Если при чтении из файл что-то пошло не так, вызывается исключение
     */
    @Override
    public UserList read() throws IOException {
        StringBuilder builder = new StringBuilder();
        String data = null;
        UserList userList = new UserList();

        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uriFile);

            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);

                while ((data = reader.readLine()) != null) {
                    builder.append(data + "\n");
                }

                inputStream.close();
            }
        } catch (IOException e) {
            Log.e("Exception", "File read failed: " + e.toString());
        }

        if (!builder.toString().equals("")) {
            FormatUser formatUser = null;
            List<String> dataUsers = Arrays.asList(builder.toString().split("\n"));
            formatUser = new FormatUser(dataUsers);
            userList = formatUser.format();
        }
        return userList;
    }
}
