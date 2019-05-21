
package com.wzp.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String Utils
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2011-7-22
 */
public class StringUtils
{

    /**
     * is null or its length is 0 or it is made by space
     * 
     * <pre>
     * isBlank( null ) = true;
     * isBlank( &quot;&quot; ) = true;
     * isBlank( &quot;  &quot; ) = true;
     * isBlank( &quot;a&quot; ) = false;
     * isBlank( &quot;a &quot; ) = false;
     * isBlank( &quot; a&quot; ) = false;
     * isBlank( &quot;a b&quot; ) = false;
     * </pre>
     * 
     * @param str
     * @return if string is null or its size is 0 or it is made by space, return
     *         true, else return false.
     */
    public static boolean isBlank( String str )
    {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * is null or its length is 0
     * 
     * <pre>
     * isEmpty( null ) = true;
     * isEmpty( &quot;&quot; ) = true;
     * isEmpty( &quot;  &quot; ) = false;
     * </pre>
     * 
     * @param str
     * @return if string is null or its size is 0, return true, else return
     *         false.
     */
    public static boolean isEmpty( String str )
    {
        return (str == null || str.length() == 0);
    }

    /**
     * compare two string
     * 
     * @param actual
     * @param expected
     * @return
     * @see ObjectUtils#isEquals(Object, Object)
     */
    public static boolean isEquals( String actual, String expected )
    {
        return ObjectUtils.isEquals( actual, expected );
    }

    /**
     * null string to empty string
     * 
     * <pre>
     * nullStrToEmpty( null ) = &quot;&quot;;
     * nullStrToEmpty( &quot;&quot; ) = &quot;&quot;;
     * nullStrToEmpty( &quot;aa&quot; ) = &quot;aa&quot;;
     * </pre>
     * 
     * @param str
     * @return
     */
    public static String nullStrToEmpty( String str )
    {
        return (str == null ? "" : str);
    }

    /**
     * capitalize first letter
     * 
     * <pre>
     * capitalizeFirstLetter(null)     =   null;
     * capitalizeFirstLetter("")       =   "";
     * capitalizeFirstLetter("2ab")    =   "2ab"
     * capitalizeFirstLetter("a")      =   "A"
     * capitalizeFirstLetter("ab")     =   "Ab"
     * capitalizeFirstLetter("Abc")    =   "Abc"
     * </pre>
     * 
     * @param str
     * @return
     */
    public static String capitalizeFirstLetter( String str )
    {
        if ( isEmpty( str ) )
        {
            return str;
        }

        char c = str.charAt( 0 );
        return (!Character.isLetter( c ) || Character.isUpperCase( c )) ? str
                : new StringBuilder( str.length() )
                        .append( Character.toUpperCase( c ) )
                        .append( str.substring( 1 ) ).toString();
    }

    /**
     * encoded in utf-8
     * 
     * <pre>
     * utf8Encode(null)        =   null
     * utf8Encode("")          =   "";
     * utf8Encode("aa")        =   "aa";
     * utf8Encode("啊啊啊啊")   = "%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A";
     * </pre>
     * 
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     *             if an error occurs
     */
    public static String utf8Encode( String str )
    {
        if ( !isEmpty( str ) && str.getBytes().length != str.length() )
        {
            try
            {
                return URLEncoder.encode( str, "UTF-8" );
            } catch ( UnsupportedEncodingException e )
            {
                throw new RuntimeException(
                        "UnsupportedEncodingException occurred. ", e );
            }
        }
        return str;
    }

    /**
     * encoded in utf-8, if exception, return defultReturn
     * 
     * @param str
     * @param defultReturn
     * @return
     */
    public static String utf8Encode( String str, String defultReturn )
    {
        if ( !isEmpty( str ) && str.getBytes().length != str.length() )
        {
            try
            {
                return URLEncoder.encode( str, "UTF-8" );
            } catch ( UnsupportedEncodingException e )
            {
                return defultReturn;
            }
        }
        return str;
    }

    /**
     * get innerHtml from href
     * 
     * <pre>
     * getHrefInnerHtml(null)                                  = ""
     * getHrefInnerHtml("")                                    = ""
     * getHrefInnerHtml("mp3")                                 = "mp3";
     * getHrefInnerHtml("&lt;a innerHtml&lt;/a&gt;")                    = "&lt;a innerHtml&lt;/a&gt;";
     * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;")                    = "innerHtml";
     * getHrefInnerHtml("&lt;a&lt;a&gt;innerHtml&lt;/a&gt;")                    = "innerHtml";
     * getHrefInnerHtml("&lt;a href="baidu.com"&gt;innerHtml&lt;/a&gt;")               = "innerHtml";
     * getHrefInnerHtml("&lt;a href="baidu.com" title="baidu"&gt;innerHtml&lt;/a&gt;") = "innerHtml";
     * getHrefInnerHtml("   &lt;a&gt;innerHtml&lt;/a&gt;  ")                           = "innerHtml";
     * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;")                      = "innerHtml";
     * getHrefInnerHtml("jack&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;")                  = "innerHtml";
     * getHrefInnerHtml("&lt;a&gt;innerHtml1&lt;/a&gt;&lt;a&gt;innerHtml2&lt;/a&gt;")        = "innerHtml2";
     * </pre>
     * 
     * @param href
     * @return <ul>
     *         <li>if href is null, return ""</li>
     *         <li>if not match regx, return source</li>
     *         <li>return the last string that match regx</li>
     *         </ul>
     */
    public static String getHrefInnerHtml( String href )
    {
        if ( isEmpty( href ) )
        {
            return "";
        }

        String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
        Pattern hrefPattern = Pattern.compile( hrefReg,
                Pattern.CASE_INSENSITIVE );
        Matcher hrefMatcher = hrefPattern.matcher( href );
        if ( hrefMatcher.matches() )
        {
            return hrefMatcher.group( 1 );
        }
        return href;
    }

/**
     * process special char in html
     * 
     * <pre>
     * htmlEscapeCharsToString(null) = null;
     * htmlEscapeCharsToString("") = "";
     * htmlEscapeCharsToString("mp3") = "mp3";
     * htmlEscapeCharsToString("mp3&lt;") = "mp3<";
     * htmlEscapeCharsToString("mp3&gt;") = "mp3\>";
     * htmlEscapeCharsToString("mp3&amp;mp4") = "mp3&mp4";
     * htmlEscapeCharsToString("mp3&quot;mp4") = "mp3\"mp4";
     * htmlEscapeCharsToString("mp3&lt;&gt;&amp;&quot;mp4") = "mp3\<\>&\"mp4";
     * </pre>
     * 
     * @param source
     * @return
     */
    public static String htmlEscapeCharsToString( String source )
    {
        return StringUtils.isEmpty( source ) ? source : source
                .replaceAll( "&lt;", "<" ).replaceAll( "&gt;", ">" )
                .replaceAll( "&amp;", "&" ).replaceAll( "&quot;", "\"" );
    }

    /**
     * transform half width char to full width char
     * 
     * <pre>
     * fullWidthToHalfWidth(null) = null;
     * fullWidthToHalfWidth("") = "";
     * fullWidthToHalfWidth(new String(new char[] {12288})) = " ";
     * fullWidthToHalfWidth("！＂＃＄％＆) = "!\"#$%&";
     * </pre>
     * 
     * @param s
     * @return
     */
    public static String fullWidthToHalfWidth( String s )
    {
        if ( isEmpty( s ) )
        {
            return s;
        }

        char[] source = s.toCharArray();
        // CHECKSTYLE:OFF
        for ( int i = 0; i < source.length; i++ )
        {
            if ( source[i] == 12288 )
            {
                source[i] = ' ';
                // } else if (source[i] == 12290) {
                // source[i] = '.';
            } else if ( source[i] >= 65281 && source[i] <= 65374 )
            {
                source[i] = (char)(source[i] - 65248);
            } else
            {
                source[i] = source[i];
            }
        }
        // CHECKSTYLE:ON
        return new String( source );
    }

    /**
     * transform full width char to half width char
     * 
     * <pre>
     * halfWidthToFullWidth(null) = null;
     * halfWidthToFullWidth("") = "";
     * halfWidthToFullWidth(" ") = new String(new char[] {12288});
     * halfWidthToFullWidth("!\"#$%&) = "！＂＃＄％＆";
     * </pre>
     * 
     * @param s
     * @return
     */
    public static String halfWidthToFullWidth( String s )
    {
        if ( isEmpty( s ) )
        {
            return s;
        }

        char[] source = s.toCharArray();
        // CHECKSTYLE:OFF
        for ( int i = 0; i < source.length; i++ )
        {
            if ( source[i] == ' ' )
            {
                source[i] = (char)12288;
                // } else if (source[i] == '.') {
                // source[i] = (char)12290;
            } else if ( source[i] >= 33 && source[i] <= 126 )
            {
                source[i] = (char)(source[i] + 65248);
            } else
            {
                source[i] = source[i];
            }
        }
        // CHECKSTYLE:ON
        return new String( source );
    }

    /**
     * 获得utf-8编码集的字符串，并且处理“ \ ” 和 “ " ”
     * 
     * @param content
     *            要处理的字符串
     * @return
     */
    public static String getUtf8EncodeStr( String content )
    {
        // content = StringUtils.utf8Encode( content );
        content = content.replace( "\\", "\\\\" ).replace( "\"", "\\\"" );
        return content;
    }

    public static boolean isEmptyOrNull( String content )
    {
        if ( (content == null) || ("".equals( content )) )
        {
            return true;
        }
        return false;
    }

    public static boolean isIPAddress( String ipString )
    {
        if ( ipString != null )
        {

            String[] singleArray = ipString.split( "\\." );
            if ( singleArray == null )
            {
                return false;
            }

            // CHECKSTYLE:OFF
            for ( String numString : singleArray )
            {
                if ( isEmptyOrNull( numString.trim() ) )
                {
                    return false;
                }
                try
                {
                    int num = Integer.parseInt( numString.trim() );
                    if ( (num < 0) || (num > 255) )
                    {
                        return false;
                    }
                } catch ( NumberFormatException e )
                {
                    Log.e( e.toString() );
                    return false;
                }
            }
            // CHECKSTYLE:ON

            return true;
        }
        return false;
    }

    public static boolean isEmailAddress( String emailString )
    {
        String format = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
        return isMatch( format, emailString );
    }

    public static boolean isDigit( String digitString )
    {
        if ( !isEmptyOrNull( digitString ) )
        {
            String regex = "[0-9]*";
            return isMatch( regex, digitString );
        }
        return false;
    }

    public static boolean isPhoneNumber( String phoneString )
    {
        String format = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        return isMatch( format, phoneString );
    }

    public static boolean isMatch( String regex, String string )
    {
        Pattern pattern = Pattern.compile( regex );
        Matcher matcher = pattern.matcher( string );
        return matcher.matches();
    }

    public static boolean isUrl( String strIp )
    {
        String strPattern = "^((https?)|(ftp))://(?:(\\s+?)(?::(\\s+?))?@)?([a-zA-Z0-9\\-.]+)(?::(\\d+))?((?:/[a-zA-Z0-9\\-._?,'+\\&%$=~*!():@\\\\]*)+)?$";

        return isMatch( strPattern, strIp );
    }

    public static String string2Unicode( String string )
    {
        if ( !isEmptyOrNull( string ) )
        {
            char[] charArray = string.toCharArray();
            StringBuffer buffer = new StringBuffer();
            int code = -1;
            // CHECKSTYLE:OFF
            for ( char ch : charArray )
            // CHECKSTYLE:ON
            {
                code = ch;
                buffer.append( code );
            }
            return buffer.toString();
        }
        return null;
    }

    public static String unicode2String( String string )
    {
        if ( !isEmptyOrNull( string ) )
        {

            int end = 0;
            String noSpace = string.trim();
            int count = noSpace.length() / 5;
            StringBuffer buffer = new StringBuffer();
            int uCode = -1;
            for ( int j = 0; j < count; j++ )
            {

                end += 5;
                uCode = Integer.valueOf( noSpace.substring( j * 5, end ) )
                        .intValue();
                // CHECKSTYLE:OFF
                buffer.append( (char)uCode );
                // CHECKSTYLE:ON
            }

            return buffer.toString();
        }
        return null;
    }

//    public static String getFirstPinYin( String string )
//    {
//        if ( !isEmptyOrNull( string ) )
//        {
//            char[] cs = string.toCharArray();
//            String[] pinyins = null;
//            for ( int i = 0; i < cs.length; i++ )
//            {
//                pinyins = null;
//                if ( cs[i] > '?' )
//                {
//                    pinyins = PinyinHelper.toHanyuPinyinStringArray( cs[i] );
//                }
//
//                if ( (pinyins != null) && (pinyins.length > 0) )
//                {
//                    return pinyins[0].substring( 0, 1 );
//                }
//            }
//        }
//        return null;
//    }

    public static String getParamValueOfUrl( String url, String paramName )
    {
        try
        {
            String[] urls = url.split( "[?]" );
            if ( urls.length > 1 )
            {
                String param = urls[1];
                String[] params = param.split( "[&]" );
                String[] keyAndValue = null;
                // CHECKSTYLE:OFF
                for ( String string : params )
                // CHECKSTYLE:ON
                {
                    keyAndValue = string.split( "[=]" );
                    if ( null != keyAndValue && keyAndValue.length > 1 )
                    {
                        String key = keyAndValue[0];
                        String value = keyAndValue[1];
                        if ( key.equalsIgnoreCase( paramName ) )
                        {
                            return value;
                        }
                    }
                }
            }
        } catch ( Exception e )
        {
            return "";
        }
        return "";
    }

    // 判断email格式是否正确
    public static boolean isEmail( String email )
    {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile( str );
        Matcher m = p.matcher( email );

        return m.matches();
    }
}
