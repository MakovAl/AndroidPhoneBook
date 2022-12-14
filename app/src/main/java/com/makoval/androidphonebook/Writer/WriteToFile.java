package com.makoval.androidphonebook.Writer;

import android.content.Context;
import android.content.Intent;

import com.makoval.androidphonebook.WriteHelper.Action.TXT.DelFromTXT;
import com.makoval.androidphonebook.WriteHelper.Action.TXT.WriteToTXT;
import com.makoval.androidphonebook.WriteHelper.Action.TXT.WriteUserToTXT;
import com.makoval.androidphonebook.WriteHelper.WriteActionStore;

import java.io.IOException;

/**
 * Класс реализующий интерфейс
 *
 * @see IWriter
 */
public class WriteToFile implements IWriter {

    /**
     * Контекст приложения
     */
    private final Context context;
    /**
     * Путь к файлу для записи
     */
    private final String pathFile;

    /**
     * Конструктор задающий значение для полей
     *
     * @param pathFile Путь к файлу для записи
     */
    public WriteToFile(String pathFile, Context context) {
        this.pathFile = pathFile;
        this.context = context;
    }

    /**
     * Метод интерфейса IWriter
     * Устанавливает возможные дейсвия с файлом в WriteActionStore
     * Получает из intent @action дейсвие которое нужно совершить.
     * Получает из WriteActionStore класс соответсвуещего действия
     *
     * @param intent намерения
     * @throws IOException
     */
    @Override
    public void write(Intent intent) throws IOException {
        int position = intent.getIntExtra("@position", -1);
        WriteActionStore was = new WriteActionStore();
        was.addAction("add", new WriteToTXT(context, pathFile));
        was.addAction("del", new DelFromTXT(context, pathFile, position));
        was.addAction("edit", new WriteToTXT(context, pathFile));
        was.addAction("writeShareContact", new WriteUserToTXT(context, pathFile, position));
        was.getAction(intent.getStringExtra("@action")).write();
    }
}
