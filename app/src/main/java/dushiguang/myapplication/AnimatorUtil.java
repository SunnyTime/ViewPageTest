package dushiguang.myapplication;

import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;

public class AnimatorUtil {
    /**
     * 平移动画
     * @param view
     * @param begin
     * @param end
     * @param durtion
     * @param listener 动画事件监听
     */
        public static void StartXTranslation(View view,float begin,float end,int durtion,AnimatorListenerAdapter listener){
            ObjectAnimator animator= ObjectAnimator.ofFloat(view, "translationX", begin, end);
            animator.setDuration(durtion);
            if(listener!=null){
                animator.addListener(listener);
            }
            animator.start();

        }
}
