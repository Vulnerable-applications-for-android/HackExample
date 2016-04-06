package com.tcolligan.hackexample.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tcolligan.hackexample.R;
import com.tcolligan.hackexample.Utils;
import com.tcolligan.hackexample.networking.NetworkingHelper;
import com.tcolligan.hackexample.networking.PostRequestAsyncTask;
import com.tcolligan.hackexample.networking.Response;
import com.tcolligan.hackexample.user.User;
import com.tcolligan.hackexample.user.UserManager;

import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity
{
    // ================================================================================
    // Static Intent Methods
    // ================================================================================

    public static Intent getIntent(Context context)
    {
        return new Intent(context, LoginActivity.class);
    }

    // ================================================================================
    // Class Properties
    // ================================================================================

    private EditText usernameEditText;
    private EditText passwordEditText;

    // ================================================================================
    // Life-Cycle Methods
    // ================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        UserManager.getInstance().loadCurrentUser(this);
        User currentUser = UserManager.getInstance().getCurrentUser();

        // Already logged in
        if (currentUser != null)
        {
            startActivity(BuyStuffActivity.getIntent(this));
            finish();
        }
    }

    // ================================================================================
    // Button Click Methods
    // ================================================================================

    public void onLoginButtonClicked(View v)
    {
        if (!inputIsValid())
        {
            Toast.makeText(getApplicationContext(), R.string.invalid_input_toast_text, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Utils.isConnectedToInternet(this))
        {
            Toast.makeText(getApplicationContext(), R.string.no_internet_toast_text, Toast.LENGTH_SHORT).show();
            return;
        }

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        login(username, password);
    }

    public void onRegisterButtonClicked(View v)
    {
        startActivity(RegisterActivity.getIntent(this));
    }

    // ================================================================================
    // Class Instance Methods
    // ================================================================================

    private boolean inputIsValid()
    {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password);
    }

    private void login(String username, String password)
    {
        String hashedPassword = Utils.md5(password);

        HashMap<String, String> postDataParams = NetworkingHelper.createPostDict();

        postDataParams.put("username", username);
        postDataParams.put("password", hashedPassword);

        new PostRequestAsyncTask(postDataParams, new PostRequestAsyncTask.PostRequestTaskListener()
        {
            @Override
            public void onResponseReceived(Response response)
            {
                Context context = getApplicationContext();

                if (!response.isError())
                {
                    JSONObject userData = response.getData();
                    User loggedInUser = new User(userData);
                    UserManager.getInstance().saveNewUser(context, loggedInUser);

                    startActivity(BuyStuffActivity.getIntent(LoginActivity.this));
                    finish();
                }

                response.displayMessageToastIfNecessary(context);
            }

            @Override
            public void onInvalidResponse()
            {
                Toast.makeText(getApplicationContext(), R.string.error_toast_text, Toast.LENGTH_SHORT).show();
            }
        }).execute(NetworkingHelper.LOGIN);
    }
}
