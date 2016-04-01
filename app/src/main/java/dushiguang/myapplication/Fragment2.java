package dushiguang.myapplication;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

/**
 * Created by ex-dushiguang201 on 2016-02-24.
 */
@EFragment(R.layout.fragment_2)
public class Fragment2 extends Fragment {

    void afterView() {
        initViews();
    }

    @UiThread
    void initViews() {

    }
}
