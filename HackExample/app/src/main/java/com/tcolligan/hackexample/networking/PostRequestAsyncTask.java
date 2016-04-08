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
import java.util.Map;

/**
 * An AsyncTask for sending POST requests.
 *
 * Created on 2/26/16
 *
 * @author ThomasColligan
 */
public class PostRequestAsyncTask extends AsyncTask<String, Void, Response>
{
    // ================================================================================
    // Class Properties
    // ================================================================================

    private static final String LOG_TAG = "PostRequestAsyncTask";
    private static final int BUFFER_SIZE = 4096;
    private static final int TIMEOUT_MILLISECONDS = 15000;

    private HashMap<String, String> postDataParams;
    private PostRequestTaskListener postRequestTaskListener;

    // ================================================================================
    // Constructor
    // ================================================================================

    public PostRequestAsyncTask(HashMap<String, String> postDataParams, PostRequestTaskListener postRequestTaskListener)
    {
        this.postDataParams = postDataParams;
        this.postRequestTaskListener = postRequestTaskListener;
    }

    // ================================================================================
    // Overridden AsyncTask Methods
    // ================================================================================

    @Override
    protected Response doInBackground(String... urls)
    {
        if (urls != null && urls.length > 0)
        {
            try
            {
                //Create the URL
                String urlString = urls[0];
                URL url = new URL(urlString);
                Uri.Builder uriBuilder = new Uri.Builder();

                // Setup out POST parameter data
                for(Map.Entry<String, String> entry : postDataParams.entrySet())
                {
                    uriBuilder.appendQueryParameter(entry.getKey(), entry.getValue());
                }

                byte[] postData = uriBuilder.build().getEncodedQuery().getBytes();

                // Setup and connect to the URL
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(TIMEOUT_MILLISECONDS);
                httpURLConnection.setConnectTimeout(TIMEOUT_MILLISECONDS);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Connection", "close");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setFixedLengthStreamingMode(postData.length);

                // Write out our POST parameter data
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postData);
                outputStream.close();

                int responseCode = httpURLConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK)
                {
                    // Read in any response data
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    InputStream inputStream = httpURLConnection.getInputStream();

                    int bytesRead = -1;
                    byte[] buffer = new byte[BUFFER_SIZE];
                    while ((bytesRead = inputStream.read(buffer)) != -1)
                    {
                        baos.write(buffer, 0, bytesRead);
                    }

                    baos.close();
                    inputStream.close();

                    Response response = new Response(baos.toByteArray());

                    if (response.isError() && !response.shouldDisplayMessage())
                    {
                        Log.w(LOG_TAG, response.getMessage());
                    }

                    return response;
                }
            }
            catch (Exception e)
            {
                Log.w(LOG_TAG, e);
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Response response)
    {
        if (postRequestTaskListener != null)
        {
            if (response == null)
            {
                postRequestTaskListener.onInvalidResponse();
            }
            else
            {
                postRequestTaskListener.onResponseReceived(response);
            }
        }
    }

    // ================================================================================
    // Interfaces
    // ================================================================================

    public interface PostRequestTaskListener
    {
        void onResponseReceived(Response response);
        void onInvalidResponse();
    }
}