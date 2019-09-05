package com.example.jumping_i.sudoku_original.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.text.Html;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import android.widget.TextView;

public final class Utils {
    private static long mlLastClickTime = 0;

    private Utils() {
    }

    /**
     * Activity 상태 체크
     * @param activity
     * @return
     */
    public static boolean checkActivityState(Activity activity) {
        return ! (activity == null || activity.isFinishing() || activity.getWindow() == null);
    }

    /**
     * 해당 DP를 pX로 변환한다.
     *
     * @param context
     *            context
     * @param DP
     *            DP
     * @return int
     */
    public static int getDpToPixel(Context context, int DP) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DP, context.getResources().getDisplayMetrics());
        return (int) px;
    }

    /**
     * TextView에 Html 효과 주기.
     *
     * @param v
     *            TextView
     * @param text
     *            text
     */
    public static void fromHtml(final TextView v, final String text) {
        if (!(v instanceof TextView) || null == text) {
            return;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            v.setText(Html.fromHtml(text));
        } else {
            v.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY));
        }
    }

    /**
     * 더블 클릭 여부 체크.
     *
     * @return boolean
     */
    public static boolean isDoubleClick() {
        if (SystemClock.elapsedRealtime() - mlLastClickTime <= ViewConfiguration.getDoubleTapTimeout()) {
            return true;
        }
        mlLastClickTime = SystemClock.elapsedRealtime();
        return false;
    }
}
