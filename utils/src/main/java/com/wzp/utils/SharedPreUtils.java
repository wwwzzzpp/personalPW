
package com.wzp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


/**
 * @author kWX238084
 * @TODO 类功能描述：
 * @date 2014-10-14上午9:57:17
 */
public class SharedPreUtils
{

    /**
     * @TODO 参数说明和方法作用：保存信息到xml文件中
     * @2014-10-14 2014-10-14上午9:57:13
     */
    public static void saveSettingToPrefrence( Context context, String key,
            boolean value )
    {
        SharedPreferences setting = context.getSharedPreferences(
                Constants.SCT_PREFERENCES, Context.MODE_APPEND );
        Editor settingEditor = setting.edit();
        settingEditor.putBoolean( key, value );
        settingEditor.commit();
    }

    /**
     * @TODO 参数说明和方法作用：获取保存在xml文件中的值
     * @2014-10-14 2014-10-14上午9:57:46
     */
    public static boolean getSettingFromPrefrence( Context context, String key )
    {
        SharedPreferences setting = context.getSharedPreferences(
                Constants.SCT_PREFERENCES, Context.MODE_APPEND );
        return setting.contains( key ) ? setting.getBoolean( key, false )
                : false;
    }

    /**
     * @TODO 参数说明和方法作用：获取保存在xml文件中的值int
     * @2014-10-14 2014-10-14上午9:57:46
     */
    public static int getIntSettingFromPrefrence( Context context, String key )
    {
        SharedPreferences setting = context.getSharedPreferences(
                Constants.SCT_PREFERENCES, Context.MODE_APPEND );
        return setting.contains( key ) ? setting.getInt( key, -1 ) : -1;
    }

    public static void saveIntSettingToPrefrence( Context context, String key,
            int value )
    {
        SharedPreferences setting = context.getSharedPreferences(
                Constants.SCT_PREFERENCES, Context.MODE_APPEND );
        Editor settingEditor = setting.edit();
        settingEditor.putInt( key, value );
        settingEditor.commit();
    }
}
