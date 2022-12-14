package com.makoval.androidphonebook.PhoneBookTools;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Инструменты для работы с контактной книгой телефона
 */
public class PhoneBookTools {

    /**
     * Контекст приложения
     */
    private final Context context;

    public PhoneBookTools(Context context) {
        this.context = context;
    }

    /**
     * Проверка номера на содержание в списке контактов
     *
     * @param phoneNumber Номер телефона
     * @return Истина или ложь в зависимости от того, содержиться ли телефон в книге контактов
     */
    public boolean checkPhoneInList(String phoneNumber) {
        List<String> contactsPhoneNumbers = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        String[] projection = new String[]{
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        };

        String selection = ContactsContract.CommonDataKinds.Phone.NUMBER + "= ?";
        String[] values = new String[]{
                phoneNumber
        };

        final Cursor phone = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection, selection, values, null);

        if (phone.moveToFirst()) {
            while (!phone.isAfterLast()) {
                int numberIndex = phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                if (numberIndex > -1) {
                    contactsPhoneNumbers.add(phone.getString(numberIndex));
                    phone.moveToNext();
                }
            }
        }
        phone.close();
        return contactsPhoneNumbers.contains(phoneNumber);
    }

    /**
     * Создает нового пользователя в книге контактов
     *
     * @param name  Имя пользователя
     * @param phone Телефон пользователя
     */
    public void createNewContact(String name, String phone) {
        Uri contactUri = context.getContentResolver()
                .insert(ContactsContract.RawContacts.CONTENT_URI, new ContentValues());
        long contactID = ContentUris.parseId(contactUri);
        ContentValues newContact = new ContentValues();

        addContactData(newContact,
                contactID,
                ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE,
                ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                name);

        addContactData(newContact,
                contactID,
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                phone);
    }

    /**
     * Добавляет данные в созданный контакт
     *
     * @param newContact ContentValues для создоваемого контакта
     * @param id         ID создоваемого контакта
     * @param mimetype   MIMETYPE добавляемого типа данных в контакте
     * @param type       Тип добовляемых данных в контакт
     * @param data       Добавляемые данные
     */
    public void addContactData(ContentValues newContact, long id, String mimetype, String type,
                               String data) {
        newContact.put(ContactsContract.Data.RAW_CONTACT_ID, id);
        newContact.put(ContactsContract.Data.MIMETYPE, mimetype);
        newContact.put(type, data);

        context.getContentResolver().insert(ContactsContract.Data.CONTENT_URI, newContact);
        newContact.clear();
    }
}
