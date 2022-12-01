package com.makoval.androidphonebook.WriteHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс реализующий хранилище для действий с пользователями
 */
public class WriteActionStore {

    /**
     * Хранилище в Map<@action, класс дейсвия>
     */
    private Map<String, IWriteHelper> store;


    public WriteActionStore() {
        store = new HashMap<>();
    }

    /**
     * Добавляет действие в хранилище
     *
     * @param action      Название действия
     * @param writeAction Класс действия
     */
    public void addAction(String action, IWriteHelper writeAction) {
        store.put(action, writeAction);
    }

    /**
     * Выдает класс дейсвия по названию
     *
     * @param action Название действия
     * @return Класс дейсвия
     */
    public IWriteHelper getAction(String action) {
        return store.get(action);
    }

    /**
     * Удаляет действие по названию
     *
     * @param action Название действия
     */
    public void delAction(String action) {
        store.remove(action);
    }

}
