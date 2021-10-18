package com.example.sa_tw.Command;

import com.example.sa_tw.Mediator.Mediator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Load_refrigerator extends Command {
    public Load_refrigerator(Mediator mediator){
        super(mediator);
    }

    protected void process(JSONObject result) {
        try {
            JSONArray jsonArray = result.getJSONArray("refrigerator");
            mediator.setRefrigerator(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
