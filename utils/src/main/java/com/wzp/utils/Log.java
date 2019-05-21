
package com.wzp.utils;

public class Log
{
    @SuppressWarnings( "unused" )
    public static void d( String text )
    {
        if ( Constants.DEBUG && null != text )
        {
            android.util.Log.d( Constants.TAG, text );
        }
    }

    @SuppressWarnings( "unused" )
    public static void e( String text )
    {
        if ( Constants.DEBUG && null != text )
        {
            android.util.Log.e( Constants.TAG, text );
        }
    }

    @SuppressWarnings( "unused" )
    public static void v( String text )
    {
        if ( Constants.DEBUG && null != text )
        {
            android.util.Log.v( Constants.TAG, text );
        }
    }

    @SuppressWarnings( "unused" )
    public static void d( Object object )
    {
        if ( Constants.DEBUG && null != object )
        {
            android.util.Log.d( Constants.TAG, object.toString() );
        }
    }
}
