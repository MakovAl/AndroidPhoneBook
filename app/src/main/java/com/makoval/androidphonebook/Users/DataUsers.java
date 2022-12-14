package com.makoval.androidphonebook.Users;

import com.makoval.androidphonebook.Formatter.UserList;

/**
 * Класс хранит список пользователей и реализует необходимые методы для работы со списком
 */
public class DataUsers {


    /**
     * Список пользователей
     */
    private static UserList usersList = new UserList();

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
     * Заменяет данные пользователя в списке по указаной позиции
     *
     * @param position Позиция пользователя в списке
     * @param user     Новые данные пользователя
     */
    public static void replaceUser(int position, User user) {
        DataUsers.usersList.remove(position);
        DataUsers.usersList.add(position, user);
    }

    /**
     * @param position Позиция в списке пользователей
     */
    public static void delUser(int position) {
        usersList.remove(position);
    }

    /**
     * @param user Пользователь
     */
    public static void addUser(User user){
        usersList.add(user);
    }

    /**
     * @return Размер списка пользователей
     */
    public static int getSize() {
        return usersList.size();
    }

    public static User getLastUser() {
        return usersList.getLast();
    }
}
