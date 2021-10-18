package com.example.sa_tw.Command;

import com.example.sa_tw.Mediator.Mediator;

import org.json.JSONException;
import org.json.JSONObject;

public class Creat_account extends Command {
    public Creat_account(Mediator mediator){
        super(mediator);
    }

    @Override
    protected void process(JSONObject result) {

        try {
            mediator.make_toast(result.get("Error").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
