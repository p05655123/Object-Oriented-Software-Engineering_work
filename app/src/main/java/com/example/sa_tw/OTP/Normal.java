package com.example.sa_tw.OTP;

public class Normal extends OTP_state {
    int fail_time = 0;
    @Override
    public void check_otp(OTP otp, String enter) {
        if(otp.getPassword().equals(enter)){
            otp.check_success();
        }else if(fail_time >= 3){
            otp.setOtp_state(1);
        }else {
            fail_time+=1;
            otp.msg("You have only "+(3-fail_time)+" chances left!");
        }
    }
}




