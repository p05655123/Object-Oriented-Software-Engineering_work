package com.example.sa_tw;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.sa_tw.Mediator.*;


public class Deposit extends AppCompatActivity {
    Mediator mediator = Mediator.getInstance();
    EditText textmoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);


        Button confirm_btn = (Button)findViewById(R.id.confirm);
        textmoney = (EditText)findViewById(R.id.Amount);

        confirm_btn.setOnClickListener(deposit);


        mediator.setDeposit_GUI(this);
        mediator.setDbMgr_activity(this);
        mediator.reload_balance();
    }
    private View.OnClickListener deposit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String enter = textmoney.getText().toString();
            if (enter.isEmpty()){
                mediator.make_toast("您尚未輸入儲值金額");
            }else if(Integer.parseInt(enter) <= 0){
                mediator.make_toast("請輸入大於等於一的金額");
            }else if(Integer.parseInt(enter) > 10000){
                mediator.make_toast("單次儲值僅接受10000元(含)以下金額");
            }else {
                mediator.depoist(textmoney.getText().toString());
            }
        }
    };

    public void onBackPressed(){
        this.finish();

    }
}
