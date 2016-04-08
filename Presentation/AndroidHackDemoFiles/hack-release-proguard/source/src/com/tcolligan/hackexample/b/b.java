// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.tcolligan.hackexample.b:
//            a

public class b
{

    private static final String a = "UserManager";
    private static final String b = "KEY_USER_INFO";
    private static b c;
    private a d;

    public b()
    {
    }

    public static b a()
    {
        if (c == null)
        {
            c = new b();
        }
        return c;
    }

    public void a(Context context)
    {
        context = context.getSharedPreferences("com.tcolligan.hackexample", 0);
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        context = context.getString("KEY_USER_INFO", null);
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        d = new a(new JSONObject(context));
        return;
        context;
        Log.w("UserManager", context);
        return;
    }

    public void a(Context context, a a1)
    {
        d = a1;
        context = context.getSharedPreferences("com.tcolligan.hackexample", 0);
        if (context != null)
        {
            context = context.edit();
            context.putString("KEY_USER_INFO", d.d().toString());
            context.commit();
        }
    }

    public a b()
    {
        return d;
    }

    public void b(Context context)
    {
        d = null;
        context = context.getSharedPreferences("com.tcolligan.hackexample", 0);
        if (context != null)
        {
            context = context.edit();
            context.remove("KEY_USER_INFO");
            context.commit();
        }
    }
}
