package com.example.sa_tw;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sa_tw.Mediator.Mediator;

public class INFO extends AppCompatActivity {
    Mediator mediator = Mediator.getInstance();
    TextView acc;
    TextView name;
    TextView email;
    TextView balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        this.mediator = Mediator.getInstance();
        mediator.setInfo_GUI(this);
        mediator.setDbMgr_activity(this);

        TextView t1 = (TextView)findViewById(R.id.text);

        TextView t2 = (TextView)findViewById(R.id.text2);
        acc = (TextView)findViewById(R.id.acc);
        acc.setTextSize(20);
        TextView t3 = (TextView)findViewById(R.id.text3);
        name = (TextView)findViewById(R.id.name);
        name.setTextSize(20);

        TextView t4 = (TextView)findViewById(R.id.text4);
        email = (TextView)findViewById(R.id.email);
        email.setTextSize(16);

        TextView t5 = (TextView)findViewById(R.id.text5);
        balance = (TextView)findViewById(R.id.Balance);
        balance.setTextSize(20);


        mediator.output_acc();
        mediator.output_name();
        mediator.output_email();
        mediator.output_balance();
    }


    public void set_acc(String a){
        acc.setText(a);
    }

    public void set_name(String n){
        name.setText(n);
    }

    public void set_email(String e){
        email.setText(e);
    }

    public void set_balance(int intbalance){
        String Sbalance = String.valueOf(intbalance);
        balance.setText("$"+Sbalance);
    }

    public void onBackPressed(){
        this.finish();

    }
}
