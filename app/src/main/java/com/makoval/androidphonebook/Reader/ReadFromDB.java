package com.makoval.androidphonebook.Reader;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.makoval.androidphonebook.DBHelper.DBHelper;
import com.makoval.androidphonebook.Formatter.UserList;
import com.makoval.androidphonebook.Users.Developer;
import com.makoval.androidphonebook.Users.Manager;

import java.io.IOException;

/**
 * Класс реализующий интерфейс IReader для записи в базу данных
 *
 * @see IReader
 */
public class ReadFromDB implements IReader {

    /**
     * Контекст приложения
     */
    private Context context;

    public ReadFromDB(Context context) {
        this.context = context;
    }


    /**
     * Открывает базу данных для чтения, считывает данных пользователей с список.
     *
     * @return Список пользователей
     * @throws IOException
     */
    @Override
    public UserList read() throws IOException {
        UserList userlist = new UserList();
        SQLiteDatabase db = new DBHelper(context).getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM users" +
                " INNER JOIN developers ON users.id = developers.id UNION" +
                " SELECT *, null as programmingLanguages FROM users" +
                " INNER JOIN managers ON users.id = managers.id;", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            if (cursor.getString(1).equals("developer")) {
                userlist.add(new Developer(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(5),
                        cursor.getString(6), cursor.getString(7), cursor.getString(8)));
            } else if (cursor.getString(1).equals("manager")) {
                userlist.add(new Manager(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(5),
                        cursor.getString(6), cursor.getString(7)));
            }
            cursor.moveToNext();
        }

        cursor.close();
        db.close();

        return userlist;
    }
}
