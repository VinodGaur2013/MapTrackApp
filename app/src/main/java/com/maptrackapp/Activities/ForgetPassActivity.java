package com.maptrackapp.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.maptrackapp.R;
import com.maptrackapp.Utils.CommonMethod;

public class ForgetPassActivity extends AppCompatActivity {

    EditText mEmail;
    TextView mTitle;
    Button mSignUpButton;
    ProgressBar pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) toolbar.findViewById(R.id.title);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTitle.setText(R.string.action_forget);

        mEmail = (EditText) findViewById(R.id.etEmail);
        pd=(ProgressBar)findViewById(R.id.pd);
        pd.setVisibility(View.GONE);
        mSignUpButton = (Button) findViewById(R.id.btnSignupSubmit);


        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptReg();
            }
        });
    }

    private void attemptReg() {

        if (!CommonMethod.isValidEmail(mEmail.getText().toString()))
        { AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(getResources().getString (R.string.error_email));
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // Toast.makeText(LoginActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            //mEmail.setError(getResources().getString (R.string.error_email));
            return;
        } else{ Toast.makeText(getApplicationContext(), "Password send to your email address.", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(ForgetPassActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
