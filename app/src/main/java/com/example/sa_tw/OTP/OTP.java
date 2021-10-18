package com.example.sa_tw.OTP;


import com.example.sa_tw.Mediator.*;
import com.example.sa_tw.OTP.Fail_over;
import com.example.sa_tw.OTP.Normal;
import com.example.sa_tw.OTP.OTP_state;

import java.util.Random;

public class OTP {
    Mediator mediator;
    private static OTP otp = new OTP();
    private String password="";
    private OTP_state[] otpStates = {new Normal(),new Fail_over()};
    private OTP_state otp_state;

    private OTP(){
        mediator = Mediator.getInstance();

    }
    public static OTP getInstance(){
        return otp;
    }
    public void creat_password(){
        otp_state = new Normal();
        password="";
        Random random = new Random();
        while (password.length() != 6){
            password+=random.nextInt(9);
        }
        mediator.send_OTP(password);
    }
    public void check_Password(String enter){
        otp_state.check_otp(this,enter);
    }
    public String getPassword(){
        return password;
    }
    public void setOtp_state(int index){
        this.otp_state=otpStates[index];
    }
    public void check_success(){
        mediator.OTP_checked();
    }
    public void msg(String m){
        mediator.make_toast(m);
    }
}
