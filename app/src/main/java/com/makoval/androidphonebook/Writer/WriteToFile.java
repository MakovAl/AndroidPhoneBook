package com.makoval.androidphonebook.Writer;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Класс реализующий интерфейс
 *
 * @see IWriter
 */
public class WriteToFile implements IWriter {

    /**
     * Путь к файлу для записи
     */
    private final String pathFile;
    /**
     * Данные для записи в файл
     */
    private final String data;

    private final Context context;

    /**
     * Конструктор задающий значение для полей
     *
     * @param pathFile Путь к файлу для записи
     * @param data     Данные для записи в файл
     */
    public WriteToFile(String pathFile, String data, Context context) {
        this.pathFile = pathFile;
        this.data = data;
        this.context = context;
    }

    /**
     * Метод интерфейса IWriter окрывающий файл для записи, записывающий данные и закрывающий файл.
     *
     * @throws IOException Если при записи в файл что-то пошло не так, вызывается исключение
     */
    @Override
    public void write() throws IOException {
        FileOutputStream fOut = context.openFileOutput(pathFile, Context.MODE_PRIVATE);
        OutputStreamWriter osw = new OutputStreamWriter(fOut);

        osw.write(data);

        osw.flush();
        osw.close();
    }
}
