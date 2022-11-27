package com.makoval.androidphonebook.Users;

import com.makoval.androidphonebook.Formatter.UserList;

/**
 * Класс хранит список пользователей и реализует необходимые методы для работы со списком
 */
public class DataUsers {

    /**
     * Список пользователей
     */
    static UserList usersList = new UserList();

    /**
     * @return Список пользователей
     */
    public static UserList getUsersList() {
        return usersList;
    }

    /**
     * @param usersList Список пользователей
     */
    public static void setUsersList(UserList usersList) {
        DataUsers.usersList = usersList;
    }

    /**
     * @param user Пользователь
     */
    public static void addUser(User user) {
        DataUsers.usersList.add(user);
    }

    /**
     * @param position Позиция в списке пользователей
     */
    public static void delUser(int position) {
        usersList.remove(position);
    }

    /**
     * @return Размер списка пользователей
     */
    public static int getSize() {
        return usersList.size();
    }
}
