// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Switch;
import com.tcolligan.hackexample.b;

public class DevOptionsActivity extends AppCompatActivity
{

    private Switch a;

    public DevOptionsActivity()
    {
    }

    public static Intent a(Context context)
    {
        return new Intent(context, com/tcolligan/hackexample/activities/DevOptionsActivity);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f04001a);
        a = (Switch)findViewById(0x7f0c006b);
    }

    public void onDestroy()
    {
        super.onDestroy();
        b.a(this, a.isChecked());
    }

    public void onResume()
    {
        super.onResume();
        a.setChecked(b.a(this));
    }
}
