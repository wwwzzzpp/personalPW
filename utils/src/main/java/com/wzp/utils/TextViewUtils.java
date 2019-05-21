
package com.wzp.utils;

import java.util.ArrayList;

import android.graphics.Paint;
import android.text.TextUtils;
import android.widget.TextView;

public class TextViewUtils
{
    public static void justify( TextView textView, float contentWidth )
    {
        String text = textView.getText().toString();
        String tempText = "";
        String resultText = "";
        Paint paint = textView.getPaint();

        ArrayList<String> paraList = new ArrayList<String>(
                Constants.INIT_VECTOR_SIZE );
        paraList = paraBreak( text, textView );
        int size = paraList.size();
        ArrayList<String> lineList = null;
        for ( int i = 0; i < size; i++ )
        {
            lineList = lineBreak( paraList.get( i ).trim(), paint, contentWidth );
            tempText = TextUtils.join( " ", lineList )
                    .replaceFirst( "\\s*", "" );
            resultText += tempText.replaceFirst( "\\s*", "" ) + "\n";
        }

        textView.setText( resultText );
    }

    // 分开每个段落
    public static ArrayList<String> paraBreak( String text, TextView textview )
    {
        ArrayList<String> paraList = new ArrayList<String>(
                Constants.INIT_VECTOR_SIZE );
        String[] paraArray = text.split( "\\n+" );
        // CHECKSTYLE:OFF
        for ( String para : paraArray )
        // CHECKSTYLE:ON
        {
            paraList.add( para );
        }
        return paraList;
    }

    // 分开每一行，使每一行填入最多的单词数
    private static ArrayList<String> lineBreak( String text, Paint paint,
            float contentWidth )
    {
        String[] wordArray = text.split( "\\s" );
        ArrayList<String> lineList = new ArrayList<String>(
                Constants.INIT_VECTOR_SIZE );
        String myText = "";
        // CHECKSTYLE:OFF
        for ( String word : wordArray )
        {
            if ( paint.measureText( myText + ' ' + word ) <= contentWidth )
            {
                myText = myText + ' ' + word;
            } else
            {
                int totalSpacesToInsert = (int)((contentWidth - paint
                        .measureText( myText )) / paint.measureText( " " ));
                lineList.add( justifyLine( myText, totalSpacesToInsert ) );
                myText = word;
            }
        }
        // CHECKSTYLE:ON
        lineList.add( myText );
        return lineList;
    }

    // 已填入最多单词数的一行，插入对应的空格数直到该行满
    private static String justifyLine( String text, int totalSpacesToInsert )
    {
        String[] wordArray = text.split( "\\s" );
        String toAppend = " ";

        while ( (totalSpacesToInsert) >= (wordArray.length - 1) )
        {
            toAppend = toAppend + ' ';
            totalSpacesToInsert = totalSpacesToInsert - (wordArray.length - 1);
        }
        int i = 0;
        String justifiedText = "";
        // CHECKSTYLE:OFF
        for ( String word : wordArray )
        // CHECKSTYLE:ON
        {
            if ( i < totalSpacesToInsert )
            {
                justifiedText = justifiedText + word + ' ' + toAppend;
            } else
            {
                justifiedText = justifiedText + word + toAppend;
            }

            i++;
        }

        return justifiedText;
    }

}
