package dushiguang.myapplication;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;


/**
 * Created by ex-dushiguang201 on 2016-02-24.
 */
@EFragment(R.layout.fragment_1)
public class Fragment1 extends Fragment {
    @ViewById(R.id.seekbar_dialig_btn)
    Button mSeekbarDialigBtn;

    private DialogUtil dialogUtil;
    private MyDialog mAlertDialog;

    private static final int SEEKBARDIALOG = 1;//含seekbar的dialog
    private static final int CHECKBOXDIALOG = 2;//含checkbox的dialog
    private static final int EDITTEXTDIALOG = 3;//含editText的dialog
    private int whichCheckboxIsChecked = 0;

    @AfterViews
    void afterView() {
        dialogUtil = new DialogUtil();
        initViews();
    }

    @UiThread
    void initViews() {

    }

    @Click(R.id.seekbar_dialig_btn)
    void seekBarDialogClick() {
        showSeekBarDialog(getActivity(), "这是一个含SeekBar的Dialog", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"得到seekBar的值"+mAlertDialog.getSeekBarValue(),Toast.LENGTH_SHORT).show();
            }
        }, SEEKBARDIALOG, 50, 50);
    }

    @UiThread
    void showSeekBarDialog(Context context, String title, View.OnClickListener onOkBtnClickListener,
                                   int dialogTheme, int value1, int value2) {
        dialogUtil.showSeekBarDialog(context, title, onOkBtnClickListener, dialogTheme, value1, value2);
        mAlertDialog = dialogUtil.getMyalterDialog();
    }

    @Click(R.id.checkbox_dialig_btn)
    void checkBoxDialogClick() {
        showCheckBoxDialog(getActivity(),
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        Toast.makeText(getActivity(),"你选择的是第1个CheckBox",Toast.LENGTH_SHORT).show();
                        whichCheckboxIsChecked = 0;
                    }
                },

                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        Toast.makeText(getActivity(),"你选择的是第2个CheckBox",Toast.LENGTH_SHORT).show();
                        whichCheckboxIsChecked = 1;
                    }
                },

                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        Toast.makeText(getActivity(),"你选择的是第3个CheckBox",Toast.LENGTH_SHORT).show();
                        whichCheckboxIsChecked = 2;
                    }
                },
                CHECKBOXDIALOG,
                whichCheckboxIsChecked);
    }
    @UiThread
    void showCheckBoxDialog(Context context,
                            CompoundButton.OnCheckedChangeListener checked1,
                            CompoundButton.OnCheckedChangeListener checked2,
                            CompoundButton.OnCheckedChangeListener checked3,
                            int dialogTheme, int whichCheckboxIsChecked) {
        dialogUtil.showCheckBoxDialog(context, checked1, checked2, checked3, dialogTheme, whichCheckboxIsChecked);
        mAlertDialog = dialogUtil.getMyalterDialog();
    }

    @Click(R.id.edittext_dialog_btn)
    void showEditTextDialog() {
        showEditTextDialog(getContext(), "这是一个含EditText的Dialog",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(),"所天的值是:" + mAlertDialog.getEdiTextValue(),Toast.LENGTH_SHORT).show();
                    }
                },

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                },
                EDITTEXTDIALOG,
                100,
                100
        );
    }
    @UiThread
    void showEditTextDialog(Context context, String title, View.OnClickListener onOkBtnClickListener,
                            View.OnClickListener onDeleteBtnClickListener,
                            int dialogTheme, int value1, int value2) {
        dialogUtil.showEditTextDialog(context, title, onOkBtnClickListener, onDeleteBtnClickListener, dialogTheme, value1, value2);
        mAlertDialog = dialogUtil.getMyalterDialog();
    }

}
