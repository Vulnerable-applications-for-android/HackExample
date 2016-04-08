// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils
{

    public Utils()
    {
    }

    public static boolean isConnectedToInternet(Context context)
    {
        if (context != null)
        {
            if ((context = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo()) != null && context.isConnected())
            {
                return true;
            }
        }
        return false;
    }

    public static String md5(String s)
    {
        try
        {
            s = MessageDigest.getInstance("MD5").digest(s.getBytes());
            BigInteger biginteger = new BigInteger(1, s);
            s = String.format((new StringBuilder()).append("%0").append(s.length << 1).append("x").toString(), new Object[] {
                biginteger
            });
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            s.printStackTrace();
            return null;
        }
        return s;
    }
}
