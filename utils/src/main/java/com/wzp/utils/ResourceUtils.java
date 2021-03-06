
package com.wzp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

/**
 * ResourceUtils
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2012-5-26
 */
public class ResourceUtils
{

    enum ResourceType
    {
        ASSETS_TAG, STREAM_TAG
    }

    /**
     * get an asset using ACCESS_STREAMING mode. This provides access to files
     * that have been bundled with an application as assets -- that is, files
     * placed in to the "assets" directory.
     * 
     * @param context
     * @param fileName
     *            The name of the asset to open. This name can be hierarchical.
     * @return
     */
    public static String geFileFromAssets( Context context, String fileName )
    {
        if ( context == null || StringUtils.isEmpty( fileName ) )
        {
            return null;
        }

        return getFileAll( context, fileName, ResourceType.ASSETS_TAG );
    }

    /**
     * get content from a raw resource. This can only be used with resources
     * whose value is the name of an asset files -- that is, it can be used to
     * open drawable, sound, and raw resources; it will fail on string and color
     * resources.
     * 
     * @param context
     * @param resId
     *            The resource identifier to open, as generated by the appt
     *            tool.
     * @return
     */
    public static String geFileFromRaw( Context context, int resId )
    {
        if ( context == null )
        {
            return null;
        }
        String res = Integer.toString( resId );
        return getFileAll( context, res, ResourceType.STREAM_TAG );
    }

    /**
     * @param context
     * @param res
     * @param tag
     * @return
     * @description 目的减少代码重复，参数上文说明
     * @added by chenmingcan
     * @data 2015.04.25
     */
    private static String getFileAll( Context context, String res,
            ResourceType tag )
    {
        StringBuilder s = new StringBuilder();
        BufferedReader br = null;
        InputStreamReader in = null;
        try
        {
            switch ( tag )
            {
                case ASSETS_TAG:
                    in = new InputStreamReader( context.getResources()
                            .getAssets().open( res ) );
                    break;
                case STREAM_TAG:
                    int resId = Integer.parseInt( res );
                    in = new InputStreamReader( context.getResources()
                            .openRawResource( resId ) );
                    break;
                default:
                    break;
            }
            br = new BufferedReader( in );
            String line = "";
            // CHECKSTYLE:OFF
            while ( (line = br.readLine()) != null )
            // CHECKSTYLE:ON
            {
                s.append( line );
            }
            br.close();
            return s.toString();
        } catch ( IOException e )
        {
            Log.d( e.getMessage() );
            return null;
        } catch ( NumberFormatException e )
        {
            Log.e( e.getMessage() );
            return null;
        } finally
        {
            if ( br != null )
            {
                try
                {
                    br.close();
                } catch ( IOException e )
                {
                    Log.d( e.getMessage() );
                }
            }
        }
    }

    /**
     * same to {@link ResourceUtils#geFileFromAssets(Context, String)}, but
     * return type is List<String>
     * 
     * @param context
     * @param fileName
     * @return
     */
    public static List<String> geFileToListFromAssets( Context context,
            String fileName )
    {
        if ( context == null || StringUtils.isEmpty( fileName ) )
        {
            return null;
        }

        List<String> fileContent = new ArrayList<String>(
                Constants.INIT_VECTOR_SIZE );
        BufferedReader br = null;
        try
        {
            InputStreamReader in = new InputStreamReader( context
                    .getResources().getAssets().open( fileName ) );
            br = new BufferedReader( in );
            String line = "";
            // CHECKSTYLE:OFF
            while ( (line = br.readLine()) != null )
            // CHECKSTYLE:ON
            {
                fileContent.add( line );
            }
            br.close();
            return fileContent;
        } catch ( IOException e )
        {
            Log.d( e.getMessage() );
            return null;
        } finally
        {
            if ( br != null )
            {
                try
                {
                    br.close();
                } catch ( IOException e )
                {
                    Log.d( e.getMessage() );
                }
            }
        }
    }

    /**
     * same to {@link ResourceUtils#geFileFromRaw(Context, int)}, but return
     * type is List<String>
     * 
     * @param context
     * @param resId
     * @return
     */
    public static List<String> geFileToListFromRaw( Context context, int resId )
    {
        if ( context == null )
        {
            return null;
        }

        List<String> fileContent = new ArrayList<String>(
                Constants.INIT_VECTOR_SIZE );
        BufferedReader reader = null;
        try
        {
            InputStreamReader in = new InputStreamReader( context
                    .getResources().openRawResource( resId ) );
            reader = new BufferedReader( in );
            String line = null;
            // CHECKSTYLE:OFF
            while ( (line = reader.readLine()) != null )
            // CHECKSTYLE:ON
            {
                fileContent.add( line );
            }
            reader.close();
            return fileContent;
        } catch ( IOException e )
        {
            Log.d( e.getMessage() );
            return null;
        } finally
        {
            if ( reader != null )
            {
                try
                {
                    reader.close();
                } catch ( IOException e )
                {
                    Log.d( e.getMessage() );
                }
            }
        }
    }
}
