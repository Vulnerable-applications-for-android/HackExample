// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.b;

import android.util.Log;
import org.json.JSONObject;

public class a
{

    private static final String a = "User";
    private static final String b = "user_id";
    private static final String c = "username";
    private static final String d = "login_token";
    private int e;
    private String f;
    private String g;

    public a(JSONObject jsonobject)
    {
        try
        {
            e = jsonobject.getInt("user_id");
            f = jsonobject.getString("username");
            g = jsonobject.getString("login_token");
            return;
        }
        // Misplaced declaration of an exception variable
        catch (JSONObject jsonobject)
        {
            Log.w("User", jsonobject);
        }
    }

    public int a()
    {
        return e;
    }

    public String b()
    {
        return f;
    }

    public String c()
    {
        return g;
    }

    public JSONObject d()
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("user_id", e);
            jsonobject.put("username", f);
            jsonobject.put("login_token", g);
        }
        catch (Exception exception)
        {
            Log.w("User", exception);
            return jsonobject;
        }
        return jsonobject;
    }
}
