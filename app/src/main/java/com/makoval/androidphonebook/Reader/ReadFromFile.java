package com.makoval.androidphonebook.Reader;

import android.content.Context;
import android.util.Log;

import com.makoval.androidphonebook.Formatter.FormatUser;
import com.makoval.androidphonebook.Formatter.UserList;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Класс реализующий интерфейс IReader для записи в текстовый файл
 * @see IReader
 */
@NoArgsConstructor
public class ReadFromFile implements IReader {

    /**
     * Путь к файлу для чтения
     */
    private String pathFile;

    /**
     * Контекст приложения
     */
    private Context context;

    public ReadFromFile(String pathFile, Context context) {
        this.pathFile = pathFile;
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
        UserList userList;

        try {
            InputStream inputStream = context.openFileInput(pathFile);

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

        FormatUser formatUser = null;
        List<String> dataUsers = Arrays.asList(builder.toString().split("\n"));
        formatUser = new FormatUser(dataUsers);
        userList = formatUser.format();
        return userList;
    }
}
