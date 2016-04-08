// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.activities;

import android.widget.Toast;
import com.tcolligan.hackexample.a.c;

// Referenced classes of package com.tcolligan.hackexample.activities:
//            BuyStuffActivity

class a
    implements com.tcolligan.hackexample.a.tivity._cls1
{

    final BuyStuffActivity a;

    public void a()
    {
        Toast.makeText(a.getApplicationContext(), 0x7f06001a, 0).show();
    }

    public void a(c c1)
    {
        if (!c1.a())
        {
            BuyStuffActivity.a(a);
        }
        c1.a(a.getApplicationContext());
    }

    (BuyStuffActivity buystuffactivity)
    {
        a = buystuffactivity;
        super();
    }
}
