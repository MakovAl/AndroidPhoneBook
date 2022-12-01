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
 * Выполняет действие изменение пользователя в базе данных
 */
public class EditUserDB implements IWriteHelper {

    /**
     * Контекст приложения
     */
    private Context context;
    /**
     * Позиция пользователя в списке
     */
    private int position;

    public EditUserDB(Context context, int position) {
        this.context = context;
        this.position = position;
    }

    /**
     * Выполняет обновление записи в базе данных используя UPDATE
     */
    @Override
    public void write() {
        User user = DataUsers.getUsersList().get(position);
        Developer developer;
        Manager manager;
        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();
        db.execSQL("UPDATE users SET type = '" + user.getType() + "', phoneNumber = '"
                + user.getUserPhoneNumber() + "', address = '" + user.getAddress() + "'" +
                " WHERE id = '" + user.getId() + "'");
        if (user.getType().equals("developer")) {
            developer = (Developer) user;
            db.execSQL("UPDATE developers SET name = '" + developer.getName() +
                    "', surname = '" + developer.getSurname() + "', position = '" + developer.getPosition() +
                    "', programmingLanguages = '" + developer.getProgrammingLanguages() + "'" +
                    " WHERE id = '" + user.getId() + "'");
        } else if (user.getType().equals("manager")) {
            manager = (Manager) user;
            db.execSQL("UPDATE managers SET name = '" + manager.getName() +
                    "', surname = '" + manager.getSurname() + "', position = '" + manager.getPosition() + "'" +
                    " WHERE id = '" + user.getId() + "'");
        }
        db.close();
    }
}
