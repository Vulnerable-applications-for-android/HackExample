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
//            BuyStuffActivity, RegisterActivity

public class LoginActivity extends AppCompatActivity
{

    private EditText passwordEditText;
    private EditText usernameEditText;

    public LoginActivity()
    {
    }

    public static Intent getIntent(Context context)
    {
        return new Intent(context, com/tcolligan/hackexample/activities/LoginActivity);
    }

    private boolean inputIsValid()
    {
        String s = usernameEditText.getText().toString();
        String s1 = passwordEditText.getText().toString();
        return !TextUtils.isEmpty(s) && !TextUtils.isEmpty(s1);
    }

    private void login(String s, String s1)
    {
        s1 = Utils.md5(s1);
        HashMap hashmap = NetworkingHelper.createPostDict();
        hashmap.put("username", s);
        hashmap.put("password", s1);
        (new PostRequestAsyncTask(hashmap, new com.tcolligan.hackexample.networking.PostRequestAsyncTask.PostRequestTaskListener() {

            final LoginActivity this$0;

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
                    startActivity(BuyStuffActivity.getIntent(LoginActivity.this));
                    finish();
                }
                response.displayMessageToastIfNecessary(context);
            }

            
            {
                this$0 = LoginActivity.this;
                super();
            }
        })).execute(new String[] {
            "http://androidhacktest-apppartner.rhcloud.com/scripts/login.php"
        });
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f04001b);
        usernameEditText = (EditText)findViewById(0x7f0c006c);
        passwordEditText = (EditText)findViewById(0x7f0c006d);
        UserManager.getInstance().loadCurrentUser(this);
        if (UserManager.getInstance().getCurrentUser() != null)
        {
            startActivity(BuyStuffActivity.getIntent(this));
            finish();
        }
    }

    public void onLoginButtonClicked(View view)
    {
        if (!inputIsValid())
        {
            Toast.makeText(getApplicationContext(), 0x7f06001e, 0).show();
            return;
        }
        if (!Utils.isConnectedToInternet(this))
        {
            Toast.makeText(getApplicationContext(), 0x7f060021, 0).show();
            return;
        } else
        {
            login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            return;
        }
    }

    public void onRegisterButtonClicked(View view)
    {
        startActivity(RegisterActivity.getIntent(this));
    }
}
