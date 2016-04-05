package com.tcolligan.hackexample.user;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created on 4/4/16
 *
 * @author ThomasColligan
 */
public class User
{
    // ================================================================================
    // Class Properties
    // ================================================================================

    private static final String TAG = "User";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_LOGIN_TOKEN = "login_token";

    private int userId;
    private String username;
    private String loginToken;

    // ================================================================================
    // Constructor
    // ================================================================================

    public User(JSONObject jsonObject)
    {
        try
        {
            userId = jsonObject.getInt(KEY_USER_ID);
            username = jsonObject.getString(KEY_USERNAME);
            loginToken = jsonObject.getString(KEY_LOGIN_TOKEN);
        }
        catch (Exception e)
        {
            Log.w(TAG, e);
        }
    }

    // ================================================================================
    // Class Instance Methods
    // ================================================================================

    public int getUserId()
    {
        return userId;
    }

    public String getUsername()
    {
        return username;
    }

    public String getLoginToken()
    {
        return loginToken;
    }

    public JSONObject toJSONObject()
    {
        JSONObject jsonObject = new JSONObject();

        try
        {
            jsonObject.put(KEY_USER_ID, userId);
            jsonObject.put(KEY_USERNAME, username);
            jsonObject.put(KEY_LOGIN_TOKEN, loginToken);
        }
        catch (Exception e)
        {
            Log.w(TAG, e);
        }

        return jsonObject;
    }
}
