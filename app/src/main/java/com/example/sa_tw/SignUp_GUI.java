package com.example.sa_tw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sa_tw.Mediator.Mediator;

import java.util.ArrayList;

public class SignUp_GUI extends AppCompatActivity {
    EditText input_name;
    EditText input_email;
    EditText input_account;
    EditText input_password;
    EditText input_confirm;
    Mediator mediator;
    TextView namemsg;
    TextView emailemsg;
    TextView accountmsg;
    TextView passwordmsg;
    TextView confirmmsg;
    ArrayList<TextView> msgs;
    ArrayList<EditText>inputs;
    Animation animShake ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_gui);

        inputs=new ArrayList<>();
        mediator = Mediator.getInstance();
        mediator.setSignUp_GUI(this);
        mediator.setDbMgr_activity(this);
        msgs=new ArrayList<>();
        animShake= AnimationUtils.loadAnimation(this, R.anim.shake);
        ConstraintLayout constraintLayout = (ConstraintLayout)findViewById(R.id.menu);


        constraintLayout.setScrollContainer(false);


        /**NAME**/
        input_name=(EditText)findViewById(R.id.editText2);
        input_name.setOnFocusChangeListener(namechange);
        input_name.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
        inputs.add(input_name);

        namemsg = (TextView)findViewById(R.id.ntext);
        namemsg.setTextSize(12);
        namemsg.setTextColor(Color.parseColor("#FF0000"));
        namemsg.setText("Please enter your name!");
        msgs.add(namemsg);


        /**E-MAIL**/
        input_email=(EditText)findViewById(R.id.editText);
        input_email.setOnFocusChangeListener(emailchange);
        input_email.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
        inputs.add(input_email);


        emailemsg = (TextView)findViewById(R.id.etext);
        emailemsg.setTextSize(12);
        emailemsg.setText("Please enter your E-Mail!");
        emailemsg.setTextColor(Color.parseColor("#FF0000"));
        msgs.add(emailemsg);

        /**ACC**/
        input_account=(EditText)findViewById(R.id.editText3);
        input_account.setOnFocusChangeListener(accchange);
        input_account.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
        inputs.add(input_account);

        accountmsg= (TextView)findViewById(R.id.atext);
        accountmsg.setTextSize(12);
        accountmsg.setTextColor(Color.parseColor("#FF0000"));
        accountmsg.setText("Please enter your Account!");
        msgs.add(accountmsg);





        /**PASSWORD**/
        input_password=(EditText)findViewById(R.id.editText4);
        input_password.setOnFocusChangeListener(passwordchange);
        input_password.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
        inputs.add(input_password);


        passwordmsg = (TextView)findViewById(R.id.ptext);
        passwordmsg.setTextSize(12);
        passwordmsg.setTextColor(Color.parseColor("#FF0000"));
        passwordmsg.setText("Please enter your Password!");
        msgs.add(passwordmsg);

        /**Confirm**/
        input_confirm=(EditText)findViewById(R.id.editText5);
        input_confirm.setOnFocusChangeListener(confirmchange);
        input_confirm.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
        inputs.add(input_confirm);


        confirmmsg = (TextView)findViewById(R.id.ctext);
        confirmmsg.setTextSize(12);
        confirmmsg.setTextColor(Color.parseColor("#FF0000"));
        confirmmsg.setText("Please confirm your Password!");
        msgs.add(confirmmsg);


        Button confirm_btn = (Button)findViewById(R.id.confirm);
        Button cancel_btn = (Button)findViewById(R.id.cancel);

        confirm_btn.setOnClickListener(confirm);
        cancel_btn.setOnClickListener(cancel);

        namemsg.setVisibility(View.INVISIBLE);
        emailemsg.setVisibility(View.INVISIBLE);
        accountmsg.setVisibility(View.INVISIBLE);
        passwordmsg.setVisibility(View.INVISIBLE);
        confirmmsg.setVisibility(View.INVISIBLE);
    }

    View.OnClickListener  cancel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };
    public void onBackPressed(){
        this.finish();
    }
    View.OnFocusChangeListener namechange = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus){
                if (input_name.getText().toString().isEmpty()){
                    namemsg.setVisibility(View.VISIBLE);
                }else {
                    namemsg.setVisibility(View.INVISIBLE);
                }
            }
        }
    };
    View.OnFocusChangeListener emailchange = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus){
                if (input_email.getText().toString().isEmpty()){
                    emailemsg.setText("Please enter your E-Mail!");
                    emailemsg.setVisibility(View.VISIBLE);
                }else {
                    if (input_email.getText().toString().indexOf("@")<0){
                        emailemsg.setText("Your email address should have \"@\"");
                        emailemsg.setVisibility(View.VISIBLE);
                    }else {
                        emailemsg.setVisibility(View.INVISIBLE);
                    }

                }
            }
        }
    };

    View.OnFocusChangeListener accchange = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus){
                if (input_account.getText().toString().isEmpty()){
                    accountmsg.setVisibility(View.VISIBLE);
                }else {
                    accountmsg.setVisibility(View.INVISIBLE);
                }
            }
        }
    };

    View.OnFocusChangeListener passwordchange = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus){
                if (input_password.getText().toString().isEmpty()){
                    passwordmsg.setVisibility(View.VISIBLE);
                }else {
                    passwordmsg.setVisibility(View.INVISIBLE);
                }
            }
        }
    };

    View.OnFocusChangeListener confirmchange = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus){
                if (input_confirm.getText().toString().isEmpty()){
                    confirmmsg.setVisibility(View.VISIBLE);
                }else {
                    confirmmsg.setVisibility(View.INVISIBLE);
                }
            }
        }
    };

    View.OnClickListener confirm = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Boolean can_register = true;
            for (int i = 0 ; i < inputs.size() ; i++) {
                inputs.get(i).requestFocus();
                inputs.get(i).clearFocus();
                String temp = inputs.get(i).getText().toString();
                if( msgs.get(i).getVisibility()==View.VISIBLE){
                    msgs.get(i).setVisibility(View.VISIBLE);
                    msgs.get(i).startAnimation(animShake);
                    inputs.get(i).requestFocus();
                    can_register = false;
                    break;
                }
            }

            if (can_register){
                if (input_password.getText().toString().equals(input_confirm.getText().toString())){
                    mediator.check_signup_data(input_account.getText().toString(),input_email.getText().toString());
                }else {
                    input_confirm.requestFocus();
                    confirmmsg.setText("The confirm password is different \nfrom the password.");
                    confirmmsg.setVisibility(View.VISIBLE);
                }
            }
        }
    };
    public String get_name(){
        return input_name.getText().toString();

    }
    public String get_email(){
        return input_email.getText().toString();
    }
    public String get_acc(){
        return input_account.getText().toString();
    }
    public String get_pwd(){
        return input_password.getText().toString();
    }
}
