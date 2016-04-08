// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.activities;

import android.widget.Toast;
import com.tcolligan.hackexample.networking.Response;
import com.tcolligan.hackexample.user.User;
import com.tcolligan.hackexample.user.UserManager;

// Referenced classes of package com.tcolligan.hackexample.activities:
//            LoginActivity, BuyStuffActivity

class this._cls0
    implements com.tcolligan.hackexample.networking.Task.PostRequestTaskListener
{

    final LoginActivity this$0;

    public void onInvalidResponse()
    {
        Toast.makeText(getApplicationContext(), 0x7f06001a, 0).show();
    }

    public void onResponseReceived(Response response)
    {
        android.content.Context context = getApplicationContext();
        if (!response.isError())
        {
            User user = new User(response.getData());
            UserManager.getInstance().saveNewUser(context, user);
            startActivity(BuyStuffActivity.getIntent(LoginActivity.this));
            finish();
        }
        response.displayMessageToastIfNecessary(context);
    }

    ()
    {
        this$0 = LoginActivity.this;
        super();
    }
}
