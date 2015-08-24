package com.aprendergcm.febaisi.dois;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by felipebaisi on 8/24/15.
 */
public class MyInstanceIDListenerService extends InstanceIDListenerService {

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putBoolean(RegistrationIntentService.STATUS, false).apply();

        Intent it = new Intent(this, RegistrationIntentService.class);
        startService(it);

    }
}
