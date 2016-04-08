package com.tcolligan.hackexample.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.tcolligan.hackexample.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created on 4/4/16
 *
 * @author ThomasColligan
 */
public class UserManager
{
    // ================================================================================
    // Class Properties
    // ================================================================================

    private static final String TAG = "UserManager";
    private static final String KEY_USER_INFO = "KEY_USER_INFO";
    private static UserManager instance;
    private User currentUser;

    // ================================================================================
    // Singleton Method
    // ================================================================================

    public static UserManager getInstance()
    {
        if (instance == null)
        {
            instance = new UserManager();
        }

        return instance;
    }

    // ================================================================================
    // Class Instance Methods
    // ================================================================================

    public User getCurrentUser()
    {
        return currentUser;
    }

    public void saveNewUser(Context context, User newUser)
    {
        currentUser = newUser;

        SharedPreferences prefs = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);

        if (prefs != null)
        {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(KEY_USER_INFO, currentUser.toJSONObject().toString());
            editor.commit();
        }
    }

    public void loadCurrentUser(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);

        if (prefs != null)
        {
            String userInfoJsonString = prefs.getString(KEY_USER_INFO, null);

            if (userInfoJsonString != null)
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(userInfoJsonString);
                    currentUser = new User(jsonObject);
                }
                catch (JSONException e)
                {
                    Log.w(TAG, e);
                }
            }
        }
    }

    public void logout(Context context)
    {
        currentUser = null;

        SharedPreferences prefs = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);

        if (prefs != null)
        {
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(KEY_USER_INFO);
            editor.commit();
        }
    }
}
