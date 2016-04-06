package com.tcolligan.hackexample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tcolligan.hackexample.R;
import com.tcolligan.hackexample.networking.NetworkingHelper;
import com.tcolligan.hackexample.networking.PostRequestAsyncTask;
import com.tcolligan.hackexample.networking.Response;
import com.tcolligan.hackexample.user.UserManager;

import org.json.JSONObject;

import java.util.HashMap;

public class BuyStuffActivity extends AppCompatActivity
{
    // ================================================================================
    // Static Intent Methods
    // ================================================================================

    public static Intent getIntent(Context context)
    {
        return new Intent(context, BuyStuffActivity.class);
    }

    // ================================================================================
    // Class Properties
    // ================================================================================

    private static final double PRICE = 10.00;
    private TextView currentDebtTextView;

    // ================================================================================
    // Life-cycle Methods
    // ================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_stuff);

        currentDebtTextView = (TextView) findViewById(R.id.currentDebtTextView);
        setCurrentDebt(0.0);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        fetchCurrentDebt();
    }

    // ================================================================================
    // Button Click Methods
    // ================================================================================

    public void onBuyStuffButtonClicked(View v)
    {
        HashMap<String, String> postDataParams = NetworkingHelper.createPostDictWithUserInfo();
        postDataParams.put("additional_debt", Double.toString(PRICE));

        new PostRequestAsyncTask(postDataParams, new PostRequestAsyncTask.PostRequestTaskListener()
        {
            @Override
            public void onResponseReceived(Response response)
            {
                if (!response.isError())
                {
                    fetchCurrentDebt();
                }

                Context context = getApplicationContext();
                response.displayMessageToastIfNecessary(context);
            }

            @Override
            public void onInvalidResponse()
            {
                Toast.makeText(getApplicationContext(), R.string.error_toast_text, Toast.LENGTH_SHORT).show();
            }
        }).execute(NetworkingHelper.ADD_DEBT);
    }

    public void onLogoutButtonClicked(View v)
    {
        UserManager.getInstance().logout(getApplicationContext());

        startActivity(LoginActivity.getIntent(this));
        finish();
    }

    // ================================================================================
    // Class Instance Methods
    // ================================================================================

    private void setCurrentDebt(double currentDebtAmount)
    {
        currentDebtTextView.setText(getString(R.string.current_debt_text, String.format("%.2f", currentDebtAmount)));
    }

    private void fetchCurrentDebt()
    {
        HashMap<String, String> postDataParams = NetworkingHelper.createPostDictWithUserInfo();

        new PostRequestAsyncTask(postDataParams, new PostRequestAsyncTask.PostRequestTaskListener()
        {
            @Override
            public void onResponseReceived(Response response)
            {
                if (!response.isError())
                {
                    JSONObject debtData = response.getData();

                    try
                    {
                        double debtAmount = debtData.getDouble("debt_amount");
                        setCurrentDebt(debtAmount);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onInvalidResponse()
            {
                Toast.makeText(getApplicationContext(), R.string.error_toast_text, Toast.LENGTH_SHORT).show();
            }
        }).execute(NetworkingHelper.FETCH_DEBT);
    }
}
