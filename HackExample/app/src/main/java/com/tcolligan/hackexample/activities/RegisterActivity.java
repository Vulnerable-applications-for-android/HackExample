package com.tcolligan.hackexample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class RegisterActivity extends AppCompatActivity
{
    // ================================================================================
    // Static Intent Methods
    // ================================================================================

    public static Intent getIntent(Context context)
    {
        return new Intent(context, RegisterActivity.class);
    }

    // ================================================================================
    // Class Properties
    // ================================================================================

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    // ================================================================================
    // Life-Cycle Methods
    // ================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirmPasswordEditText);
    }

    // ================================================================================
    // Button Click Methods
    // ================================================================================

    public void onRegisterButtonClicked(View v)
    {
        if (!inputIsValid())
        {
            Toast.makeText(getApplicationContext(), R.string.invalid_input_toast_text, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passwordsMatch())
        {
            Toast.makeText(getApplicationContext(), R.string.passwords_no_match_toast_text, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Utils.isConnectedToInternet(this))
        {
            Toast.makeText(getApplicationContext(), R.string.no_internet_toast_text, Toast.LENGTH_SHORT).show();
            return;
        }

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        register(username, password);
    }

    // ================================================================================
    // Class Instance Methods
    // ================================================================================

    private boolean inputIsValid()
    {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        return !TextUtils.isEmpty(username) &&
                !TextUtils.isEmpty(password) &&
                !TextUtils.isEmpty(confirmPassword);
    }

    private boolean passwordsMatch()
    {
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        return password.equals(confirmPassword);
    }

    private void register(String username, String password)
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

                    // TODO: Launch main screen
                }

                response.displayMessageToastIfNecessary(context);
            }

            @Override
            public void onInvalidResponse()
            {
                Toast.makeText(getApplicationContext(), R.string.error_toast_text, Toast.LENGTH_SHORT).show();
            }
        }).execute(NetworkingHelper.REGISTER);
    }

}
