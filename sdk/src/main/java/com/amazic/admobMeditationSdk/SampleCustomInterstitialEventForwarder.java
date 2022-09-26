package com.amazic.admobMeditationSdk;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener;

public class SampleCustomInterstitialEventForwarder extends SampleAdListener {
    private CustomEventInterstitialListener mInterstitialListener;
    private InterstitialAd interstitialAd;
    /**
     * Creates a new SampleInterstitialEventForwarder.
     * @param listener An AdMob CustomEventInterstitialListener that should
     *                 receive forwarded events.
     */
    public SampleCustomInterstitialEventForwarder(CustomEventInterstitialListener listener,InterstitialAd interstitialAd) {
        this.mInterstitialListener = listener;
        this.interstitialAd = interstitialAd;
    }

    @Override
    public void onAdFetchSucceeded() {
        mInterstitialListener.onAdLoaded();
    }

    public static final String SAMPLE_NETWORK_DOMAIN = "www.sample-mediation.net";

    @Override
    public void onAdFetchFailed(SampleErrorCode errorCode) {
        switch (errorCode) {
            case UNKNOWN:
                mInterstitialListener.onAdFailedToLoad(new AdError(AdRequest.ERROR_CODE_INTERNAL_ERROR,
                        "Sample network returns an unknown error.", SAMPLE_NETWORK_DOMAIN));
                break;
            case BAD_REQUEST:
                mInterstitialListener.onAdFailedToLoad(new AdError(AdRequest.ERROR_CODE_INVALID_REQUEST,
                        "Sample network returns HTTP 301 error.", SAMPLE_NETWORK_DOMAIN));
                break;
            case NETWORK_ERROR:
                mInterstitialListener.onAdFailedToLoad(new AdError(AdRequest.ERROR_CODE_NETWORK_ERROR,
                        "Sample network returns a network error.", SAMPLE_NETWORK_DOMAIN));
                break;
            case NO_INVENTORY:
                mInterstitialListener.onAdFailedToLoad(new AdError(AdRequest.ERROR_CODE_NO_FILL,
                        "Sample network returns a no-fills error.", SAMPLE_NETWORK_DOMAIN));
                break;
        }
    }

    @Override
    public void onAdFullScreen() {
        mInterstitialListener.onAdOpened();
    }

    @Override
    public void onAdClosed() {
        mInterstitialListener.onAdClosed();
    }
}