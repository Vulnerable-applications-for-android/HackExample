// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.a;

import com.tcolligan.hackexample.b.b;
import java.util.HashMap;

public class a
{

    public static final String a = "http://androidhacktest-apppartner.rhcloud.com/scripts/login.php";
    public static final String b = "http://androidhacktest-apppartner.rhcloud.com/scripts/register.php";
    public static final String c = "http://androidhacktest-apppartner.rhcloud.com/scripts/fetch_debt.php";
    public static final String d = "http://androidhacktest-apppartner.rhcloud.com/scripts/add_debt.php";
    private static final String e = "2f7564d29c250b017ae00bcea021901e";
    private static final String f = "http://androidhacktest-apppartner.rhcloud.com/scripts/";
    private static final String g = "LOGIN_TOKEN_KEY";

    public a()
    {
    }

    public static HashMap a()
    {
        return a(false);
    }

    private static HashMap a(boolean flag)
    {
        HashMap hashmap = new HashMap();
        com.tcolligan.hackexample.b.a a1 = com.tcolligan.hackexample.b.b.a().b();
        hashmap.put("api_token", "2f7564d29c250b017ae00bcea021901e");
        if (flag)
        {
            hashmap.put("login_token", a1.c());
            hashmap.put("user_id", Integer.toString(a1.a()));
        }
        return hashmap;
    }

    public static HashMap b()
    {
        return a(true);
    }
}
