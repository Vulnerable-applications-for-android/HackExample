// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.tcolligan.hackexample.Utils;
import com.tcolligan.hackexample.networking.NetworkingHelper;
import com.tcolligan.hackexample.networking.PostRequestAsyncTask;
import com.tcolligan.hackexample.networking.Response;
import com.tcolligan.hackexample.user.User;
import com.tcolligan.hackexample.user.UserManager;
import java.util.HashMap;

// Referenced classes of package com.tcolligan.hackexample.activities:
//            BuyStuffActivity

public class RegisterActivity extends AppCompatActivity
{

    private EditText confirmPasswordEditText;
    private EditText passwordEditText;
    private EditText usernameEditText;

    public RegisterActivity()
    {
    }

    public static Intent getIntent(Context context)
    {
        return new Intent(context, com/tcolligan/hackexample/activities/RegisterActivity);
    }

    private boolean inputIsValid()
    {
        String s = usernameEditText.getText().toString();
        String s1 = passwordEditText.getText().toString();
        String s2 = confirmPasswordEditText.getText().toString();
        return !TextUtils.isEmpty(s) && !TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2);
    }

    private boolean passwordsMatch()
    {
        return passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString());
    }

    private void register(String s, String s1)
    {
        s1 = Utils.md5(s1);
        HashMap hashmap = NetworkingHelper.createPostDict();
        hashmap.put("username", s);
        hashmap.put("password", s1);
        (new PostRequestAsyncTask(hashmap, new com.tcolligan.hackexample.networking.PostRequestAsyncTask.PostRequestTaskListener() {

            final RegisterActivity this$0;

            public void onInvalidResponse()
            {
                Toast.makeText(getApplicationContext(), 0x7f06001a, 0).show();
            }

            public void onResponseReceived(Response response)
            {
                Context context = getApplicationContext();
                if (!response.isError())
                {
                    User user = new User(response.getData());
                    UserManager.getInstance().saveNewUser(context, user);
                    startActivity(BuyStuffActivity.getIntent(RegisterActivity.this));
                    finish();
                }
                response.displayMessageToastIfNecessary(context);
            }

            
            {
                this$0 = RegisterActivity.this;
                super();
            }
        })).execute(new String[] {
            "http://androidhacktest-apppartner.rhcloud.com/scripts/register.php"
        });
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f04001c);
        usernameEditText = (EditText)findViewById(0x7f0c006c);
        passwordEditText = (EditText)findViewById(0x7f0c006d);
        confirmPasswordEditText = (EditText)findViewById(0x7f0c006e);
    }

    public void onRegisterButtonClicked(View view)
    {
        if (!inputIsValid())
        {
            Toast.makeText(getApplicationContext(), 0x7f06001e, 0).show();
            return;
        }
        if (!passwordsMatch())
        {
            Toast.makeText(getApplicationContext(), 0x7f060022, 0).show();
            return;
        }
        if (!Utils.isConnectedToInternet(this))
        {
            Toast.makeText(getApplicationContext(), 0x7f060021, 0).show();
            return;
        } else
        {
            register(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            return;
        }
    }
}
