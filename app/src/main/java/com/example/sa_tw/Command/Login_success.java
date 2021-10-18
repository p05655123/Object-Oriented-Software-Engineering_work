package com.example.sa_tw.Command;

import com.example.sa_tw.Mediator.Mediator;

import org.json.JSONException;
import org.json.JSONObject;

public class Login_success extends Command {
    public Login_success(Mediator mediator){
        super(mediator);
    }

    protected void process(JSONObject result){
        try {
            String name_id= result.get("ID").toString();
            String name= result.get("NAME").toString();
            String email=result.get("EMAIL").toString();
            int balance=Integer.parseInt(result.get("BALANCE").toString());
            mediator.show_home_GUI();
            mediator.setuserInfo(name_id,name,email,balance);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
