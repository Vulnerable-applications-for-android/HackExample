// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tcolligan.hackexample.networking;

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

// Referenced classes of package com.tcolligan.hackexample.networking:
//            Response

public class PostRequestAsyncTask extends AsyncTask
{
    public static interface PostRequestTaskListener
    {

        public abstract void onInvalidResponse();

        public abstract void onResponseReceived(Response response);
    }


    private static final int BUFFER_SIZE = 4096;
    private static final String LOG_TAG = "PostRequestAsyncTask";
    private static final int TIMEOUT_MILLISECONDS = 15000;
    private HashMap postDataParams;
    private PostRequestTaskListener postRequestTaskListener;

    public PostRequestAsyncTask(HashMap hashmap, PostRequestTaskListener postrequesttasklistener)
    {
        postDataParams = hashmap;
        postRequestTaskListener = postrequesttasklistener;
    }

    protected transient Response doInBackground(String as[])
    {
        if (as == null || as.length <= 0) goto _L2; else goto _L1
_L1:
        Object obj;
        as = new URL(as[0]);
        obj = new android.net.Uri.Builder();
        java.util.Map.Entry entry;
        for (Iterator iterator = postDataParams.entrySet().iterator(); iterator.hasNext(); ((android.net.Uri.Builder) (obj)).appendQueryParameter((String)entry.getKey(), (String)entry.getValue()))
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
        abyte0 = new Response(abyte0.toByteArray());
        as = abyte0;
        if (!abyte0.isError()) goto _L7; else goto _L6
_L6:
        as = abyte0;
        if (abyte0.shouldDisplayMessage()) goto _L7; else goto _L8
_L8:
        Log.w("PostRequestAsyncTask", abyte0.getMessage());
        return abyte0;
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected void onPostExecute(Response response)
    {
label0:
        {
            if (postRequestTaskListener != null)
            {
                if (response != null)
                {
                    break label0;
                }
                postRequestTaskListener.onInvalidResponse();
            }
            return;
        }
        postRequestTaskListener.onResponseReceived(response);
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Response)obj);
    }
}
