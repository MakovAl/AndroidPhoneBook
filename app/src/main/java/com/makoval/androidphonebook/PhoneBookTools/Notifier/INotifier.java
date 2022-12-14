package com.makoval.androidphonebook.PhoneBookTools.Notifier;

/**
 * Интерфейс содержащий метод уведомления
 */
public interface INotifier {
    /**
     * Метод реализуется классом в зависимости от ожидаемого поведения и способа уведомления.
     *
     * @param phone Номер телефона
     */
    void notifyAboutCall(String phone);
}
