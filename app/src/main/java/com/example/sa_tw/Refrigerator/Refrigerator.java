package com.example.sa_tw.Refrigerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sa_tw.Mediator.Mediator;
import com.example.sa_tw.R;

public class Refrigerator extends AppCompatActivity {
    Mediator mediator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrigerator);

        Button rent_btn = (Button)findViewById(R.id.Rent);
        Button open_btn = (Button)findViewById(R.id.Open);
        Button myrefrigerator_btn = (Button)findViewById(R.id.myrefrigerator);

        rent_btn.setOnClickListener(show_rent_gui);
        open_btn.setOnClickListener(show_open_gui);
        myrefrigerator_btn.setOnClickListener(show_myrefrigerator_gui);


        mediator = Mediator.getInstance();
        mediator.setup();
        mediator.setRefrigerator_GUI(this);
        mediator.setDbMgr_activity(this);

    }

    private View.OnClickListener show_rent_gui = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mediator.show_rent_GUI();
        }
    };
    private View.OnClickListener show_open_gui = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mediator.show_open_GUI();
        }
    };
    private View.OnClickListener show_myrefrigerator_gui = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mediator.show_myrefrigerator_GUI();
        }
    };
}
