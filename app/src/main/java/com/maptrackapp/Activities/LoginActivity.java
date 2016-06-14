package com.maptrackapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.maptrackapp.R;
import com.maptrackapp.Utils.SessionManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etEmail, etPass;
    Button btnSubmit;
    TextView tvForget, tvSignup, mTitle;
    CheckBox cbRemember;
    SessionManager session;
    ProgressBar pd;
    LinearLayout root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null)
            mTitle = (TextView) toolbar.findViewById(R.id.title);
        setSupportActionBar(toolbar);*/
        /*mTitle.setText("LOGIN");
        mTitle.setGravity(View.TEXT_ALIGNMENT_CENTER);*/

        session = new SessionManager(getApplicationContext());

        root=(LinearLayout)findViewById(R.id.root);


        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        tvForget = (TextView) findViewById(R.id.tvForget);
        tvSignup = (TextView) findViewById(R.id.tvSignup);


        tvSignup.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        tvForget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tvSignup:
                Intent iSignup = new Intent(LoginActivity.this, SignUpActivity.class);
                iSignup.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(iSignup);
                break;

            case R.id.btnSubmit:
                Intent i=new Intent(LoginActivity.this,DrawerActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                //   attemptLogin();
                break;

            case R.id.tvForget:
                Intent intent = new Intent(LoginActivity.this, ForgetPassActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}
