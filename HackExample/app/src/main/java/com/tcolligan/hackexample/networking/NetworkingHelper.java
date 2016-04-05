package com.tcolligan.hackexample.networking;

import android.content.Context;

import com.tcolligan.hackexample.user.User;
import com.tcolligan.hackexample.user.UserManager;

import java.util.HashMap;

/**
 * Created on 4/4/16
 *
 * @author ThomasColligan
 */
public class NetworkingHelper
{
    // ================================================================================
    // Class Properties
    // ================================================================================

    private static final String API_TOKEN = "2f7564d29c250b017ae00bcea021901e";
    private static final String BASE_URL = "http://androidhacktest-apppartner.rhcloud.com/scripts/";
    private static final String LOGIN_TOKEN_KEY = "LOGIN_TOKEN_KEY";

    public static final String LOGIN = BASE_URL + "login.php";
    public static final String REGISTER = BASE_URL + "register.php";
    public static final String FETCH_DEBT = BASE_URL + "fetch_debt.php";
    public static final String ADD_DEBT = BASE_URL + "add_debt.php";

    // ================================================================================
    // POST Dictionary Methods
    // ================================================================================

    public static HashMap<String, String> createPostDict()
    {
        return createPostDict(false);
    }

    public static HashMap<String, String> createPostDictWithLoginToken()
    {
        return createPostDict(true);
    }

    private static HashMap<String, String> createPostDict(boolean hasLoginToken)
    {
        HashMap<String, String> postDataParams = new HashMap<>();
        User currentUser = UserManager.getInstance().getCurrentUser();

        postDataParams.put("api_token", API_TOKEN);

        if (hasLoginToken)
        {
            postDataParams.put("login_token", currentUser.getLoginToken());
        }

        return postDataParams;
    }
}
