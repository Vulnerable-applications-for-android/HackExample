// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.networking;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONObject;

public class Response
{

    private static final String TAG = "Response";
    private JSONObject data;
    private boolean isError;
    private String message;
    private boolean shouldDisplayMessage;

    public Response(byte abyte0[])
    {
        try
        {
            abyte0 = new JSONObject(new String(abyte0));
            isError = abyte0.getBoolean("isError");
            shouldDisplayMessage = abyte0.getBoolean("shouldDisplayMessage");
            message = abyte0.optString("message", null);
            data = abyte0.optJSONObject("data");
            return;
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            Log.w("Response", abyte0);
        }
    }

    public void displayMessageToastIfNecessary(Context context)
    {
        if (shouldDisplayMessage)
        {
            Toast.makeText(context, message, 0).show();
        } else
        if (isError)
        {
            Toast.makeText(context, 0x7f06001a, 0).show();
            return;
        }
    }

    public JSONObject getData()
    {
        return data;
    }

    public String getMessage()
    {
        return message;
    }

    public boolean isError()
    {
        return isError;
    }

    public boolean shouldDisplayMessage()
    {
        return shouldDisplayMessage;
    }
}
