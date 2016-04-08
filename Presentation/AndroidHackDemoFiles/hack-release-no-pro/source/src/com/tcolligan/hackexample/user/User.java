// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.user;

import android.util.Log;
import org.json.JSONObject;

public class User
{

    private static final String KEY_LOGIN_TOKEN = "login_token";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_ID = "user_id";
    private static final String TAG = "User";
    private String loginToken;
    private int userId;
    private String username;

    public User(JSONObject jsonobject)
    {
        try
        {
            userId = jsonobject.getInt("user_id");
            username = jsonobject.getString("username");
            loginToken = jsonobject.getString("login_token");
            return;
        }
        // Misplaced declaration of an exception variable
        catch (JSONObject jsonobject)
        {
            Log.w("User", jsonobject);
        }
    }

    public String getLoginToken()
    {
        return loginToken;
    }

    public int getUserId()
    {
        return userId;
    }

    public String getUsername()
    {
        return username;
    }

    public JSONObject toJSONObject()
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("user_id", userId);
            jsonobject.put("username", username);
            jsonobject.put("login_token", loginToken);
        }
        catch (Exception exception)
        {
            Log.w("User", exception);
            return jsonobject;
        }
        return jsonobject;
    }
}
