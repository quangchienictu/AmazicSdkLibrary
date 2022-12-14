/*
 * Copyright (C) 2014 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amazic.admobMeditationSdk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.Random;

/**
 * A sample interstitial ad. This is an example of an interstitial class that most ad networks SDKs
 * have.
 */
public class SampleInterstitial {

  private final Context context;
  private String adUnit;
  private SampleAdListener listener;

  /**
   * Create a new {@link SampleInterstitial}.
   *
   * @param context An Android {@link Context}.
   */
  public SampleInterstitial(Context context) {
    this.context = context;
  }

  /**
   * Sets the sample ad unit.
   *
   * @param sampleAdUnit The sample ad unit.
   */
  public void setAdUnit(String sampleAdUnit) {
    this.adUnit = sampleAdUnit;
  }

  /**
   * Sets a {@link SampleAdListener} to listen for ad events.
   *
   * @param listener The ad listener.
   */
  public void setAdListener(SampleAdListener listener) {
    this.listener = listener;
  }

  /**
   * Fetch an ad. Instead of doing an actual ad fetch, we will randomly decide to succeed, or fail
   * with different error codes.
   *
   * @param request The ad request with targeting information.
   */
  public void fetchAd(SampleAdRequest request) {
    if (listener == null) {
      return;
    }

    // If the publisher didn't set an ad unit, return a bad request.
    if (adUnit == null) {
      listener.onAdFetchFailed(SampleErrorCode.BAD_REQUEST);
    }

    listener.onAdFetchSucceeded();
  }

  /**
   * Destroy the interstitial.
   */
  public void destroy() {
    listener = null;
  }
}

