package dushiguang.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;

/**
 * Created by ex-dushiguang on 2016-02-23.
 */
public class DialogUtil {

    private MyDialog alertDialog;

    /**
     * 显示含EditText的提示dialog
     *
     * @param title
     */
    public void showEditTextDialog(Context context, String title, View.OnClickListener onOkBtnClickListener,
                                   View.OnClickListener onDeleteBtnClickListener,
                                  int dialogTheme, int value1, int value2) {
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return;
        }

        try {
            if (null != alertDialog) {
                if (alertDialog.isShowing()) {
                    return;
                }

                if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {
                    alertDialog.dismiss();
                }
                alertDialog = null;
            }

            alertDialog = new MyDialog(dialogTheme, context);
            alertDialog.setTitle(title);
            //设置editext的值,初始值是两个值的和,修改editext中的一个值另一个值等于(总值-editext中的值)
            alertDialog.setEditTextlValue(value1, value2);
            alertDialog.setOnComfirmClick(onOkBtnClickListener);
            alertDialog.setOnCancelClick(onDeleteBtnClickListener);
            alertDialog.setmCanDismiss(false);
            alertDialog.show();
        } catch (Exception e) {

        }
    }

    /**
     * 显示含SeekBar的提示dialog
     *
     * @param title
     */
    public void showSeekBarDialog(Context context, String title, View.OnClickListener onOkBtnClickListener,
                                  int dialogTheme, int value1, int value2) {
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return;
        }

        try {
            if (null != alertDialog) {
                if (alertDialog.isShowing()) {
                    return;
                }

                if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {
                    alertDialog.dismiss();
                }
                alertDialog = null;
            }

            alertDialog = new MyDialog(dialogTheme, context);
            alertDialog.setTitle(title);
            //设置总值,也就是seekBar代表的总值.
            alertDialog.setTotalValue(value1, value2);
            alertDialog.setOnComfirmClick(onOkBtnClickListener);
            alertDialog.setmCanDismiss(false);
            alertDialog.show();
        } catch (Exception e) {
        }
    }

    /**
     * 显示含CheckBox的提示dialog
     * @param context
     * @param dialogTheme
     */
    public void showCheckBoxDialog(Context context,
                                      CompoundButton.OnCheckedChangeListener checkedChangeListener,
                                      CompoundButton.OnCheckedChangeListener checkedChangeListener2,
                                      CompoundButton.OnCheckedChangeListener checkedChangeListener3,
                                      int dialogTheme, int whichCheckboxIsChecked) {
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return;
        }

        try {
            if (null != alertDialog) {
                if (alertDialog.isShowing()) {
                    return;
                }

                if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {
                    alertDialog.dismiss();
                }
                alertDialog = null;
            }

            alertDialog = new MyDialog(dialogTheme, context);
            alertDialog.setChecbBoxChangeListener(checkedChangeListener, checkedChangeListener2, checkedChangeListener3);
            alertDialog.setCheckBoxisChecked(whichCheckboxIsChecked);
            alertDialog.setmCanDismiss(false);
            alertDialog.show();
        } catch (Exception e) {
        }
    }

    public MyDialog getMyalterDialog() {
        return alertDialog;
    }
}
