package com.maptrackapp.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.maptrackapp.R;
import com.maptrackapp.Utils.CommonMethod;
import com.maptrackapp.Utils.CommonUtils;
import com.maptrackapp.Utils.SessionManager;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    TextView mTitle, mTerms;
    EditText mUserName,mEmail,mTelephone,mPassword,mConfirmPass;
    CheckBox mCbTerm;
    Button mSignUpButton;
    SessionManager session;
    ProgressBar pd;
    Spinner spUserType;
    String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle=(TextView)toolbar.findViewById(R.id.title);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTitle.setText("Sign Up");

        CommonUtils.hideSoftKeyboard(SignUpActivity.this);

        session = new SessionManager(getApplicationContext());

        mTerms=(TextView)findViewById(R.id.tvTerms);
        mCbTerm=(CheckBox)findViewById(R.id.cbTerms);
        mUserName=(EditText)findViewById(R.id.etName);
        mEmail=(EditText)findViewById(R.id.etEmail);
        mTelephone=(EditText)findViewById(R.id.etTelephone);
        mPassword=(EditText)findViewById(R.id.etPass);
        mConfirmPass=(EditText)findViewById(R.id.etConfirmPass);
        spUserType=(Spinner)findViewById(R.id.spUserType);
        pd=(ProgressBar)findViewById(R.id.pd);
        pd.setVisibility(View.GONE);

        mSignUpButton=(Button)findViewById(R.id.btnSignupSubmit);
        setSpanable(mTerms, "I agree to terms & conditions", 11, 29);
        mSignUpButton.setOnClickListener(this);
    }

    public void setSpanable(TextView tv,String title, int from, int to){
        Spannable wordtoSpan = new SpannableString(title);//11,28
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent=new Intent(SignUpActivity.this, TermsActivity.class);
                startActivity(intent);
                //  Toast.makeText(SignUpActivity.this, "Clicked", Toast.LENGTH_LONG).show();
            }
        };
        wordtoSpan.setSpan(new ForegroundColorSpan(Color.rgb(9, 107, 244)), from, to, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(clickableSpan, from, to, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(wordtoSpan);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignupSubmit:
                attemptReg();
                break;
        }
    }

    private void attemptReg() {
        if (!CommonMethod.validateName(mUserName.getText().toString())){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Please input username...");
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // Toast.makeText(LoginActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            // mUserName.setError(getResources().getString (R.string.error_username));

        }else if (!CommonMethod.isValidEmail(mEmail.getText().toString())){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Please input valid email address...");
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // Toast.makeText(LoginActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            //mEmail.setError(getResources().getString (R.string.error_email));

        }else if (!CommonMethod.isValidTelephone(mTelephone.getText().toString())){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Please input your 10 digits mobile number ...");
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // Toast.makeText(LoginActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            //mTelephone.setError(getResources().getString (R.string.error_telephone));

        }else if (!CommonMethod.isValidPass(mPassword.getText().toString())){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Please input your secure password...");
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // Toast.makeText(LoginActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            //mPassword.setError(getResources().getString (R.string.error_pass));

        }else if (!mConfirmPass.getText().toString().equalsIgnoreCase(mPassword.getText().toString())){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Your entered password does not matched...");
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // Toast.makeText(LoginActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            // mConfirmPass.setError(getResources().getString (R.string.error_pass_not_match));

        } else if (!mCbTerm.isChecked()){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Please accept the terms to register...");
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // Toast.makeText(LoginActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            //mCbTerm.setError(getResources().getString (R.string.error_checkbox));

        } else{
            Intent intent=new Intent(SignUpActivity.this,DrawerActivity.class);
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
