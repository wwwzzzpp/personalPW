
package com.wzp.utils;

import java.io.ByteArrayOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * ImageUtils
 * <ul>
 * convert between Bitmap, byte array, Drawable
 * <li>{@link #bitmapToByte(Bitmap)}</li>
 * <li>{@link #bitmapToDrawable(Bitmap)}</li>
 * <li>{@link #byteToBitmap(byte[])}</li>
 * <li>{@link #byteToDrawable(byte[])}</li>
 * <li>{@link #drawableToBitmap(Drawable)}</li>
 * <li>{@link #drawableToByte(Drawable)}</li>
 * </ul>
 * <ul>
 * get image
 * <li>{@link #getInputStreamFromUrl(String, int)}</li>
 * <li>{@link #getBitmapFromUrl(String, int)}</li>
 * <li>{@link #getDrawableFromUrl(String, int)}</li>
 * </ul>
 * <ul>
 * scale image
 * <li>{@link #scaleImageTo(Bitmap, int, int)}</li>
 * <li>{@link #scaleImage(Bitmap, float, float)}</li>
 * </ul>
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2012-6-27
 */
public class ImageUtils
{

    /**
     * convert Bitmap to byte array
     * 
     * @param b
     * @return
     */
    public static byte[] bitmapToByte( Bitmap b )
    {
        if ( b == null )
        {
            return null;
        }

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        b.compress( Bitmap.CompressFormat.PNG, 100, o );
        return o.toByteArray();
    }

    /**
     * convert byte array to Bitmap
     * 
     * @param b
     * @return
     */
    public static Bitmap byteToBitmap( byte[] b )
    {
        return (b == null || b.length == 0) ? null : BitmapFactory
                .decodeByteArray( b, 0, b.length );
    }

    /**
     * convert Drawable to Bitmap
     * 
     * @param d
     * @return
     */
    public static Bitmap drawableToBitmap( Drawable d )
    {
        return d == null ? null : ((BitmapDrawable)d).getBitmap();
    }

    /**
     * <对方法的基本功能进行简单描述> <详细描述方法实现的具体功能，用户需要遵循的约束，采用的特殊算法或者业务逻辑等>
     * 
     * @param context
     * @param id
     * @return
     */
    public static Bitmap resoureToBitmap( Context context, int id )
    {

        return drawableToBitmap( context.getResources().getDrawable( id ) );
    }

    /**
     * convert Bitmap to Drawable
     * 
     * @param b
     * @return
     */
    @SuppressWarnings( "deprecation" )
    public static Drawable bitmapToDrawable( Bitmap b )
    {
        return b == null ? null : new BitmapDrawable( b );
    }

    /**
     * convert Drawable to byte array
     * 
     * @param d
     * @return
     */
    public static byte[] drawableToByte( Drawable d )
    {
        return bitmapToByte( drawableToBitmap( d ) );
    }

    /**
     * convert byte array to Drawable
     * 
     * @param b
     * @return
     */
    public static Drawable byteToDrawable( byte[] b )
    {
        return bitmapToDrawable( byteToBitmap( b ) );
    }

    /**
     * scale image
     * 
     * @param org
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap scaleImageTo( Bitmap org, int newWidth, int newHeight )
    {
        int width = Double.valueOf( newWidth / org.getWidth() ).intValue();
        int height = Double.valueOf( newHeight / org.getHeight() ).intValue();
        return scaleImage( org, width, height );
    }

    /**
     * scale image
     * 
     * @param org
     * @param scaleWidth
     *            sacle of width
     * @param scaleHeight
     *            scale of height
     * @return
     */
    public static Bitmap scaleImage( Bitmap org, float scaleWidth,
            float scaleHeight )
    {
        if ( org == null )
        {
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postScale( scaleWidth, scaleHeight );
        return Bitmap.createBitmap( org, 0, 0, org.getWidth(), org.getHeight(),
                matrix, true );
    }
}
