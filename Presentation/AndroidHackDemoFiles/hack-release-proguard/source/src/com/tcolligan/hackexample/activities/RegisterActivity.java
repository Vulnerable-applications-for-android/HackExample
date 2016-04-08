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
import com.tcolligan.hackexample.a.a;
import com.tcolligan.hackexample.a.b;
import com.tcolligan.hackexample.a.c;
import com.tcolligan.hackexample.d;
import java.util.HashMap;

// Referenced classes of package com.tcolligan.hackexample.activities:
//            BuyStuffActivity

public class RegisterActivity extends AppCompatActivity
{

    private EditText a;
    private EditText b;
    private EditText c;

    public RegisterActivity()
    {
    }

    public static Intent a(Context context)
    {
        return new Intent(context, com/tcolligan/hackexample/activities/RegisterActivity);
    }

    private void a(String s, String s1)
    {
        s1 = d.a(s1);
        HashMap hashmap = com.tcolligan.hackexample.a.a.a();
        hashmap.put("username", s);
        hashmap.put("password", s1);
        (new b(hashmap, new com.tcolligan.hackexample.a.b.a() {

            final RegisterActivity a;

            public void a()
            {
                Toast.makeText(a.getApplicationContext(), 0x7f06001a, 0).show();
            }

            public void a(c c1)
            {
                Context context = a.getApplicationContext();
                if (!c1.a())
                {
                    com.tcolligan.hackexample.b.a a1 = new com.tcolligan.hackexample.b.a(c1.d());
                    com.tcolligan.hackexample.b.b.a().a(context, a1);
                    a.startActivity(com.tcolligan.hackexample.activities.BuyStuffActivity.a(a));
                    a.finish();
                }
                c1.a(context);
            }

            
            {
                a = RegisterActivity.this;
                super();
            }
        })).execute(new String[] {
            "http://androidhacktest-apppartner.rhcloud.com/scripts/register.php"
        });
    }

    private boolean a()
    {
        String s = a.getText().toString();
        String s1 = b.getText().toString();
        String s2 = c.getText().toString();
        return !TextUtils.isEmpty(s) && !TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2);
    }

    private boolean b()
    {
        return b.getText().toString().equals(c.getText().toString());
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f04001c);
        a = (EditText)findViewById(0x7f0c006c);
        b = (EditText)findViewById(0x7f0c006d);
        c = (EditText)findViewById(0x7f0c006e);
    }

    public void onRegisterButtonClicked(View view)
    {
        if (!a())
        {
            Toast.makeText(getApplicationContext(), 0x7f06001e, 0).show();
            return;
        }
        if (!b())
        {
            Toast.makeText(getApplicationContext(), 0x7f060022, 0).show();
            return;
        }
        if (!d.a(this))
        {
            Toast.makeText(getApplicationContext(), 0x7f060021, 0).show();
            return;
        } else
        {
            a(a.getText().toString(), b.getText().toString());
            return;
        }
    }
}
