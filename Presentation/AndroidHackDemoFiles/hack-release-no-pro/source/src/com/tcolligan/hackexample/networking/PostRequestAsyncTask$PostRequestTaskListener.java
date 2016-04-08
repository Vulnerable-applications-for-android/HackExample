// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.networking;


// Referenced classes of package com.tcolligan.hackexample.networking:
//            PostRequestAsyncTask, Response

public static interface 
{

    public abstract void onInvalidResponse();

    public abstract void onResponseReceived(Response response);
}
