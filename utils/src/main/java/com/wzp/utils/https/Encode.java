package com.wzp.utils.https;

import com.wzp.utils.Log;

import it.sauronsoftware.base64.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encode
{
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final String STR_KEY = "q5KKHUPX6YAYCw8lYNd4aA==";

    public Encode()
    {
    }

    private static String toHexString( byte[] b )
    {
        StringBuilder sb = new StringBuilder( b.length * 2 );
        for ( int i = 0; i < b.length; i++ )
        {
            sb.append( HEX_DIGITS[((b[i] & 0xF0) >>> 4)] );
            sb.append( HEX_DIGITS[(b[i] & 0xF)] );
        }
        return sb.toString();
    }

    public static byte[] getAESDecrypt( String hieroglyph ) throws Exception
    {
        return getAESDecrypt( STR_KEY, hieroglyph );
    }

    public static byte[] getAESDecrypt( String key, String hieroglyph )
            throws Exception
    {
        try
        {
            SecretKey k = new SecretKeySpec( string2Bytes( key ), "AES" );
            Cipher cipher = Cipher.getInstance( "AES" );
            cipher.init( 2, k );

            byte[] bt = cipher.doFinal( string2Bytes( hieroglyph ) );
            return bt;

        } catch ( Exception e )
        {

            throw e;
        }
    }

    public static byte[] string2Bytes( String str )
            throws UnsupportedEncodingException
    {
        return Base64.decode( str.getBytes( "UTF-8" ) );
    }

    public static String bytes2String( byte[] b )
    {
        try
        {
            return new String( Base64.encode( b ), "UTF-8" );
        } catch ( Exception e )
        {
            Log.d( e.getMessage() );
        }
        return "";
    }

    public static String getAESEncrypt( String vivid )
    {
        return getAESEncrypt( STR_KEY, vivid );
    }

    public static String getAESEncrypt( String key, String vivid )
    {
        try
        {
            SecretKey k = new SecretKeySpec( string2Bytes( key ), "AES" );
            Cipher cipher = Cipher.getInstance( "AES" );
            cipher.init( 1, k );
            return bytes2String( cipher.doFinal( vivid.getBytes( "UTF-8" ) ) );
        } catch ( Exception e )
        {
            Log.d( e.getMessage() );
        }
        return "";
    }

    public static String getRSAEncrypt( String encryptkey, String data )
            throws Exception
    {
        PublicKey key = getPublicKeyByString( encryptkey );
        byte[] bytes = data.getBytes( "UTF-8" );
        byte[] en = getRSAEncrypt( key, bytes );
        byte[] b = Base64.encode( en );
        return new String( b, "UTF-8" );
    }

    public static byte[] getRSAEncrypt( PublicKey publicKey, byte[] data )
            throws Exception
    {
        if ( publicKey != null )
        {
            try
            {
                Cipher cipher = Cipher.getInstance( "RSA/None/PKCS1Padding" );
                SecureRandom random = new SecureRandom();
                cipher.init( 1, publicKey, random );
                return cipher.doFinal( data );
            } catch ( Exception e )
            {
                throw e;
            }
        }
        return null;
    }

    private static PublicKey getPublicKeyByString( String key )
            throws Exception
    {
        KeyFactory keyFactory = KeyFactory.getInstance( "RSA" );
        byte[] b1 = Base64.decode( key.getBytes( "UTF-8" ) );
        X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec( b1 );
        PublicKey publicKey = keyFactory.generatePublic( bobPubKeySpec );
        return publicKey;
    }

    public static String getMD5Encrypt( byte[] data ) throws Exception
    {
        MessageDigest md5 = MessageDigest.getInstance( "MD5" );
        md5.update( data );
        return toHexString( md5.digest() );
    }

    public static String getMD5Encrypt( String inStr ) throws Exception
    {
        MessageDigest md = MessageDigest.getInstance( "MD5" );
        byte[] md5Bytes = md.digest( inStr.getBytes( "UTF-8" ) );
        return toHexString( md5Bytes );
    }

    public static String getMD5Encrypt( InputStream input ) throws Exception
    {
        byte[] buffer = new byte['?'];
        int numRead = 0;

        try
        {
            MessageDigest md5 = MessageDigest.getInstance( "MD5" );
            // CHECKSTYLE:OFF
            while ( (numRead = input.read( buffer )) > 0 )
            // CHECKSTYLE:ON
            {
                md5.update( buffer, 0, numRead );
            }
            return toHexString( md5.digest() );
        } catch ( Exception e )
        {
            throw e;
        } finally
        {
            if ( input != null )
            {
                try
                {
                    input.close();
                } catch ( IOException e )
                {
                    throw e;
                }
            }
        }
    }
}
