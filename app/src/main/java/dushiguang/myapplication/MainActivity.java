package dushiguang.myapplication;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by ex-dushiguang on 2016-02-24.
 */
@EActivity(R.layout.main_activity)
public class MainActivity extends AppCompatActivity {
    @ViewById(R.id.fragmeng1_bt)
    Button mFragmeng1Btn;
    @ViewById(R.id.fragmeng2_bt)
    Button mFragmeng2Btn;
    @ViewById(R.id.viewpager)
    MyViewPager mViewpager;
    @ViewById(R.id.indicator_view)
    View mIndicatorView;

    private Fragment1_ mFragment1;
    private Fragment2_ mFragment2;
    private ViewPageAdapter mAdapter;
    private int fTranslationBegin;
    private int ftranslationEnd;

    @AfterViews
    void afterViews() {
        initViews();
        setIndicatorWidth();
    }

    public void initViews() {
        mAdapter = new ViewPageAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mAdapter);
        mViewpager.setCurrentItem(0);

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    getTranslationPosition();
                    AnimatorUtil.StartXTranslation(mIndicatorView, fTranslationBegin, ftranslationEnd, 300, null);
                } else if (position == 0) {
                    getTranslationPosition();
                    AnimatorUtil.StartXTranslation(mIndicatorView, ftranslationEnd, fTranslationBegin, 300, null);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setIndicatorWidth(){
        WindowManager wm = MainActivity.this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams linearParams =new LinearLayout.LayoutParams(width/2,4);
        mIndicatorView.setLayoutParams(linearParams);
    }

    @Click(R.id.fragmeng1_bt)
    void OnClickBothAgentBt() {
        if(mViewpager.getCurrentItem()==0){
            return;
        }
        mViewpager.setCurrentItem(0);
    }

    @Click(R.id.fragmeng2_bt)
    void OnClickSellerAgentBt() {
        if(mViewpager.getCurrentItem()==1){
            return;
        }
        mViewpager.setCurrentItem(1);
    }

    private void getTranslationPosition(){
        /**
         * int[] location = new  int[2] ;
         * view.getLocationInWindow(location); //获取在当前窗口内的绝对坐标
         * view.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
         * location [0]--->x坐标,location [1]--->y坐标
         */
        int[] position= new int[2];
        mFragmeng1Btn.getLocationInWindow(position);
        fTranslationBegin=position[0];
        int[] position2= new int[2];
        mFragmeng2Btn.getLocationInWindow(position2);
        ftranslationEnd=position2[0];
    }
}
