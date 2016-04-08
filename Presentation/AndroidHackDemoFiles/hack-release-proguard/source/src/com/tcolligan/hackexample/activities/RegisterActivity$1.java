// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.activities;

import android.widget.Toast;
import com.tcolligan.hackexample.a.c;
import com.tcolligan.hackexample.b.a;
import com.tcolligan.hackexample.b.b;

// Referenced classes of package com.tcolligan.hackexample.activities:
//            RegisterActivity, BuyStuffActivity

class a
    implements com.tcolligan.hackexample.a.tivity._cls1
{

    final RegisterActivity a;

    public void a()
    {
        Toast.makeText(a.getApplicationContext(), 0x7f06001a, 0).show();
    }

    public void a(c c1)
    {
        android.content.Context context = a.getApplicationContext();
        if (!c1.a())
        {
            a a1 = new a(c1.d());
            b.a().a(context, a1);
            a.startActivity(com.tcolligan.hackexample.activities.BuyStuffActivity.a(a));
            a.finish();
        }
        c1.a(context);
    }

    (RegisterActivity registeractivity)
    {
        a = registeractivity;
        super();
    }
}
