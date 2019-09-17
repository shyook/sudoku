package com.example.jumping_i.sudoku_original.views.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.jumping_i.sudoku_original.R;
import com.example.jumping_i.sudoku_original.utils.Utils;

public class CustomDialog extends Dialog {
    /*******************************************************************************
     * Constant.
     *******************************************************************************/
    private static final String TAG = CustomDialog.class.getSimpleName();

    /*******************************************************************************
     * . Variable
     *******************************************************************************/
    protected LinearLayout mParentLL;
    protected TextView mTitleTV;
    protected LinearLayout mContentLL;
    protected ScrollView mMsgSV;
    protected TextView mMsgTV;
    protected TextView mSubMsgTV;
    protected CheckBox mCheckCB;
    // protected EditText mInputET;
    private LinearLayout mButtonLL;
    protected Button mPositiveBT;
    protected Button mNegativeBT;
    protected LinearLayout mCloseBT;
    private RelativeLayout topPanel;
    protected LayoutInflater mInflater;
    protected int mBtnCnt;
    protected boolean mIsCancelable = true;

    private int mWidthDimensId;
    private int mMidHeightDimensId;

    public CustomDialog(@NonNull Context context) {
        super(context, R.style.AppDialog);
        setOwnerActivity((Activity) context);
        setContentView(R.layout.dialog_default);

        initUI();
    }

    /**
     * . 자원 초기화
     */
    private void initUI() {
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setCanceledOnTouchOutside(false);

        this.mInflater = LayoutInflater.from(getContext());

        mParentLL = (LinearLayout) findViewById(R.id.parentPanel);
        mTitleTV = (TextView) findViewById(R.id.alertTitle);
        mContentLL = (LinearLayout) findViewById(R.id.contentPanel);
        mMsgSV = (ScrollView) findViewById(R.id.scrollView);
        mMsgTV = (TextView) findViewById(R.id.message);
        mSubMsgTV = (TextView) findViewById(R.id.subMessage);
        mButtonLL = (LinearLayout) findViewById(R.id.buttonPanel);
        mPositiveBT = (Button) findViewById(R.id.btn_positive);
        mNegativeBT = (Button) findViewById(R.id.btn_negative);
        topPanel = (RelativeLayout) findViewById(R.id.topPanel);
        mCloseBT = (LinearLayout) findViewById(R.id.btn_close);

        if (0 < mWidthDimensId) {
            int nPopupWidth = Utils.getDpToPixel(getContext(), mWidthDimensId);

            DisplayMetrics metrics = new DisplayMetrics();
            getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int nDisplayWidth = metrics.widthPixels;

            int nMargin = 0;
            if (nDisplayWidth <= nPopupWidth) {
                nMargin = Utils.getDpToPixel(getContext(), 10);
            } else {
                nMargin = (nDisplayWidth - nPopupWidth) / 2;
            }

            RelativeLayout.LayoutParams oParams = (RelativeLayout.LayoutParams) mParentLL.getLayoutParams();
            oParams.width = nDisplayWidth - (nMargin * 2);
            oParams.setMargins(nMargin, nMargin, nMargin, nMargin);
            mParentLL.setLayoutParams(oParams);
        }

        if (0 < mMidHeightDimensId) {
            ScrollView.LayoutParams oParams2 = (ScrollView.LayoutParams) mContentLL.getLayoutParams();
            oParams2.height = Utils.getDpToPixel(getContext(), mMidHeightDimensId);
            mContentLL.setLayoutParams(oParams2);
            mContentLL.setPadding(0, 0, 0, 0);
        }
    }

    /**
     * 타이틀 입력.
     * @param titleId
     */
    public void setTitle(int titleId) {
        if (mTitleTV == null) {
            return;
        }
        mTitleTV.setText(titleId);
    }

    /**
     * 타이틀 입력.
     * @param title
     */
    public void setTitle(CharSequence title) {
        if (mTitleTV == null) {
            return;
        }
        mTitleTV.setText(title);
    }

