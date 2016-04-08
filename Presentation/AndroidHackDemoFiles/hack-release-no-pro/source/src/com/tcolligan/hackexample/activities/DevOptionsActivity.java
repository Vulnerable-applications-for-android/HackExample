// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Switch;
import com.tcolligan.hackexample.DevOptions;

public class DevOptionsActivity extends AppCompatActivity
{

    private Switch stuffIsFreeSwitch;

    public DevOptionsActivity()
    {
    }

    public static Intent getIntent(Context context)
    {
        return new Intent(context, com/tcolligan/hackexample/activities/DevOptionsActivity);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f04001a);
        stuffIsFreeSwitch = (Switch)findViewById(0x7f0c006b);
    }

    public void onDestroy()
    {
        super.onDestroy();
        DevOptions.setStuffIsFree(this, stuffIsFreeSwitch.isChecked());
    }

    public void onResume()
    {
        super.onResume();
        stuffIsFreeSwitch.setChecked(DevOptions.stuffIsFree(this));
    }
}
