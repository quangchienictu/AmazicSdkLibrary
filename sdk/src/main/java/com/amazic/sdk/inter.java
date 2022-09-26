package com.amazic.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitial;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener;

public class inter  implements CustomEventInterstitial {
    private InterstitialAd mInterstitialAd;
    private Context mContext;
    private boolean isLoadSuccess;
    private String Tag = "SDKCustom Inter";
    @Override
    public void requestInterstitialAd(@NonNull Context context, @NonNull CustomEventInterstitialListener customEventInterstitialListener, @Nullable String s, @NonNull MediationAdRequest mediationAdRequest, @Nullable Bundle bundle) {
        Log.e(Tag, "ID :"+s );
        this.mContext = context;
        isLoadSuccess = false;
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, s, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                if(customEventInterstitialListener != null){
                    customEventInterstitialListener.onAdFailedToLoad(loadAdError);
                }
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                if(customEventInterstitialListener != null){
                    customEventInterstitialListener.onAdLoaded();
                }
                isLoadSuccess = true;
                mInterstitialAd = interstitialAd;
                interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        if(customEventInterstitialListener != null){
                            customEventInterstitialListener.onAdClosed();
                        }
                    }

                    @Override
                    public void onAdClicked() {
                        if(customEventInterstitialListener != null){
                            customEventInterstitialListener.onAdClicked();
                        }
                    }
                });
            }
        });


    }

    @Override
    public void showInterstitial() {
        mInterstitialAd.show((Activity) mContext);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    public AdRequest getAdRequest() {
        AdRequest.Builder builder = new AdRequest.Builder();
        return builder.build();
    }
    private SampleAdRequest createSampleRequest(MediationAdRequest mediationAdRequest) {
        SampleAdRequest request = new SampleAdRequest();
        request.setTestMode(mediationAdRequest.isTesting());
        request.setKeywords(mediationAdRequest.getKeywords());
        return request;
    }
}
