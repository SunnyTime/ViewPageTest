package dushiguang.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dushiguang on 2016-02-24.
 */
public class ViewPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentList;
    private Fragment1_ mFragment1;
    private Fragment2_ mFragment2;
    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
        fragmentList= new ArrayList<Fragment>();
        mFragment1= new Fragment1_();
        fragmentList.add(mFragment1);
        mFragment2= new Fragment2_();
        fragmentList.add(mFragment2);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
