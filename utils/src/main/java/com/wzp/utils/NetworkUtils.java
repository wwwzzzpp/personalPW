
package com.wzp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class NetworkUtils
{

    public static final int NONE = 0;

    public static final int WIFI = 1;

    public static final int MOBILE = 2;

    public NetworkUtils()
    {
    }

    public static int getNetworkState( Context context )
    {
        ConnectivityManager connManager = (ConnectivityManager)context
                .getSystemService( "connectivity" );

        NetworkInfo wifiNet = connManager.getNetworkInfo( 1 );

        if ( (wifiNet != null)
                && ((wifiNet.getState() == NetworkInfo.State.CONNECTED) || (wifiNet
                        .getState() == NetworkInfo.State.CONNECTING)) )
        {
            return 1;
        }

        NetworkInfo mobileNet = connManager.getNetworkInfo( 0 );

        if ( (mobileNet != null)
                && ((mobileNet.getState() == NetworkInfo.State.CONNECTED) || (mobileNet
                        .getState() == NetworkInfo.State.CONNECTING)) )
        {
            return 2;
        }
        return 0;
    }

    public static boolean isConnectivityAvailable( Context context )
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)context
                .getSystemService( "connectivity" );

        if ( connectivityManager == null )
        {
            return false;
        }

        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
        if ( networkInfos == null )
        {
            return false;
        }

        for ( int i = 0; i < networkInfos.length; i++ )
        {
            if ( NetworkInfo.State.CONNECTED == networkInfos[i].getState() )
            {
                return true;
            }
        }

        return false;
    }

    public static void showToast( Context context, int resouceId )
    {
        Toast toast = Toast.makeText( context, resouceId, Toast.LENGTH_LONG );
        toast.setGravity( 17, 0, 0 );
        toast.show();
    }

    public static String getLocalMacAddress( Context context )
    {
        WifiManager wifi = (WifiManager)context.getSystemService( "wifi" );
        WifiInfo info = wifi.getConnectionInfo();
        String wifiAddress = info.getMacAddress();
        if ( (wifiAddress == null) || ("".equals( wifiAddress.trim() )) )
        {
            if ( !wifi.isWifiEnabled() )
            {
                wifi.setWifiEnabled( true );

                wifiAddress = getWifiTimes( 50, wifi );

                if ( wifi.isWifiEnabled() )
                {
                    wifi.setWifiEnabled( false );
                }
            }
        }

        return wifiAddress != null ? wifiAddress : "";
    }

    public static String getWifiTimes( int times, WifiManager wifi )
    {
        String macAddress = "";
        for ( int index = 0; index < times; index++ )
        {
            if ( index != 0 )
            {
                // CHECKSTYLE:OFF
                try
                {
                    Thread.sleep( 100L );
                } catch ( InterruptedException e )
                {
                    Log.e( e.toString() );
                }
                // CHECKSTYLE:ON
            }
            macAddress = wifi.getConnectionInfo().getMacAddress();
            if ( (macAddress != null) && (!"".equals( macAddress )) )
            {
                break;
            }
        }
        return macAddress;
    }
}
