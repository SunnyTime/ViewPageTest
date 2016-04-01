package dushiguang.myapplication;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * dushiguang
 * 可以设置使ViewPager不能滑动
 */
public class MyViewPager extends ViewPager {

  private boolean noScroll=false;
  private Context context;


  public MyViewPager(Context context){
    super(context);
  }

  public MyViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
  }


  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    if (noScroll) {
      return false;
    } else {
      return super.onTouchEvent(ev);
    }
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent arg0) {
    if(noScroll){
      return false;
      //false  不能左右滑动
    }else{
      return super.onInterceptTouchEvent(arg0);
    }
  }

  public boolean isScrollble() {
    return noScroll;
  }

  public void setNoScroll(boolean scrollble) {
    this.noScroll = scrollble;
  }
}