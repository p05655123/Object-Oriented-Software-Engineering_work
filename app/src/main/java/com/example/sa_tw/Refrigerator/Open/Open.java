package com.example.sa_tw.Refrigerator.Open;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sa_tw.Mediator.Mediator;
import com.example.sa_tw.R;

public class Open extends AppCompatActivity {
    Mediator mediator;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediator = Mediator.getInstance();
        mediator.setOpen_GUI(this);
        mediator.setDbMgr_activity(this);
        setContentView(R.layout.activity_open);
        t1 = (TextView) findViewById(R.id.textView);
        t1.setText("您的冰箱已成功租借\n" +
                "您是否現在就要開啟您的冰箱？");
        t1.setTextSize(20);
        Button confirm_btn = (Button)findViewById(R.id.confirm);
        Button cancel_btn = (Button)findViewById(R.id.cancel);
        confirm_btn.setOnClickListener(confirm);
        cancel_btn.setOnClickListener(cancel);
    }

    View.OnClickListener confirm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           SocketClient client = new SocketClient();
            client.execute("");
            t1.setText("冰箱已開啟囉");
            t1.setTextSize(30);
        }
    };
    View.OnClickListener cancel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mediator.open_show_home_GUI();
        }
    };
}
