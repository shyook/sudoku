package com.example.jumping_i.sudoku_original.views.ad.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.example.jumping_i.sudoku_original.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

/**
 * 종료 팝업시 광고 노출을 위한 다이얼 로그.
 */
public class NativeAdDialog extends Dialog {
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    private NativeExpressAdView mAdView;
    private Button mConfirm;
    private Button mCancel;

    public NativeAdDialog(@NonNull Context context) {
        super(context);
    }

    /*******************************************************************************
     * Life Cycle.
     *******************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_ad_finish);

        init();
    }

    /*******************************************************************************
     * View Implement.
     *******************************************************************************/
    public void init() {
        mAdView = findViewById(R.id.dialog_native_ad);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mCancel = findViewById(R.id.dialog_finish_cancel);
        mConfirm = findViewById(R.id.dialog_finish_confirm);

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
