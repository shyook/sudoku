package com.example.jumping_i.sudoku_original.views.ad.full;

import android.app.Activity;
import android.util.Log;

import com.example.jumping_i.sudoku_original.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * 전면 광고 노출을 위한 싱글턴 클래스.
 */
public class InterstitialAds {
    private static final String TAG = InterstitialAds.class.getSimpleName();
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private static InterstitialAds mInstance; // 싱글턴 인스턴스
    private InterstitialAd mInterstitialAd; // 전면 광고 google class
    private Activity mActivity; // context

    /*******************************************************************************
     * 생성자.
     *******************************************************************************/
    private InterstitialAds(Activity activity) {
        mActivity = activity;
        init();
    }

    public static InterstitialAds getInstance(Activity activity) {
        if (mInstance == null) {
            mInstance = new InterstitialAds(activity);
        }

        return mInstance;
    }

    /*******************************************************************************
     * Public Method.
     *******************************************************************************/
    /**
     * 전면 광고를 노출 하고 광고를 닫으면 다른 광고를 로드해 놓는다.
     */
    public void show() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }

        mInterstitialAd.setAdListener(new AdListener() {
                  @Override
                  public void onAdClosed() {
                      // Load the next interstitial.
                      mInterstitialAd.loadAd(new AdRequest.Builder().build());
                  }
              }
        );
    }

    /*******************************************************************************
     * private Method.
     *******************************************************************************/
    /**
     * 전면 광고를 로드 한다.
     */
    private void init() {
        Log.e(TAG, ">> init()");
        mInterstitialAd = new InterstitialAd(mActivity);
        mInterstitialAd.setAdUnitId(mActivity.getResources().getString(R.string.sample_id));

        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }
}
