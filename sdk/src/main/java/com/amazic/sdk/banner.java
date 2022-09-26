package com.amazic.sdk;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventBanner;
import com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener;

public class banner implements CustomEventBanner
{
    private String Tag = "SDK Banner";
    private SampleAdView sampleAdView;
    private AdView adView;
    @Override
    public void requestBannerAd(@NonNull Context context, @NonNull CustomEventBannerListener customEventBannerListener, @Nullable String s, @NonNull AdSize adSize, @NonNull MediationAdRequest mediationAdRequest, @Nullable Bundle bundle) {
        Log.e(Tag, "ID :"+s );
        adView = new AdView(context);
        adView.setAdUnitId(s);
        adView.setAdSize(adSize);
        adView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        adView.loadAd(getAdRequest());

        sampleAdView = new SampleAdView(context);
        sampleAdView.setAdUnit(s);
        sampleAdView.setSize(new SampleAdSize(adSize.getWidth(), adSize.getHeight()));
        sampleAdView.setAdListener(new SampleCustomBannerEventForwarder(customEventBannerListener, adView));
        // Make an ad request.
        sampleAdView.fetchAd(createSampleRequest(mediationAdRequest));
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

    private SampleAdRequest createSampleRequest(MediationAdRequest mediationAdRequest) {
        SampleAdRequest request = new SampleAdRequest();
        request.setTestMode(mediationAdRequest.isTesting());
        request.setKeywords(mediationAdRequest.getKeywords());
        return request;
    }

    public AdRequest getAdRequest() {
        AdRequest.Builder builder = new AdRequest.Builder();
        return builder.build();
    }
}
