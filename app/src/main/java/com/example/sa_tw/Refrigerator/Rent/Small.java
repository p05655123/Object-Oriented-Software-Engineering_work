package com.example.sa_tw.Refrigerator.Rent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sa_tw.Mediator.Mediator;
import com.example.sa_tw.R;
import com.example.sa_tw.Refrigerator.Rent.Strategy.Coupon1;
import com.example.sa_tw.Refrigerator.Rent.Strategy.Coupon2;
import com.example.sa_tw.Refrigerator.Rent.Strategy.StrategyContext;

public class Small extends AppCompatActivity {
    Mediator mediator;
    String[] strings={"價格全面九折","租借天數多加五天"};
    String choose;
    private String getBundle1(){
        String place="";    //要存放的變數
        Bundle bundle = this.getIntent().getExtras();            //拿包裹
        if(bundle!=null) place=bundle.getString("name");  //拆包裹
        return place;
    }
    private String getBundle2(){
        String id="";    //要存放的變數
        Bundle bundle = this.getIntent().getExtras();            //拿包裹
        if(bundle!=null) id=bundle.getString("id");  //拆包裹
        return id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small);
        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        this.addContentView(constraintLayout, new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
        constraintLayout.setBackgroundResource(getResources().getIdentifier("@drawable/background_small", null, getPackageName()));

        /**確認按鈕**/
        Button confirm = new Button(this);
        confirm.setBackgroundResource(getResources().getIdentifier("@drawable/smallconfirm", null, getPackageName()));
        confirm.setX(180);
        confirm.setY(1580);
        constraintLayout.addView(confirm, new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        /**-------**/

        /**取消按鈕**/
        Button cancel = new Button(this);
        cancel.setBackgroundResource(getResources().getIdentifier("@drawable/smallcancel", null, getPackageName()));
        cancel.setX(580);
        cancel.setY(1580);
        constraintLayout.addView(cancel, new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        /**-------**/

        /**確認資訊**/
        TextView info = new TextView(this);
        info.setText("您選擇的冰箱是位在\n" +
                getBundle1() +
                "的「小」冰箱\n"+
                "冰箱編號為："+
                getBundle2()+
                "\n\n小冰箱的費用是\n" +
                "NT.30/30日\n" +
                "請再確認一次您確實要承租的是\n" +
                "「小」冰箱");
        info.setTextSize(19);
        info.setGravity(Gravity.CENTER);
        info.setX(140);
        info.setY(900);
        constraintLayout.addView(info, new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        /**-------**/

        /**冰箱圖片**/
        ImageView refrigerator = new ImageView(this);
        refrigerator.setImageResource(getResources().getIdentifier("@drawable/small", null, getPackageName()));
        refrigerator.setX(280);
        refrigerator.setY(225);
        constraintLayout.addView(refrigerator, new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        /**-------**/

        confirm.setOnClickListener(ok);
        cancel.setOnClickListener(back);

        mediator = Mediator.getInstance();
        mediator.setup();
        mediator.setSmall_GUI(this);
        mediator.setDbMgr_activity(this);
    }
    View.OnClickListener  ok = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            new AlertDialog.Builder(Small.this)
                    .setIcon(R.drawable.rlogo)
                    .setTitle("開幕優惠(只能擇一進行優惠)")
                    .setSingleChoiceItems(strings, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            choose = strings[which];
                        }
                    })
                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            StrategyContext c = new StrategyContext();
                            c.setCost(30);
                            c.setDays(30);
                            if(choose == "價格全面九折"){
                                c.setStrategy(new Coupon1());
                                mediator.creat_OTP(getBundle1() , getBundle2() , c.getDays() , (int) c.getCost());
                                mediator.S_show_otp_GUI();
                            }else if(choose == "租借天數多加五天"){
                                c.setStrategy(new Coupon2());
                                mediator.creat_OTP(getBundle1() , getBundle2() , c.getDays() , (int) c.getCost());
                                mediator.S_show_otp_GUI();
                            }else{
                                mediator.make_toast("請選擇一個優惠");
                            }
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .create()
                    .show();
        }
    };
    View.OnClickListener  back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mediator.show_rent_GUI();
        }
    };
    public void onBackPressed(){
        this.finish();

    }
}
