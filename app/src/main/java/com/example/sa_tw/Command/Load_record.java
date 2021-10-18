package com.example.sa_tw.Command;

import com.example.sa_tw.Mediator.Mediator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Load_record extends Command {
    public Load_record(Mediator mediator){
        super(mediator);
    }

    @Override
    protected void process(JSONObject result) {

        try {
            JSONArray jsonArray = result.getJSONArray("records");
            mediator.set_recodlistGUI_records(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
