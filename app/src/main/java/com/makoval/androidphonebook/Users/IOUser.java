package com.makoval.androidphonebook.Users;

import com.makoval.androidphonebook.Reader.IReader;
import com.makoval.androidphonebook.Writer.IWriter;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс хранщий читателя и писателя используемые в текущем сеансе
 */
public class IOUser {
    /**
     * Писатель
     *
     * @see IWriter
     */
    private static IWriter writer;
    /**
     * Читатель
     *
     * @see IReader
     */
    private static IReader reader;
    /**
     * Писатель для отправляемых файлов
     *
     * @see IWriter
     */
    private static IWriter writerSend;
    /**
     * Имя отправляемого файла
     */
    private static String fileSend;
    /**
     * Читатель для получаемых файлов
     *
     * @see IReader
     */
    private static final Map<String, IReader> readerSend = new HashMap<>();

    public static IReader getReaderSend(String key) {
        return readerSend.get(key);
    }

    public static void setReaderSend(String key, IReader reader) {
        IOUser.readerSend.put(key, reader);
    }

    public static String getFileSend() {
        return fileSend;
    }

    public static void setFileSend(String fileSend) {
        IOUser.fileSend = fileSend;
    }

    public static IWriter getWriterSend() {
        return writerSend;
    }

    public static void setWriterSend(IWriter writerSend) {
        IOUser.writerSend = writerSend;
    }

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
