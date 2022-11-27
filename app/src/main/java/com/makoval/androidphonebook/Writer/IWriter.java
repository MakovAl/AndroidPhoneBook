package com.makoval.androidphonebook.Writer;

import java.io.IOException;

/**
 * Интерфейс содержащий метод записии
 */
public interface IWriter {
    /**
     * Метод реализуется классом в зависимости от ожидаемого поведения и способа записи данных.
     *
     * @throws IOException Исключение возникающее если что-то пошло не так, при записи.
     */
    void write() throws IOException;
}
