package com.makoval.androidphonebook.Writer;

import android.content.Intent;

import java.io.IOException;

/**
 * Интерфейс содержащий метод записии
 */
public interface IWriter {
    /**
     * Метод реализуется классом в зависимости от ожидаемого поведения и способа записи данных.
     *
     * @param intent намерения
     * @throws IOException Исключение возникающее если что-то пошло не так, при записи.
     */
    void write(Intent intent) throws IOException;
}
