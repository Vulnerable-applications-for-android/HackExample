// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.activities;

import android.widget.Toast;
import com.tcolligan.hackexample.a.c;
import org.json.JSONObject;

// Referenced classes of package com.tcolligan.hackexample.activities:
//            BuyStuffActivity

class a
    implements com.tcolligan.hackexample.a.tivity._cls2
{

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
        BuyStuffActivity.a(a, d);
        return;
        c1;
        c1.printStackTrace();
        return;
    }

    (BuyStuffActivity buystuffactivity)
    {
        a = buystuffactivity;
        super();
    }
}
