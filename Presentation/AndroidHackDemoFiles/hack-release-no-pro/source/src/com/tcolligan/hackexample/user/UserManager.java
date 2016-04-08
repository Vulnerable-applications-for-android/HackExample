// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.tcolligan.hackexample.user:
//            User

public class UserManager
{

    private static final String KEY_USER_INFO = "KEY_USER_INFO";
    private static final String TAG = "UserManager";
    private static UserManager instance;
    private User currentUser;

    public UserManager()
    {
    }

    public static UserManager getInstance()
    {
        if (instance == null)
        {
            instance = new UserManager();
        }
        return instance;
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public void loadCurrentUser(Context context)
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
        currentUser = new User(new JSONObject(context));
        return;
        context;
        Log.w("UserManager", context);
        return;
    }

    public void logout(Context context)
    {
        currentUser = null;
        context = context.getSharedPreferences("com.tcolligan.hackexample", 0);
        if (context != null)
        {
            context = context.edit();
            context.remove("KEY_USER_INFO");
            context.commit();
        }
    }

    public void saveNewUser(Context context, User user)
    {
        currentUser = user;
        context = context.getSharedPreferences("com.tcolligan.hackexample", 0);
        if (context != null)
        {
            context = context.edit();
            context.putString("KEY_USER_INFO", currentUser.toJSONObject().toString());
            context.commit();
        }
    }
}
