package com.example.sa_tw.OTP;


import com.example.sa_tw.OTP.OTP;

public class Fail_over extends OTP_state {
    @Override
    public void check_otp(OTP otp, String enter) {
        otp.msg("You fail too many times!");
    }
}
