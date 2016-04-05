package com.tcolligan.hackexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity
{
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
    }

    // ================================================================================
    // Button Click Methods
    // ================================================================================

    public void onLoginButtonClicked(View v)
    {

    }
}
