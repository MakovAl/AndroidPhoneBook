package com.makoval.androidphonebook.Writer;

import android.content.Context;
import android.content.Intent;

import com.makoval.androidphonebook.WriteHelper.Action.DB.AddToDB;
import com.makoval.androidphonebook.WriteHelper.Action.DB.DelFromDB;
import com.makoval.androidphonebook.WriteHelper.Action.DB.EditUserDB;
import com.makoval.androidphonebook.WriteHelper.WriteActionStore;


import java.io.IOException;

public class WriteToDB implements IWriter {

    /**
     * Контекст приложения
     */
    private Context context;

    public WriteToDB(Context context) {
        this.context = context;
    }


    /**
     * Метод интерфейса IWriter
     * Устанавливает возможные дейсвия с базой данных в WriteActionStore
     * Получает из intent @action дейсвие которое нужно совершить.
     * Получает из WriteActionStore класс соответсвуещего действия
     *
     * @param intent намерения
     * @throws IOException
     */
    @Override
    public void write(Intent intent) throws IOException {
        WriteActionStore was = new WriteActionStore();
        was.addAction("add", new AddToDB(context));
        was.addAction("del", new DelFromDB(context,
                intent.getIntExtra("@position", -1)));
        was.addAction("edit", new EditUserDB(context,
                intent.getIntExtra("@position", -1)));
        was.getAction(intent.getStringExtra("@action")).write();
    }
}
