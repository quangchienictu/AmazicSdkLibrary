package com.amazic.admobMeditationSdk.util;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;

public class FirebaseAnalyticsUtil {
    public static String INTER = "INTER";
    public static String BANNER = "BANNER";
    public static String NATIVE = "NATIVE";
    private static final String TAG = "FirebaseAnalyticsUtil";

    public static void logEventMediationAdmob(Context context, String adsName) {
        Bundle bundle = new Bundle();
        Log.e(TAG,"Mediation Admob :"+adsName);
        FirebaseAnalytics.getInstance(context).logEvent("MediationAdmob: "+adsName, bundle);
    }

    public static void logEventMediationAdx(Context context, String adsName) {
        Bundle bundle = new Bundle();
        Log.e(TAG,"MediationAdmob Adx :"+adsName);
        FirebaseAnalytics.getInstance(context).logEvent("MediationAdx: "+adsName, bundle);
    }
}
