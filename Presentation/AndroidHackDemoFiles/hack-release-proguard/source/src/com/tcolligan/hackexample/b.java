// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample;

import android.content.Context;
import android.content.SharedPreferences;

public class b
{

    private static final String a = "KEY_STUFF_IS_FREE";

    public b()
    {
    }

    public static void a(Context context, boolean flag)
    {
        context = context.getSharedPreferences("com.tcolligan.hackexample", 0);
        if (context != null)
        {
            context = context.edit();
            context.putBoolean("KEY_STUFF_IS_FREE", flag);
            context.commit();
        }
    }

    public static boolean a(Context context)
    {
        boolean flag = false;
        context = context.getSharedPreferences("com.tcolligan.hackexample", 0);
        if (context != null)
        {
            flag = context.getBoolean("KEY_STUFF_IS_FREE", false);
        }
        return flag;
    }
}
