package com.example.sa_tw.Command;

import com.example.sa_tw.Mediator.Mediator;

import org.json.JSONException;
import org.json.JSONObject;

public class canregister extends Command {
    public canregister(Mediator mediator){
        super(mediator);
    }

    @Override
    protected void process(JSONObject result) {

        if(result.has("NOTEmpty")){
            try {
                mediator.make_toast(result.get("NOTEmpty").toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            mediator.register();
        }

    }
}
