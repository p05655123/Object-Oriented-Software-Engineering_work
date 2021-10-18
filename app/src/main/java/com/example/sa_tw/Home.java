package com.example.sa_tw;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.sa_tw.Mediator.*;
import com.example.sa_tw.Factory.BackIMG1;
import com.example.sa_tw.Factory.BackIMG2;
import com.example.sa_tw.Factory.BackIMG3;
import com.example.sa_tw.Factory.Factory;

import java.util.Random;

public class Home extends AppCompatActivity {
    private Mediator mediator;
    ImageView image;
    int imageResource;
    int image_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Random rnd = new Random();

        image_no = 1;
        image = (ImageView) findViewById(R.id.imageView);
        Button refrigerator_btn = (Button)findViewById(R.id.Refrigerator);
        Button deposit_btn = (Button)findViewById(R.id.Deposit);
        Button info_btn = (Button)findViewById(R.id.Info);
        Button introduction_btn = (Button)findViewById(R.id.Introduction);
        Button change_btn = (Button)findViewById(R.id.change);

        refrigerator_btn.setOnClickListener(show_Refrigerator_gui);
        deposit_btn.setOnClickListener(show_despoist_gui);
        info_btn.setOnClickListener(show_info_gui);
        introduction_btn.setOnClickListener(show_introduction_gui);
        change_btn.setOnClickListener(change);
        mediator = Mediator.getInstance();
        mediator.setup();
        mediator.setHome_GUI(this);
        mediator.setDbMgr_activity(this);

    }

    private View.OnClickListener show_despoist_gui = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mediator.show_deposit_GUI();
        }
    };
    private View.OnClickListener show_Refrigerator_gui = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mediator.show_Refrigerator_GUI();
        }
    };

    private View.OnClickListener show_introduction_gui = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mediator.show_introduction_GUI();
        }
    };

    private View.OnClickListener show_info_gui = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mediator.show_info_GUI();
        }
    };
    private View.OnClickListener change = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Factory backIMG1 = new BackIMG1();
            Factory backIMG2 = new BackIMG2();
            Factory backIMG3 = new BackIMG3();
            if(image_no == 1){
                image_no = 2;
                String uri = "@drawable/" + backIMG2.getbackimg(); //圖片路徑和名稱
                imageResource = getResources().getIdentifier(uri, null, getPackageName()); //取得圖片Resource位子
            }else if(image_no == 2){
                image_no = 3;
                String uri = "@drawable/" + backIMG3.getbackimg(); //圖片路徑和名稱
                imageResource = getResources().getIdentifier(uri, null, getPackageName()); //取得圖片Resource位子
            }else{
                image_no = 1;
                String uri = "@drawable/" + backIMG1.getbackimg(); //圖片路徑和名稱
                imageResource = getResources().getIdentifier(uri, null, getPackageName()); //取得圖片Resource位子
            }
            image.setImageResource(imageResource);
        }
    };

    public void onBackPressed(){
        this.finish();

    }
}
