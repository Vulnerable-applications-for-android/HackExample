package com.tcolligan.hackexample.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.tcolligan.hackexample.DevOptions;
import com.tcolligan.hackexample.R;

public class DevOptionsActivity extends AppCompatActivity
{
    // ================================================================================
    // Static Intent Methods
    // ================================================================================

    public static Intent getIntent(Context context)
    {
        return new Intent(context, DevOptionsActivity.class);
    }

    // ================================================================================
    // Class Properties
    // ================================================================================

    private Switch stuffIsFreeSwitch;

    // ================================================================================
    // Life-cycle Methods
    // ================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_options);

        stuffIsFreeSwitch = (Switch) findViewById(R.id.stuffIsFreeSwitch);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        stuffIsFreeSwitch.setChecked(DevOptions.stuffIsFree(this));
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        DevOptions.setStuffIsFree(this, stuffIsFreeSwitch.isChecked());
    }
}
