package com.makoval.androidphonebook.Writer;

import android.content.Context;
import android.content.Intent;

import com.makoval.androidphonebook.WriteHelper.Action.JSON.DelFromJSON;
import com.makoval.androidphonebook.WriteHelper.Action.JSON.WriteToJSON;
import com.makoval.androidphonebook.WriteHelper.WriteActionStore;

import java.io.IOException;

/**
 * Класс реализующий интерфейс
 *
 * @see IWriter
 */
public class WriteToJson implements IWriter {

    /**
     * Контекст приложения
     */
    Context context;

    /**
     * Путь к файлу
     */
    String path;

    public WriteToJson(Context context, String path) {
        this.path = path;
        this.context = context;
    }


    /**
     * Метод интерфейса IWriter
     * Устанавливает возможные дейсвия с json файлом в WriteActionStore
     * Получает из intent @action дейсвие которое нужно совершить.
     * Получает из WriteActionStore класс соответсвуещего действия
     *
     * @param intent намерения
     * @throws IOException
     */
    @Override
    public void write(Intent intent) throws IOException {
        WriteActionStore was = new WriteActionStore();
        was.addAction("add", new WriteToJSON(context, path));
        was.addAction("del", new DelFromJSON(context, path,
                intent.getIntExtra("@position", -1)));
        was.addAction("edit", new WriteToJSON(context, path));
        was.getAction(intent.getStringExtra("@action")).write();
    }
}
