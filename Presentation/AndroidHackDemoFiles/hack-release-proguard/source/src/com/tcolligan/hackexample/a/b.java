// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.a;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.tcolligan.hackexample.a:
//            c

public class b extends AsyncTask
{
    public static interface a
    {

        public abstract void a();

        public abstract void a(c c1);
    }


    private static final String a = "PostRequestAsyncTask";
    private static final int b = 4096;
    private static final int c = 15000;
    private HashMap d;
    private a e;

    public b(HashMap hashmap, a a1)
    {
        d = hashmap;
        e = a1;
    }

    protected transient c a(String as[])
    {
        if (as == null || as.length <= 0) goto _L2; else goto _L1
_L1:
        Object obj;
        as = new URL(as[0]);
        obj = new android.net.Uri.Builder();
        java.util.Map.Entry entry;
        for (Iterator iterator = d.entrySet().iterator(); iterator.hasNext(); ((android.net.Uri.Builder) (obj)).appendQueryParameter((String)entry.getKey(), (String)entry.getValue()))
        {
            entry = (java.util.Map.Entry)iterator.next();
        }

          goto _L3
        as;
        Log.w("PostRequestAsyncTask", as);
_L2:
        as = null;
_L7:
        return as;
_L3:
        byte abyte0[] = ((android.net.Uri.Builder) (obj)).build().getEncodedQuery().getBytes();
        as = (HttpURLConnection)as.openConnection();
        as.setReadTimeout(15000);
        as.setConnectTimeout(15000);
        as.setRequestMethod("POST");
        as.setRequestProperty("Connection", "close");
        as.setDoInput(true);
        as.setDoOutput(true);
        as.setFixedLengthStreamingMode(abyte0.length);
        OutputStream outputstream = as.getOutputStream();
        outputstream.write(abyte0);
        outputstream.close();
        if (as.getResponseCode() != 200) goto _L2; else goto _L4
_L4:
        byte abyte1[];
        abyte0 = new ByteArrayOutputStream();
        as = as.getInputStream();
        abyte1 = new byte[4096];
_L5:
        int i = as.read(abyte1);
        if (i == -1)
        {
            break MISSING_BLOCK_LABEL_231;
        }
        abyte0.write(abyte1, 0, i);
          goto _L5
        abyte0.close();
        as.close();
        abyte0 = new c(abyte0.toByteArray());
        as = abyte0;
        if (!abyte0.a()) goto _L7; else goto _L6
_L6:
        as = abyte0;
        if (abyte0.b()) goto _L7; else goto _L8
_L8:
        Log.w("PostRequestAsyncTask", abyte0.c());
        return abyte0;
    }

    protected void a(c c1)
    {
label0:
        {
            if (e != null)
            {
                if (c1 != null)
                {
                    break label0;
                }
                e.a();
            }
            return;
        }
        e.a(c1);
    }

    protected Object doInBackground(Object aobj[])
    {
        return a((String[])aobj);
    }

    protected void onPostExecute(Object obj)
    {
        a((c)obj);
    }
}
