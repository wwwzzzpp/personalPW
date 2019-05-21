
package com.wzp.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class DisplayUtil
{
    /**
     * * 将px值转换为dip或dp值，保证尺寸大小不变 * * @param pxValue * @param scale *
     * （DisplayMetrics类中属性density） * @return
     */
    // CHECKSTYLE:OFF

    public static int px2dip( Context context, float pxValue )
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }

    /**
     * * 将dip或dp值转换为px值，保证尺寸大小不变 * * @param dipValue * @param scale *
     * （DisplayMetrics类中属性density） * @return
     */
    public static int dip2px( Context context, float dipValue )
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    /**
     * * 将px值转换为sp值，保证文字大小不变 * * @param pxValue * @param fontScale *
     * （DisplayMetrics类中属性scaledDensity） * @return
     */
    public static int px2sp( Context context, float pxValue )
    {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue / fontScale + 0.5f);
    }

    /**
     * * 将sp值转换为px值，保证文字大小不变 * * @param spValue * @param fontScale *
     * （DisplayMetrics类中属性scaledDensity） * @return
     */
    public static int sp2px( Context context, float spValue )
    {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue * fontScale + 0.5f);
    }

    // CHECKSTYLE:ON
    /**
     * <对方法的基本功能进行简单描述> 获取屏幕大小
     * 
     * @param context
     * @return
     */
    public static int[] getDisplay( Activity context )
    {
        int[] size = new int[2];
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics( metrics );
        size[0] = metrics.widthPixels;
        size[1] = metrics.heightPixels;
        return size;
    }

    // /**
    // *
    // * <对方法的基本功能进行简单描述>
    // * <详细描述方法实现的具体功能，用户需要遵循的约束，采用的特殊算法或者业务逻辑等>
    // * @param paramContext
    // * @return
    // */
    // public static final BitmapDisplayConfig getAfinalBitmapConfig(Context
    // paramContext)
    // {
    // BitmapDisplayConfig localBitmapDisplayConfig = new BitmapDisplayConfig();
    // Animation localAnimation = AnimationUtils.loadAnimation(paramContext,
    // R.anim.fade_in);
    // localBitmapDisplayConfig.setLoadfailBitmap(ImageUtils.resoureToBitmap(paramContext,
    // R.drawable.lv_icon_null));
    // localBitmapDisplayConfig.setLoadingBitmap(ImageUtils.resoureToBitmap(paramContext,
    // R.drawable.lv_icon_null));
    // localBitmapDisplayConfig.setAnimation(localAnimation);
    // localBitmapDisplayConfig.setAnimationType(0);
    // return localBitmapDisplayConfig;
    // }

}
