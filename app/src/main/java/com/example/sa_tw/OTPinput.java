package com.example.sa_tw;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sa_tw.Mediator.*;

public class OTPinput extends AppCompatActivity {
    EditText inputotp;
    Mediator mediator;
    TextView msg1;
    Button btnconfirm;
    static OTPinput otp_input = new OTPinput();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_input);

        this.mediator = Mediator.getInstance();
        mediator.setOtp_GUI(this);

        inputotp = (EditText)findViewById(R.id.editText1);
        msg1 = (TextView)findViewById(R.id.text1);
        msg1.setText("已將一次性密碼寄至：\n"+ mediator.get_email());
        msg1.setTextSize(20);

        btnconfirm = (Button)findViewById(R.id.button);
        btnconfirm.setOnClickListener(confirm);
    }
    public void setMediator(Mediator mediator){
        this.mediator = mediator;
    }
    public static OTPinput getInstance(){
        return otp_input;
    }
    View.OnClickListener confirm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mediator.check_OTP(inputotp.getText().toString());
        }
    };
}
