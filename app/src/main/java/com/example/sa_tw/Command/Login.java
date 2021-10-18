package com.example.sa_tw.Command;

import com.example.sa_tw.Mediator.Mediator;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends Command {
    public Login(Mediator mediator){
        super(mediator);
    }

    protected void process(JSONObject result){
        if(result.has("Fail")){
            try {
                mediator.make_toast(result.get("Fail").toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            mediator.login_success();
        }
    }
}
