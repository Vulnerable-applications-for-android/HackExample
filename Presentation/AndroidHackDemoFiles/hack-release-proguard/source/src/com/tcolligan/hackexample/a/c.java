// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.a;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONObject;

public class c
{

    private static final String a = "Response";
    private boolean b;
    private boolean c;
    private String d;
    private JSONObject e;

    public c(byte abyte0[])
    {
        try
        {
            abyte0 = new JSONObject(new String(abyte0));
            b = abyte0.getBoolean("isError");
            c = abyte0.getBoolean("shouldDisplayMessage");
            d = abyte0.optString("message", null);
            e = abyte0.optJSONObject("data");
            return;
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            Log.w("Response", abyte0);
        }
    }

    public void a(Context context)
    {
        if (c)
        {
            Toast.makeText(context, d, 0).show();
        } else
        if (b)
        {
            Toast.makeText(context, 0x7f06001a, 0).show();
            return;
        }
    }

    public boolean a()
    {
        return b;
    }

    public boolean b()
    {
        return c;
    }

    public String c()
    {
        return d;
    }

    public JSONObject d()
    {
        return e;
    }
}
