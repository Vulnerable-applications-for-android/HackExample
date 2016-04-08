package com.tcolligan.hackexample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created on 4/4/16
 *
 * @author ThomasColligan
 */
public class Utils
{
    public static boolean isConnectedToInternet(final Context context)
    {
        if (context == null)
        {
            return false;
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }

    public static String md5(String string)
    {
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] hashedBytes = messageDigest.digest(string.getBytes());

            // Convert bytes to hex representation
            BigInteger bi = new BigInteger(1, hashedBytes);
            return String.format("%0" + (hashedBytes.length << 1) + "x", bi);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
