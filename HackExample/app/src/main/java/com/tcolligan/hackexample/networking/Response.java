package com.tcolligan.hackexample.networking;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.tcolligan.hackexample.R;
import com.tcolligan.hackexample.user.User;
import com.tcolligan.hackexample.user.UserManager;

import org.json.JSONObject;

/**
 * Created on 4/4/16
 *
 * @author ThomasColligan
 */
public class Response
{
    // ================================================================================
    // Class Properties
    // ================================================================================

    private static final String TAG = "Response";

    private boolean isError;
    private boolean shouldDisplayMessage;
    private String message;
    private JSONObject data;

    // ================================================================================
    // Constructor
    // ================================================================================

    public Response(byte[] responseData)
    {
        try
        {
            String responseString = new String(responseData);
            JSONObject jsonResponse = new JSONObject(responseString);

            isError = jsonResponse.getBoolean("isError");
            shouldDisplayMessage = jsonResponse.getBoolean("shouldDisplayMessage");
            message = jsonResponse.optString("message", null);
            data = jsonResponse.optJSONObject("data");
        }
        catch (Exception e)
        {
            Log.w(TAG, e);
        }
    }

    // ================================================================================
    // Class Instance Methods
    // ================================================================================

    public boolean isError()
    {
        return isError;
    }

    public boolean shouldDisplayMessage()
    {
        return shouldDisplayMessage;
    }

    public String getMessage()
    {
        return message;
    }

    public JSONObject getData()
    {
        return data;
    }

    public void displayMessageToastIfNecessary(Context context)
    {
        if (shouldDisplayMessage)
        {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
        else if (isError)
        {
            Toast.makeText(context, R.string.error_toast_text, Toast.LENGTH_SHORT).show();
        }
    }
}
