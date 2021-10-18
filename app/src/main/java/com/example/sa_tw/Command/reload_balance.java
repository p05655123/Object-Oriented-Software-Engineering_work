package com.example.sa_tw.Command;

import com.example.sa_tw.Mediator.Mediator;

import org.json.JSONException;
import org.json.JSONObject;

public class reload_balance extends Command {
    public reload_balance(Mediator mediator){
        super(mediator);
    }

    @Override
    protected void process(JSONObject result) {

        try {
            int temp = Integer.parseInt(result.getString("BALANCE"));
            mediator.reset_balance(temp);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
