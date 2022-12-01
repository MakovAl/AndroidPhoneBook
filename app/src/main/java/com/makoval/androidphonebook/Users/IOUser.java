package com.makoval.androidphonebook.Users;

import com.makoval.androidphonebook.Reader.IReader;
import com.makoval.androidphonebook.Writer.IWriter;

/**
 * Класс хранщий читателя и писателя используемые в текущем сеансе
 */
public class IOUser {

    /**
     * Читатель
     *
     * @see IWriter
     */
    private static IWriter writer;
    /**
     * Писатель
     *
     * @see IReader
     */
    private static IReader reader;


    public static IWriter getWriter() {
        return writer;
    }

    public static void setWriter(IWriter writer) {
        IOUser.writer = writer;
    }

    public static IReader getReader() {
        return reader;
    }

    public static void setReader(IReader reader) {
        IOUser.reader = reader;
    }
}
