
package com.wzp.utils;

import android.content.Context;
import android.content.res.Resources;

public class CR
{
    public CR()
    {
    }

    public static int getDrawableId( Context context, String name )
    {
        Resources res = context.getResources();
        return res.getIdentifier( name, "drawable", context.getPackageName() );
    }

    public static int getLayoutId( Context context, String name )
    {
        Resources res = context.getResources();
        return res.getIdentifier( name, "layout", context.getPackageName() );
    }

    public static int getStringsId( Context context, String name )
    {
        Resources res = context.getResources();
        return res.getIdentifier( name, "string", context.getPackageName() );
    }

    public static int getColorId( Context context, String name )
    {
        Resources res = context.getResources();
        return res.getIdentifier( name, "color", context.getPackageName() );
    }

    public static int getIdId( Context context, String name )
    {
        Resources res = context.getResources();
        return res.getIdentifier( name, "id", context.getPackageName() );
    }

    public static int getRawId( Context context, String name )
    {
        Resources res = context.getResources();
        return res.getIdentifier( name, "raw", context.getPackageName() );
    }

    public static int getAnimId( Context context, String name )
    {
        Resources res = context.getResources();
        return res.getIdentifier( name, "anim", context.getPackageName() );
    }

    public static int getStyleId( Context context, String name )
    {
        Resources res = context.getResources();
        return res.getIdentifier( name, "style", context.getPackageName() );
    }

    public static int getStyleableId( Context context, String name )
    {
        Resources res = context.getResources();
        return res.getIdentifier( name, "styleable", context.getPackageName() );
    }

    public static int getAttrId( Context context, String name )
    {
        Resources res = context.getResources();
        return res.getIdentifier( name, "attr", context.getPackageName() );
    }

    public static int getArrayId( Context context, String name )
    {
        Resources res = context.getResources();
        return res.getIdentifier( name, "array", context.getPackageName() );
    }

    public static int getDimenId( Context context, String name )
    {
        Resources res = context.getResources();
        return res.getIdentifier( name, "dimen", context.getPackageName() );
    }

    public static final int[] getResourceDeclareStyleableIntArray(
            Context context, String name )
    {
        // CHECKSTYLE:OFF

        try
        {
            java.lang.reflect.Field[] fields2 = Class.forName(
                    context.getPackageName() + ".R$styleable" ).getFields();

            for ( java.lang.reflect.Field f : fields2 )
            {

                if ( f.getName().equals( name ) )
                {

                    return (int[])f.get( null );
                }
            }
        } catch ( Throwable localThrowable )
        {
            Log.d( "" );
        }
        // CHECKSTYLE:ON
        return null;
    }
}
