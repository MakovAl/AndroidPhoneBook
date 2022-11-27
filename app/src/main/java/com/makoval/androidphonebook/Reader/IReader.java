package com.makoval.androidphonebook.Reader;

import com.makoval.androidphonebook.Formatter.UserList;

import java.io.IOException;

/**
 * Интерфейс содержащий метод чтения
 */
public interface IReader {
    /**
     * Метод реализуется классом в зависимости от ожидаемого поведения и способа чтения данных.
     *
     * @return Список пользователей
     * @see com.makoval.androidphonebook.Formatter.UserList
     * @throws IOException Исключение возникающее если что-то пошло не так, при чтении.
     */
    UserList read() throws IOException;
}