    /**
     * 메세지 입력.
     *
     * @param message
     *            메세지
     */
    public void setMessage(String message) {
        if (mMsgTV == null) {
            return;
        }
        String strChangeHttpFormatText = message.replace("\n", "<BR>");
        strChangeHttpFormatText = strChangeHttpFormatText.replace("<![CDATA[", "");
        strChangeHttpFormatText = strChangeHttpFormatText.replace("]]>", "");

        Utils.fromHtml(mMsgTV, strChangeHttpFormatText);
        mMsgTV.setVisibility(View.VISIBLE);
        if (mMsgSV != null) {
            mMsgSV.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 메세지 입력.
     *
     * @param messageId
     *            메세지 리소스 id
     */
    public void setMessage(int messageId) {
        if (mMsgTV == null) {
            return;
        }
        String text = getContext().getString(messageId);
        setMessage(text);
    }

    /**
     * 하단 메세지 입력.
     *
     * @param message
     *            메세지
     * @param isBoxStyle
     *            박스 내에 텍스트 포함 여부
     */
    public void setSubMessage(String message, boolean isBoxStyle) {
        if (mMsgTV == null) {
            return;
        }
        String strChangeHttpFormatText = message.replace("\n", "<BR>");
        strChangeHttpFormatText = strChangeHttpFormatText.replace("<![CDATA[", "");
        strChangeHttpFormatText = strChangeHttpFormatText.replace("]]>", "");

        Utils.fromHtml(mSubMsgTV, strChangeHttpFormatText);
        mSubMsgTV.setVisibility(View.VISIBLE);
        if (mMsgSV != null) {
            mMsgSV.setVisibility(View.VISIBLE);
        }

        if (isBoxStyle) {
            // noinspection ResourceType
            mSubMsgTV.setBackgroundResource(R.drawable.background_f9f9f9_line_e2e2e2);
            int padding = Utils.getDpToPixel(getContext(), 30);
            mSubMsgTV.setPadding(padding, padding, padding, padding);
        }
    }

    /**
     * 하단 메세지 입력.
     *
     * @param messageId
     *            메세지 리소스 id
     * @param isBoxStyle
     *            박스 내에 텍스트 포함 여부
     */
    public void setSubMessage(int messageId, boolean isBoxStyle) {
        if (mSubMsgTV == null) {
            return;
        }
        String text = getContext().getString(messageId);
        setSubMessage(text, isBoxStyle);
    }

    /**
     * Custom View 추가.
     *
     * @param layoutId
     *            내용 구성 layout
     */
    public void setCustomView(int layoutId) {
        View oView = mInflater.inflate(layoutId, null);
        mContentLL.addView(oView);
    }

    /**
     * Custom View 추가.
     *
     * @param view
     *            내용 구성 View
     */
    public void setCustomView(View view) {
        mContentLL.addView(view);
    }

    /**
     * positive 버튼 추가.
     *
     * @param text
     *            버튼 라벨
     * @param listener
     *            {@link OnClickListener} 클릭 이벤트 리스너
     */
    public void setPositiveButton(String text, OnClickListener listener) {
        mPositiveBT.setText(text);
        setButton(mPositiveBT, listener);
    }

    /**
     * positive 버튼 추가.
     *
     * @param textId
     *            버튼 라벨 리소스 id
     * @param listener
     *            {@link OnClickListener} 클릭 이벤트 리스너
     */
    public void setPositiveButton(int textId, OnClickListener listener) {
        mPositiveBT.setText(textId);
        setButton(mPositiveBT, listener);
    }

    /**
     * negative 버튼 추가.
     *
     * @param text
     *            버튼 라벨
     * @param listener
     *            {@link OnClickListener} 클릭 이벤트 리스너
     */
    public void setNegativeButton(String text, OnClickListener listener) {
        mNegativeBT.setText(text);
        setButton(mNegativeBT, listener);
    }

    /**
     * negative 버튼 추가.
     *
     * @param textId
     *            버튼 라벨 리소스 id
     * @param listener
     *            {@link OnClickListener} 클릭 이벤트 리스너
     */
    public void setNegativeButton(int textId, OnClickListener listener) {
        mNegativeBT.setText(textId);
        setButton(mNegativeBT, listener);
    }

    /**
     * close 버튼 추가.
     *
     * @param listener
     *            {@link OnClickListener} 클릭 이벤트 리스너
     */
    public void setCloseButton(OnClickListener listener) {
        setButton(mCloseBT, listener);
    }

    /**
     * 상단 Close 버튼 디스플레이 여부.
     *
     * @param isShow true : 디스플레이
     */
    public void setCloseButtonVisible(boolean isShow) {
        if (isShow) {
            mCloseBT.setVisibility(View.VISIBLE);
        } else {
            mCloseBT.setVisibility(View.GONE);
        }
    }

    /**
     * . 버튼 추가 및 버튼 클릭 이벤트 처리
     *
     * @param btn
     *            버튼 View
     * @param listener
     *            {@link OnClickListener} 클릭 이벤트 리스너
     */
    protected void setButton(final View btn, final OnClickListener listener) {
        if (btn == null) {
            return;
        }
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Utils.isDoubleClick()) {
                    return;
                }

                if (listener != null) {
                    if (v.getId() == R.id.btn_positive) {
                        listener.onClick(CustomDialog.this, DialogInterface.BUTTON_POSITIVE);
                    } else if (v.getId() == R.id.btn_negative) {
                        listener.onClick(CustomDialog.this, DialogInterface.BUTTON_NEGATIVE);
                    } else if (v.getId() == R.id.btn_close) {
                        listener.onClick(CustomDialog.this, DialogInterface.BUTTON_NEUTRAL);
                    }
                }
            }
        });

        if (btn.getId() != R.id.btn_close) {
            mBtnCnt++;
        }
    }

    /**
     * BackKey 이벤트 처리.
     *
     * @param listener
     *            {@link OnClickListener}
     */
    public void setOnKeyListener(final OnClickListener listener) {
        setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP
                        && !event.isCanceled()) {
                    if (mIsCancelable) {
                        dialog.dismiss();
                    }
                    if (listener != null) {
                        listener.onClick(dialog, DialogInterface.BUTTON_NEUTRAL);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
        mIsCancelable = flag;
    }

    @Override
    public void show() {
        if (mCheckCB != null && mCheckCB.getVisibility() == View.VISIBLE) {
            mContentLL.setPadding(mContentLL.getPaddingLeft(), mContentLL.getPaddingTop(), //
                    mContentLL.getPaddingRight(), Utils.getDpToPixel(getContext(), 15));
        }

        if (mBtnCnt == 0) {
            if (mButtonLL != null) {
                mButtonLL.setVisibility(View.GONE);
            }
        }

        if (Utils.checkActivityState(getOwnerActivity())) {
            try {
                super.show();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }
}
