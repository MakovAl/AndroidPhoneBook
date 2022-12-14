package com.makoval.androidphonebook.PhoneBookTools.Notifier;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.CalendarContract;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Уведомление через календарь
 */
public class NotifierViaCalendar implements INotifier {
    /**
     * Контекст приложения
     */
    Context context;


    public NotifierViaCalendar(Context context) {
        this.context = context;
    }

    @Override
    public void notifyAboutCall(String phone) {
        Uri uri = createEvent(phone, context);
        long eventID = Long.parseLong(uri.getLastPathSegment());
        getReminders(eventID, context);
    }

    /**
     * Создает событие с сообщением о пропущенном вызове
     *
     * @param phone   Номер телефона
     * @param context Контекст приложения
     * @return Uri события созданого в календаре
     */
    private Uri createEvent(String phone, Context context) {
        long calID = 1;
        long startMillis = 0;
        long endMillis = 0;
        Calendar beginTime = Calendar.getInstance();
        beginTime.add(Calendar.MINUTE, 5);
        startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.MINUTE, 15);
        endMillis = endTime.getTimeInMillis();

        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, startMillis);
        values.put(CalendarContract.Events.DTEND, endMillis);
        values.put(CalendarContract.Events.TITLE, "Call missed");
        values.put(CalendarContract.Events.DESCRIPTION, "Call missed from: " + phone);
        values.put(CalendarContract.Events.CALENDAR_ID, calID);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().toString());
        return cr.insert(CalendarContract.Events.CONTENT_URI, values);
    }

    /**
     * Отправляет на почту сообщение о пропущенном вызове с помощью напоминания
     *
     * @param eventID ID события
     * @param context Контекст приложения
     */
    private void getReminders(long eventID, Context context) {
        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Reminders.MINUTES, 5);
        values.put(CalendarContract.Reminders.EVENT_ID, eventID);
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_EMAIL);
        cr.insert(CalendarContract.Reminders.CONTENT_URI, values);
    }
}
