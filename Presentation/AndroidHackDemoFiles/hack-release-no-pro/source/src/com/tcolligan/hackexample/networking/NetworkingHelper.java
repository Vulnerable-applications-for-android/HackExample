// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.networking;

import com.tcolligan.hackexample.user.User;
import com.tcolligan.hackexample.user.UserManager;
import java.util.HashMap;

public class NetworkingHelper
{

    public static final String ADD_DEBT = "http://androidhacktest-apppartner.rhcloud.com/scripts/add_debt.php";
    private static final String API_TOKEN = "2f7564d29c250b017ae00bcea021901e";
    private static final String BASE_URL = "http://androidhacktest-apppartner.rhcloud.com/scripts/";
    public static final String FETCH_DEBT = "http://androidhacktest-apppartner.rhcloud.com/scripts/fetch_debt.php";
    public static final String LOGIN = "http://androidhacktest-apppartner.rhcloud.com/scripts/login.php";
    private static final String LOGIN_TOKEN_KEY = "LOGIN_TOKEN_KEY";
    public static final String REGISTER = "http://androidhacktest-apppartner.rhcloud.com/scripts/register.php";

    public NetworkingHelper()
    {
    }

    public static HashMap createPostDict()
    {
        return createPostDict(false);
    }

    private static HashMap createPostDict(boolean flag)
    {
        HashMap hashmap = new HashMap();
        User user = UserManager.getInstance().getCurrentUser();
        hashmap.put("api_token", "2f7564d29c250b017ae00bcea021901e");
        if (flag)
        {
            hashmap.put("login_token", user.getLoginToken());
            hashmap.put("user_id", Integer.toString(user.getUserId()));
        }
        return hashmap;
    }

    public static HashMap createPostDictWithUserInfo()
    {
        return createPostDict(true);
    }
}
