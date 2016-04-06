package com.tcolligan.hackexample;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created on 4/5/16
 *
 * @author ThomasColligan
 */
public class DevOptions
{
    private static final String KEY_STUFF_IS_FREE = "KEY_STUFF_IS_FREE";

    public static void setStuffIsFree(Context context, boolean stuffIsFree)
    {
        SharedPreferences prefs = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);

        if (prefs != null)
        {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(KEY_STUFF_IS_FREE, stuffIsFree);
            editor.commit();
        }
    }

    public static boolean stuffIsFree(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);

        if (prefs != null)
        {
            return prefs.getBoolean(KEY_STUFF_IS_FREE, false);
        }

        return false;
    }
}
