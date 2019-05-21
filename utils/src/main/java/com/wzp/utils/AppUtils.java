package com.wzp.utils;

import java.util.List;
import java.util.Locale;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;

/**
 * AppUtils
 * <ul>
 * <li>{@link AppUtils#isNamedProcess(Context, String)}</li>
 * </ul>
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-5-07
 */
public class AppUtils
{

    /**
     * whether this process is named with processName
     * 
     * @param context
     * @param processName
     * @return <ul>
     *         return whether this process is named with processName
     *         <li>if context is null, return false</li>
     *         <li>if {@link ActivityManager#getRunningAppProcesses()} is null,
     *         return false</li>
     *         <li>if one process of
     *         {@link ActivityManager#getRunningAppProcesses()} is equal to
     *         processName, return true, otherwise return false</li>
     *         </ul>
     */
    public static boolean isNamedProcess( Context context, String processName )
    {
        if ( context == null )
        {
            return false;
        }

        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager)context
                .getSystemService( Context.ACTIVITY_SERVICE );
        List<RunningAppProcessInfo> processInfoList = manager
                .getRunningAppProcesses();
        if ( processInfoList == null )
        {
            return true;
        }
        // CHECKSTYLE:OFF
        for ( RunningAppProcessInfo processInfo : manager
                .getRunningAppProcesses() )
        {
            if ( processInfo.pid == pid
                    && ObjectUtils.isEquals( processName,
                            processInfo.processName ) )
            {
                return true;
            }
        }
        // CHECKSTYLE:ON
        return false;
    }

    /**
     * whether application is in background
     * <ul>
     * <li>need use permission android.permission.GET_TASKS in Manifest.xml</li>
     * </ul>
     * 
     * @param context
     * @return if application is in background return true, otherwise return
     *         false
     */
    public static boolean isApplicationInBackground( Context context )
    {
        ActivityManager am = (ActivityManager)context
                .getSystemService( Context.ACTIVITY_SERVICE );
        List<RunningTaskInfo> taskList = am.getRunningTasks( 1 );
        if ( taskList != null && !taskList.isEmpty() )
        {
            ComponentName topActivity = taskList.get( 0 ).topActivity;
            if ( topActivity != null
                    && !topActivity.getPackageName().equals(
                            context.getPackageName() ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断系统语言
     * 
     * @return 检测系统语言为中文返回true，否则返回false
     */
    public static boolean getSystemLanguage()
    {
        String lan = Locale.getDefault().getLanguage();
        Log.d( ">>>>>>>>>>>>>>:" + lan );
        if ( "zh".equals( lan ) )
        {
            return true;
        }
        return false;
    }

    /**
     * 判断当前语言是否是简体中文
     * 
     * @return true:是简体中文,false:不是简体中文
     */
    public static boolean isAppLanguageChinese( Context context )
    {
        boolean result = true;
        int language = SharedPreUtils.getIntSettingFromPrefrence( context,
                Constants.LANGUAGE );
        if ( language == 0 )// 中文
        {
            result = true;
        } else if ( language == -1 )
        {
            if ( "zh".equals( context.getResources().getConfiguration().locale
                    .getLanguage() ) )
            {
                result = true;
            } else
            {
                result = false;
            }
        } else
        {
            result = false;
        }
        return result;
    }
}
