package com.example.sa_tw.Command;

import com.example.sa_tw.Mediator.*;

import org.json.JSONException;
import org.json.JSONObject;

public class Get_item extends Command {
    public Get_item(Mediator mediator){
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
