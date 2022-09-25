package com.amazic.sdk;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener;

public class SampleCustomBannerEventForwarder extends SampleAdListener {
    private CustomEventBannerListener mBannerListener;
    private AdView mAdView;

    /**
     * Creates a new SampleBannerEventForwarder.
     * @param listener A CustomEventBannerListener that should receive
     *                 forwarded events.
     * @param adView   A SampleAdView.
     */
    public SampleCustomBannerEventForwarder(CustomEventBannerListener listener, AdView adView) {
        this.mBannerListener = listener;
        this.mAdView = adView;
    }

    @Override
    public void onAdFetchSucceeded() {
        mBannerListener.onAdLoaded(mAdView);
    }

    @Override
    public void onAdFetchFailed(SampleErrorCode errorCode) {
        switch (errorCode) {
            case UNKNOWN:
                mBannerListener.onAdFailedToLoad(AdRequest.ERROR_CODE_INTERNAL_ERROR);
                break;
            case BAD_REQUEST:
                mBannerListener.onAdFailedToLoad(AdRequest.ERROR_CODE_INVALID_REQUEST);
                break;
            case NETWORK_ERROR:
                mBannerListener.onAdFailedToLoad(AdRequest.ERROR_CODE_NETWORK_ERROR);
                break;
            case NO_INVENTORY:
                mBannerListener.onAdFailedToLoad(AdRequest.ERROR_CODE_NO_FILL);
                break;
        }
    }

    @Override
    public void onAdFullScreen() {
        mBannerListener.onAdClicked();
        mBannerListener.onAdOpened();
    }

    @Override
    public void onAdClosed() {
        mBannerListener.onAdClosed();
    }
}