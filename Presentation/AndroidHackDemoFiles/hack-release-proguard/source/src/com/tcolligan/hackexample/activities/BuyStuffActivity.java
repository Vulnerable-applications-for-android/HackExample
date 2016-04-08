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
import com.tcolligan.hackexample.a.a;
import com.tcolligan.hackexample.a.b;
import com.tcolligan.hackexample.a.c;
import java.util.HashMap;
import org.json.JSONObject;

// Referenced classes of package com.tcolligan.hackexample.activities:
//            DevOptionsActivity, LoginActivity

public class BuyStuffActivity extends AppCompatActivity
{

    private static final double a = 10D;
    private TextView b;
    private Button c;

    public BuyStuffActivity()
    {
    }

    public static Intent a(Context context)
    {
        return new Intent(context, com/tcolligan/hackexample/activities/BuyStuffActivity);
    }

    private void a()
    {
        (new b(com.tcolligan.hackexample.a.a.b(), new com.tcolligan.hackexample.a.b.a() {

            final BuyStuffActivity a;

            public void a()
            {
                Toast.makeText(a.getApplicationContext(), 0x7f06001a, 0).show();
            }

            public void a(c c1)
            {
                if (c1.a())
                {
                    break MISSING_BLOCK_LABEL_27;
                }
                c1 = c1.d();
                double d = c1.getDouble("debt_amount");
                com.tcolligan.hackexample.activities.BuyStuffActivity.a(a, d);
                return;
                c1;
                c1.printStackTrace();
                return;
            }

            
            {
                a = BuyStuffActivity.this;
                super();
            }
        })).execute(new String[] {
            "http://androidhacktest-apppartner.rhcloud.com/scripts/fetch_debt.php"
        });
    }

    private void a(double d)
    {
        b.setText(getString(0x7f060018, new Object[] {
            String.format("%.2f", new Object[] {
                Double.valueOf(d)
            })
        }));
    }

    static void a(BuyStuffActivity buystuffactivity)
    {
        buystuffactivity.a();
    }

    static void a(BuyStuffActivity buystuffactivity, double d)
    {
        buystuffactivity.a(d);
    }

    public void onBuyStuffButtonClicked(View view)
    {
        double d;
        if (com.tcolligan.hackexample.b.a(this))
        {
            d = 0.0D;
        } else
        {
            d = 10D;
        }
        view = com.tcolligan.hackexample.a.a.b();
        view.put("additional_debt", Double.toString(d));
        (new b(view, new com.tcolligan.hackexample.a.b.a() {

            final BuyStuffActivity a;

            public void a()
            {
                Toast.makeText(a.getApplicationContext(), 0x7f06001a, 0).show();
            }

            public void a(c c1)
            {
                if (!c1.a())
                {
                    com.tcolligan.hackexample.activities.BuyStuffActivity.a(a);
                }
                c1.a(a.getApplicationContext());
            }

            
            {
                a = BuyStuffActivity.this;
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
        b = (TextView)findViewById(0x7f0c0069);
        c = (Button)findViewById(0x7f0c006a);
        c.setVisibility(8);
        a(0.0D);
    }

    public void onDevOptionsButtonClicked(View view)
    {
        startActivity(com.tcolligan.hackexample.activities.DevOptionsActivity.a(this));
    }

    public void onLogoutButtonClicked(View view)
    {
        com.tcolligan.hackexample.b.b.a().b(getApplicationContext());
        startActivity(com.tcolligan.hackexample.activities.LoginActivity.a(this));
        finish();
    }

    public void onResume()
    {
        super.onResume();
        a();
    }
}
