package com.makoval.androidphonebook.PhoneBookTools.Notifier;

/**
 * Если пользователь решил, что не хочет получать уведомления
 */
public class WithoutNotifications implements INotifier{

    @Override
    public void notifyAboutCall(String phone) {

    }

}
