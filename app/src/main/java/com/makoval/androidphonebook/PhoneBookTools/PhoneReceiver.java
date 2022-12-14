package com.makoval.androidphonebook.PhoneBookTools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;

import androidx.preference.PreferenceManager;

import com.makoval.androidphonebook.PhoneBookTools.Notifier.INotifier;
import com.makoval.androidphonebook.PhoneBookTools.Notifier.NotifierViaCalendar;
import com.makoval.androidphonebook.PhoneBookTools.Notifier.WithoutNotifications;

import java.util.HashMap;
import java.util.Map;

public class PhoneReceiver extends BroadcastReceiver {

    /**
     * Звонок пропущен
     */
    static boolean callReceived = false;
    /**
     * Уведомитель
     */
    static INotifier notifier;

    /**
     * Если поступил звонок от номера помечаного как важный и установлен уведомитель,
     * то отправляется уведомление
     *
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String phone = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        String state = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
        if (notifier == null){
            Map<String, INotifier> notifierMap = new HashMap<>();
            notifierMap.put("Calendar", new NotifierViaCalendar(context));
            notifierMap.put("NotNotify", new WithoutNotifications());

            notifier = notifierMap.get(sharedPreferences.getString("listNotifications",
                    "NotNotify"));
        }

        if (phone != null) {
            if (sharedPreferences.contains(phone)) {
                if (state.equals("OFFHOOK")) {
                    callReceived = true;
                }
                if (state.equals("IDLE")) {
                    if (!callReceived) {
                        notifier.notifyAboutCall(phone);
                    } else {
                        callReceived = false;
                    }
                }
            }
        }
    }
}
