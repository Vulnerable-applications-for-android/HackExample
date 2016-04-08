// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.tcolligan.hackexample.BuildConfig;
import com.tcolligan.hackexample.DevOptions;
import com.tcolligan.hackexample.networking.NetworkingHelper;
import com.tcolligan.hackexample.networking.PostRequestAsyncTask;
import com.tcolligan.hackexample.networking.Response;
import com.tcolligan.hackexample.user.UserManager;
import java.util.HashMap;
import org.json.JSONObject;

// Referenced classes of package com.tcolligan.hackexample.activities:
//            DevOptionsActivity, LoginActivity

public class BuyStuffActivity extends AppCompatActivity
{

    private static final double PRICE = 10D;
    private TextView currentDebtTextView;
    private Button devOptionsButton;

    public BuyStuffActivity()
    {
    }

    private void fetchCurrentDebt()
    {
        (new PostRequestAsyncTask(NetworkingHelper.createPostDictWithUserInfo(), new com.tcolligan.hackexample.networking.PostRequestAsyncTask.PostRequestTaskListener() {

            final BuyStuffActivity this$0;

            public void onInvalidResponse()
            {
                Toast.makeText(getApplicationContext(), 0x7f06001a, 0).show();
            }

            public void onResponseReceived(Response response)
            {
                if (response.isError())
                {
                    break MISSING_BLOCK_LABEL_27;
                }
                response = response.getData();
                double d = response.getDouble("debt_amount");
                setCurrentDebt(d);
                return;
                response;
                response.printStackTrace();
                return;
            }

            
            {
                this$0 = BuyStuffActivity.this;
                super();
            }
        })).execute(new String[] {
            "http://androidhacktest-apppartner.rhcloud.com/scripts/fetch_debt.php"
        });
    }

    public static Intent getIntent(Context context)
    {
        return new Intent(context, com/tcolligan/hackexample/activities/BuyStuffActivity);
    }

    private void setCurrentDebt(double d)
    {
        currentDebtTextView.setText(getString(0x7f060018, new Object[] {
            String.format("%.2f", new Object[] {
                Double.valueOf(d)
            })
        }));
    }

    public void onBuyStuffButtonClicked(View view)
    {
        double d;
        if (DevOptions.stuffIsFree(this))
        {
            d = 0.0D;
        } else
        {
            d = 10D;
        }
        view = NetworkingHelper.createPostDictWithUserInfo();
        view.put("additional_debt", Double.toString(d));
        (new PostRequestAsyncTask(view, new com.tcolligan.hackexample.networking.PostRequestAsyncTask.PostRequestTaskListener() {

            final BuyStuffActivity this$0;

            public void onInvalidResponse()
            {
                Toast.makeText(getApplicationContext(), 0x7f06001a, 0).show();
            }

            public void onResponseReceived(Response response)
            {
                if (!response.isError())
                {
                    fetchCurrentDebt();
                }
                response.displayMessageToastIfNecessary(getApplicationContext());
            }

            
            {
                this$0 = BuyStuffActivity.this;
                super();
            }
        })).execute(new String[] {
            "http://androidhacktest-apppartner.rhcloud.com/scripts/add_debt.php"
        });
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f040019);
        currentDebtTextView = (TextView)findViewById(0x7f0c0069);
        devOptionsButton = (Button)findViewById(0x7f0c006a);
        bundle = devOptionsButton;
        int i;
        if (BuildConfig.DEBUG)
        {
            i = 0;
        } else
        {
            i = 8;
        }
        bundle.setVisibility(i);
        setCurrentDebt(0.0D);
    }

    public void onDevOptionsButtonClicked(View view)
    {
        startActivity(DevOptionsActivity.getIntent(this));
    }

    public void onLogoutButtonClicked(View view)
    {
        UserManager.getInstance().logout(getApplicationContext());
        startActivity(LoginActivity.getIntent(this));
        finish();
    }

    public void onResume()
    {
        super.onResume();
        fetchCurrentDebt();
    }


}
