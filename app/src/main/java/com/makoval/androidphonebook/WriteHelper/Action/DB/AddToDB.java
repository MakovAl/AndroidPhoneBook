package com.makoval.androidphonebook.WriteHelper.Action.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.makoval.androidphonebook.DBHelper.DBHelper;
import com.makoval.androidphonebook.Users.DataUsers;
import com.makoval.androidphonebook.Users.Developer;
import com.makoval.androidphonebook.Users.Manager;
import com.makoval.androidphonebook.Users.User;
import com.makoval.androidphonebook.WriteHelper.IWriteHelper;

/**
 * Является реализации интерфейса IWriteHelper
 * Выполняет действие добавлние пользователя в базу данных
 */
public class AddToDB implements IWriteHelper {

    /**
     * Контекст приложения
     */
    private Context context;

    public AddToDB(Context context) {
        this.context = context;
    }

    /**
     * Выполняет запись в базу данных используя INSERT
     */
    @Override
    public void write() {
        User user = DataUsers.getLastUser();
        Developer developer;
        Manager manager;
        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();
        db.execSQL("INSERT INTO users (type, phoneNumber, address) VALUES ('" + user.getType() + "','"
                + user.getUserPhoneNumber() + "','" + user.getAddress() + "')");
        if (user.getType().equals("developer")) {
            developer = (Developer) user;
            db.execSQL("INSERT INTO developers (id, name, surname, position, programmingLanguages)" +
                    " VALUES (last_insert_rowid(), '" + developer.getName() + "','" + developer.getSurname() +
                    "','" + developer.getPosition() + "','" + developer.getProgrammingLanguages() + "')");
        } else if (user.getType().equals("manager")) {
            manager = (Manager) user;
            db.execSQL("INSERT INTO managers (id, name, surname, position)" +
                    " VALUES (last_insert_rowid(), '" + manager.getName() + "','" + manager.getSurname() +
                    "','" + manager.getPosition() + "')");
        }

        db.close();
    }
}
