package com.example.sa_tw.Refrigerator.MyRefrigerator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sa_tw.Mediator.Mediator;
import com.example.sa_tw.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MyRefrigerator extends AppCompatActivity {
    Mediator mediator ;
    TextView record;
    TextView t1;
    Button cancel_btn;
    private Timer mTimer;
    Handler handler;
    ArrayList<String> r_size_id = new ArrayList<String>();
    ArrayList<String> r_place = new ArrayList<String>();
    ArrayList<String> r_date = new ArrayList<String>();
    ArrayList<String> r_countdown = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTimer = new Timer();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordlist);


        mediator = Mediator.getInstance();
        mediator.setMyRefrigerator_GUI(this);
        mediator.setDbMgr_activity(this);

        t1 = (TextView)findViewById(R.id.text2);
        record = (TextView)findViewById(R.id.record);
        cancel_btn = (Button) findViewById(R.id.cancel);
        t1.setText("以下是您已租用的冰箱：");
        t1.setTextSize(18);
        record.setTextSize(18);
        try {
            mediator.load_record();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setTimerTask1();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if(r_size_id == null){
                    record.setText("");
                }else{
                    for(int i = 0 ; i < r_size_id.size() ; i++){
                        int n = i+1;
                        record.append("這是您租借的第"+n+"台冰箱：\n");
                        record.append("　　冰箱地點：" + r_place.get(i) + "\n");
                        record.append("　　冰箱編號：" + r_size_id.get(i) + "\n");
                        record.append("　　租借日期：" + r_date.get(i) + "\n");
                        record.append("　　剩餘到期日：" + r_countdown.get(i) + "\n");
                        record.append("\n");
                    }
                }
            }
        };
        cancel_btn.setOnClickListener(cancel);

    }
    protected void onDestroy() {
        super.onDestroy();
        // cancel timer
        mTimer.cancel();
    }

    private void setTimerTask1() {
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mediator.output_item_All();
                mediator.output_Address();
                mediator.output_State();
                mediator.output_Date();
                handler.sendEmptyMessage(0);
            }
        }, 1000);
    }
    public void set_size_id(ArrayList a){
        this.r_size_id = a;
    }
    public void set_place(ArrayList a){
        this.r_place = a;
    }
    public void set_date(ArrayList a){
        this.r_date = a;
    }
    public void set_countdown(ArrayList a){
        this.r_countdown = a;
    }



    View.OnClickListener  cancel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };
    public void onBackPressed(){
        this.finish();
    }
}
