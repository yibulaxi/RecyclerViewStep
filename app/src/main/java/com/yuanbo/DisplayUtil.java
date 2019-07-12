package com.yuanbo;

import android.content.Context;
import android.util.DisplayMetrics;

public class DisplayUtil {
    /**
     * @param context
     * @param dipValue
     * @return
     * @brief DP转像素
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 获取屏幕宽度
     */
    public static int getWindowWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

}
