
package com.wzp.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.AudioManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

public class PhoneUtils
{
    protected static final String LOG_TAG = PhoneUtils.class.getSimpleName();

    /*
     * private static final int CHINA_MOBILE = 1; private static final int
     * UNICOM = 2; private static final int TELECOMMUNICATIONS = 3; private
     * static final int FAILURE = 0;
     */

    public PhoneUtils()
    {
    }

    public static String getMacAddress( Context context )
    {
        WifiManager manager = (WifiManager)context.getSystemService( "wifi" );
        WifiInfo info = manager.getConnectionInfo();
        return info.getMacAddress();
    }

    private static TelephonyManager getTelManager( Context context )
    {
        return (TelephonyManager)context.getSystemService( "phone" );
    }

    public static String getDeviceID( Context context )
    {
        return getTelManager( context ).getDeviceId();
    }

    public static String getIMSI( Context context )
    {
        return getTelManager( context ).getSubscriberId();
    }

    public static String getProductModel()
    {
        return Build.MODEL;
    }

    public static String getSysReleaseVersion()
    {
        return Build.VERSION.RELEASE;
    }

    public static int getSDKINTVersion()
    {
        return Build.VERSION.SDK_INT;
    }

    public static String getPhoneNumber( Context context )
    {
        return getTelManager( context ).getLine1Number();
    }

    public static int getProviderName( Context context )
    {
        String IMSI = getIMSI( context );
        if ( IMSI == null )
        {
            return 0;
        }
        if ( (IMSI.startsWith( "46000" )) || (IMSI.startsWith( "46002" )) )
        {
            return 1;
        }
        if ( IMSI.startsWith( "46001" ) )
        {
            return 2;
        }
        if ( IMSI.startsWith( "46003" ) )
        {
            return 3;
        }
        return 0;
    }

    private static int getVoice( Context context, int voiceType )
    {
        AudioManager manager = (AudioManager)context.getSystemService( "audio" );
        int voiceValue = manager.getStreamVolume( voiceType );
        return voiceValue;
    }

    public static int getCallVoice( Context context )
    {
        return getVoice( context, 0 );
    }

    public static int getAlarmVoice( Context context )
    {
        return getVoice( context, 4 );
    }

    public static int getRingVoice( Context context )
    {
        return getVoice( context, 2 );
    }

    public static int getMusicVoice( Context context )
    {
        return getVoice( context, 3 );
    }

    public static int getSystemVoice( Context context )
    {
        return getVoice( context, 1 );
    }

    public static String getCpuName()
    {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try
        {
            fileReader = new FileReader( "/pro/cpuinfo" );
            bufferedReader = new BufferedReader( fileReader );
            String string = bufferedReader.readLine();
            String[] strings = string.split( ":\\s+", 2 );
            return strings[1];
        } catch ( FileNotFoundException e )
        {
            Log.e( e.toString() );
        } catch ( IOException e )
        {
            Log.e( e.toString() );
        } finally
        {
            if ( bufferedReader != null )
            {
                try
                {
                    bufferedReader.close();
                } catch ( IOException e )
                {
                    Log.e( e.toString() );
                }
            }
            if ( fileReader != null )
            {
                try
                {
                    fileReader.close();
                } catch ( IOException e )
                {
                    Log.e(   e.toString() );
                }
            }
        }
        return null;
    }

    public static String getVersionName( Context context )
    {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try
        {
            packInfo = packageManager.getPackageInfo( context.getPackageName(),
                    0 );
        } catch ( NameNotFoundException e )
        {
            Log.d( e.getMessage() );
        }

        String version = "";
        if ( packInfo != null )
        {
            version = packInfo.versionName;
        }

        return version;
    }

    public static int getVersionCode( Context context )
    {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try
        {
            packInfo = packageManager.getPackageInfo( context.getPackageName(),
                    0 );
        } catch ( NameNotFoundException e )
        {
            Log.d( e.getMessage() );
        }

        int version = -1;
        if ( packInfo != null )
        {
            version = packInfo.versionCode;
        }

        return version;
    }
}
