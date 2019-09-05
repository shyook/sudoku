package com.example.jumping_i.sudoku_original.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Window;

import com.example.jumping_i.sudoku_original.R;
import com.example.jumping_i.sudoku_original.views.dialog.CustomDialog;

public class DialogUtils {
    public static Dialog mDialog = null;

    public static boolean checkValidate(Activity activity, boolean isDismiss) {
        if (! Utils.checkActivityState(activity)) {
            return false;
        }

        if (isDismiss) {
            dismissDialog();
        }

        return true;
    }

    public static void dismissDialog() {
        dismissDialog(mDialog);
    }

    public static void setDialog(Dialog dialog) {
        mDialog = dialog;
    }

    public static void dismissDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static Dialog showProgress(Activity activity, boolean isCancelabled, DialogInterface.OnCancelListener cancleListener) {
        if (!checkValidate(activity, false)) {
            return null;
        }
        Dialog dialog = new Dialog(activity, R.style.TransDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(isCancelabled);
        dialog.setOwnerActivity(activity);
        dialog.setContentView(R.layout.dialog_progress);
        if (cancleListener != null) {
            dialog.setOnCancelListener(cancleListener);
        }
        dialog.show();
        setDialog(dialog);
        return dialog;
    }

    /**
     * 메세지 팝업 (타이틀[사용자 변경] + 메세지 + 버튼[yes] + 버튼[no])
     *
     * @param activity
     *            {@link Activity} 객체
     * @param title
     *            제목
     * @param msg
     *            메세지
     * @param isCancelable
     *            BackKey 동작여부
     * @param listener
     *            버튼 클릭 이벤트 리스너
     * @return Dialog
     */
    public static Dialog confirm(Activity activity, String title, String msg, boolean isCancelable,
                                 DialogInterface.OnClickListener listener) {
        if (!checkValidate(activity, false)) {
            return null;
        }
        CustomDialog dialog = new CustomDialog(activity);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setPositiveButton(R.string.yes, listener);
        dialog.setNegativeButton(R.string.no, listener);
        dialog.setCloseButton(listener);
        dialog.setCancelable(isCancelable);
        if (isCancelable) {
            dialog.setOnKeyListener(listener);
        }
        dialog.show();
        setDialog(dialog);
        return dialog;
    }

    /**
     * 메세지 팝업 (타이틀[사용자 변경] + 메세지 + 버튼[yes] + 버튼[no]).
     *
     * @param activity
     *            Activity
     * @param title
     *            title
     * @param msg
     *            msg
     * @param listener
     *            OnClickListener
     * @return Dialog
     */
    public static Dialog confirm(Activity activity, String title, String msg, DialogInterface.OnClickListener listener) {
        return confirm(activity, title, msg, true, listener);
    }

    /**
     * 부가 메세지가 있는 메세지 팝업 (타이틀[사용자 변경] + 메세지 + 하단메세지 + 버튼[yes] + 버튼[no]).
     *
     * @param activity
     *            {@link Activity} 객체
     * @param title
     *            제목
     * @param msg
     *            메세지
     * @param subMsg
     *            하단 메세지
     * @param listener
     *            버튼 클릭 이벤트 리스너
     * @return Dialog
     */
    public static Dialog confirmAndSubMsg(Activity activity, String title, String msg, String subMsg,
                                          DialogInterface.OnClickListener listener) {
        if (!checkValidate(activity, false)) {
            return null;
        }
        CustomDialog dialog = new CustomDialog(activity);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setSubMessage(subMsg, false);
        dialog.setPositiveButton(R.string.yes, listener);
        dialog.setNegativeButton(R.string.no, listener);
        dialog.setCloseButton(listener);
        dialog.setCancelable(true);
        dialog.setOnKeyListener(listener);
        dialog.show();
        setDialog(dialog);
        return dialog;
    }

    /**
     * 메세지 팝업 (타이틀[사용자 변경] + 메세지 + 버튼[ok]).
     *
     * @param activity
     *            {@link Activity} 객체
     * @param title
     *            제목
     * @param msg
     *            메세지
     * @return Dialog
     */
    public static Dialog alert(Activity activity, String title, String msg, DialogInterface.OnClickListener listener) {
        if (!checkValidate(activity, false)) {
            return null;
        }
        CustomDialog dialog = new CustomDialog(activity);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setPositiveButton(activity.getApplicationContext().getString(R.string.confirm), listener);
        dialog.setCloseButton(listener);
        dialog.setCancelable(true);
        dialog.show();
        setDialog(dialog);
        return dialog;
    }

    /**
     * 부가 메세지 팝업 (타이틀[사용자 변경] + 메세지 + 하단메세지 + 버튼[사용자 변경]).
     *
     * @param activity
     *            {@link Activity} 객체
     * @param title
     *            제목
     * @param msg
     *            메세지
     * @param subMsg
     *            하단 메세지
     * @param listener
     *            버튼 클릭 이벤트 리스너
     * @return Dialog
     */
    public static Dialog alertAndSubMsg(Activity activity, String title, String msg, String subMsg,
                                        DialogInterface.OnClickListener listener) {
        if (!checkValidate(activity, false)) {
            return null;
        }
        CustomDialog dialog = new CustomDialog(activity);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setSubMessage(subMsg, false);
        dialog.setPositiveButton(R.string.confirm, listener);
        dialog.setCloseButton(listener);
        dialog.setCancelable(true);
        dialog.setOnKeyListener(listener);
        dialog.show();
        setDialog(dialog);
        return dialog;
    }

}
