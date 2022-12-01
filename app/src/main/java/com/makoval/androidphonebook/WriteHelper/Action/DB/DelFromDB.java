package com.makoval.androidphonebook.WriteHelper.Action.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.makoval.androidphonebook.DBHelper.DBHelper;
import com.makoval.androidphonebook.Users.DataUsers;
import com.makoval.androidphonebook.Users.User;
import com.makoval.androidphonebook.WriteHelper.IWriteHelper;

/**
 * Является реализации интерфейса IWriteHelper
 * Выполняет действие удаления пользователя из базы данных
 */
public class DelFromDB implements IWriteHelper {

    /**
     * Контекст приложения
     */
    private Context context;
    /**
     * Позиция пользователя в списке
     */
    private int position;

    public DelFromDB(Context context, int position) {
        this.context = context;
        this.position = position;
    }

    /**
     * Удаления данных пользователя по id используя DELETE
     */
    @Override
    public void write() {
        User user = DataUsers.getUsersList().get(position);
        DataUsers.delUser(position);
        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();
        db.execSQL("DELETE FROM users WHERE id = '" + user.getId() + "'");
        db.close();
    }


}
