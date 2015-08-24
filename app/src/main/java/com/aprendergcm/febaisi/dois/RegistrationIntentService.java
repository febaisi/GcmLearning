package com.aprendergcm.febaisi.dois;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by felipebaisi on 8/24/15.
 */
public class RegistrationIntentService extends IntentService {
    public static String LOG = "GCMLOG";
    public static String STATUS = "STATUS";
    public static String SENDER_ID = "847921637262";


    public RegistrationIntentService(){
        super(LOG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean status = sharedPreferences.getBoolean(STATUS, false);

        synchronized (LOG){
            InstanceID instanceID = InstanceID.getInstance(this);
            if (!status){
                try {
                    String token = instanceID.getToken(SENDER_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null );
                    Log.i(LOG, "TOKEN= " + token);
                    sharedPreferences.edit().putBoolean(STATUS, token != null && token.trim().length() > 0).apply();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void SendRegistration(String token) {



    }
}
