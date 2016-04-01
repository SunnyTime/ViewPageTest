package dushiguang.myapplication;


import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * dushiguang
 */
public class MyDialog extends Dialog {

    private Context context;
    private TextView mTitle;
    private TextView mConfirm;
    private TextView mCancel;
    private SeekBar mSeekBar;
    private EditText mBuyerPayEt;
    private TextView mBuyerPay;
    private TextView mSellerPay;

    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;

    private int dialogTheme = 1;
    private float mTotalValue;
    private int mBuyerPayValue;
    boolean mCanDismiss = true;

    public MyDialog(Context context) {
        super(context);
        init(context);
    }

    public MyDialog(int dialogTheme, Context context) {
        super(context);
        this.dialogTheme = dialogTheme;
        init(context);
    }

    public MyDialog(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }



    private void init(Context context) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (dialogTheme == 1){
            View view = LayoutInflater.from(context).inflate(R.layout.lib_layout_progressdialog, null);
            setContentView(view);
            setCanceledOnTouchOutside(mCanDismiss ? true : false);//点击对话框外面可以消失
            mTitle = (TextView) view.findViewById(R.id.md_title);
            mSeekBar = (SeekBar) view.findViewById(R.id.seekBar);
            mBuyerPay = (TextView)view.findViewById(R.id.buyer_pay_tv);
            mSellerPay = (TextView) view.findViewById(R.id.seller_pay_tv);
            mConfirm = (TextView) view.findViewById(R.id.md_confirm);
            mSeekBar.setOnSeekBarChangeListener(new seekBarChange());
        } else if (dialogTheme == 2) {
            View view = LayoutInflater.from(context).inflate(R.layout.lib_layout_checkboxdialog, null);
            setContentView(view);
            setCanceledOnTouchOutside(mCanDismiss ? true : false);//点击对话框外面可以消失
            checkBox1 = (CheckBox) view.findViewById(R.id.shuifei_both_pay);
            checkBox2 = (CheckBox) view.findViewById(R.id.shuifei_jia_pay);
            checkBox3 = (CheckBox) view.findViewById(R.id.shuifei_yi_pay);
        } else if(dialogTheme == 3) {
            View view = LayoutInflater.from(context).inflate(R.layout.lib_layout_edittextdialog, null);
            setContentView(view);
            setCanceledOnTouchOutside(mCanDismiss ? true : false);
            mTitle = (TextView) view.findViewById(R.id.md_title);
            mBuyerPayEt = (EditText)view.findViewById(R.id.buyer_pay_et);
            mSellerPay = (TextView) view.findViewById(R.id.seller_pay_tv);
            mConfirm = (TextView) view.findViewById(R.id.md_confirm);
            mCancel = (TextView) view.findViewById(R.id.md_delete);

            mBuyerPayEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!TextUtils.isEmpty(mBuyerPayEt.getText())) {
                        if (Integer.valueOf(mBuyerPayEt.getText().toString()) > mTotalValue) {
                            mBuyerPayEt.setText(String.valueOf((int) mTotalValue));
                        }
                        mSellerPay.setText(String.valueOf((int) mTotalValue - Integer.valueOf(mBuyerPayEt.getText().toString())));
                        mConfirm.setEnabled(true);
                    } else {
                        mConfirm.setEnabled(false);
                        mSellerPay.setText(String.valueOf((int) mTotalValue));
                    }
                }
            });
        }
    }

    private class seekBarChange implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            int value = seekBar.getProgress();
            mBuyerPayValue = (int)(mTotalValue * value / 100);
            mBuyerPay.setText((int)mBuyerPayValue + "元");
            mSellerPay.setText((int)(mTotalValue - mBuyerPayValue) + "元");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    public void setOnComfirmClick(final View.OnClickListener listener) {
        setOnComfirmClick(listener, false);
    }

    public void setOnComfirmClick(final View.OnClickListener listener,final boolean donotDismiss) {
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(v);
                if (!donotDismiss)
                    dismiss();
            }
        });
    }

    public void setOnCancelClick(final View.OnClickListener listener) {
        if (listener != null) {
            mCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(v);
                    }
                    dismiss();
                }
            });
        }
        else {
            mCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog.this.dismiss();
                }
            });
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle.setText(title);
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(context.getString(titleId));
    }


    public void setConfirm(String confirm) {
        mConfirm.setText(confirm);
    }

    public void setCancel(String cancel) {
        mCancel.setText(cancel);
    }

    public void setCancelGone() {
        mCancel.setVisibility(View.GONE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && !mCanDismiss) return true;
        else
            return super.onKeyDown(keyCode, event);
    }

    public void setmCanDismiss(boolean mCanDismiss) {
        this.mCanDismiss = mCanDismiss;
        setCanceledOnTouchOutside(mCanDismiss);
    }

    /**
     * 设置SeekBar上代表的总值
     * @param buyPayValue
     * @param sellerPayValue
     */
    public void setTotalValue(int buyPayValue, int sellerPayValue) {
        int value;
        this.mTotalValue = buyPayValue + sellerPayValue;
        value = (int)((buyPayValue / mTotalValue) * 100);
        mSeekBar.setProgress(value);

        mBuyerPay.setText(buyPayValue + "元");
        mSellerPay.setText(sellerPayValue + "元");
    }

    public float getSeekBarValue() {
        return mBuyerPayValue;
    }

    public void setEditTextlValue(int buyPayValue, int sellerPayValue) {
        this.mTotalValue = buyPayValue + sellerPayValue;
        mBuyerPayEt.setText(String.valueOf(buyPayValue));
        mSellerPay.setText(String.valueOf(sellerPayValue));
    }

    public int getEdiTextValue() {
        mBuyerPayValue = Integer.valueOf(mBuyerPayEt.getText().toString());
        return mBuyerPayValue;
    }

    public void setChecbBoxChangeListener(final CompoundButton.OnCheckedChangeListener checkedChangeListener,
                                          final CompoundButton.OnCheckedChangeListener checkedChangeListener2,
                                          final CompoundButton.OnCheckedChangeListener checkedChangeListener3) {
        if (checkedChangeListener != null) {
            checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (checkedChangeListener != null) {
                        checkedChangeListener.onCheckedChanged(buttonView, isChecked);
                    }

                    dismiss();
                }
            });
        }
        else {
            checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    MyDialog.this.dismiss();
                }
            });
        }

        if (checkedChangeListener2 != null) {
            checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkedChangeListener2.onCheckedChanged(buttonView, isChecked);
                    dismiss();
                }
            });
        }
        else {
            checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    MyDialog.this.dismiss();
                }
            });
        }

        if (checkedChangeListener3 != null) {
            checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkedChangeListener3.onCheckedChanged(buttonView, isChecked);
                    dismiss();
                }
            });
        }
        else {
            checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    MyDialog.this.dismiss();
                }
            });
        }
    }

    public void setCheckBoxisChecked(int which) {
        if (which == 0) {
            checkBox1.setChecked(true);
            checkBox2.setChecked(false);
            checkBox3.setChecked(false);
        } else if (which == 1) {
            checkBox1.setChecked(false);
            checkBox2.setChecked(true);
            checkBox3.setChecked(false);
        } else if (which == 2) {
            checkBox1.setChecked(false);
            checkBox2.setChecked(false);
            checkBox3.setChecked(true);
        }
    }

}


