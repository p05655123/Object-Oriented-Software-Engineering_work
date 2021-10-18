package com.example.sa_tw.Refrigerator.Rent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import com.example.sa_tw.Mediator.Mediator;
import com.example.sa_tw.R;
import org.json.JSONException;
import java.util.ArrayList;



public class Rent extends AppCompatActivity {
    Mediator mediator = Mediator.getInstance();
    String choice;
    Button confirm;
    RadioButton rbp1;
    RadioButton rbp2;
    RadioButton rbp3;
    RadioButton rbs1;
    RadioButton rbs2;
    RadioButton rbs3;
    private ArrayList<String> size_id = new ArrayList<String>();
    private ArrayList<String> size = new ArrayList<String>();
    private ArrayList<String> place = new ArrayList<String>();
    private ArrayList<String> rent_or_not = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        rbp1 = (RadioButton) findViewById(R.id.rbp1);
        rbp1.setText("管理學院A館 系辦外");
        rbp1.setTextSize(20);
        rbp2 = (RadioButton) findViewById(R.id.rbp2);
        rbp2.setText("管理學院B館 MB大廳");
        rbp2.setTextSize(20);
        rbp3 = (RadioButton) findViewById(R.id.rbp3);
        rbp3.setText("管理學院D館 MD大廳");
        rbp3.setTextSize(20);

        rbs1 = (RadioButton) findViewById(R.id.rbs1);
        rbs1.setText("大(NT.50/30日)");
        rbs1.setTextSize(20);
        rbs2 = (RadioButton) findViewById(R.id.rbs2);
        rbs2.setText("中(NT.40/30日)");
        rbs2.setTextSize(20);
        rbs3 = (RadioButton) findViewById(R.id.rbs3);
        rbs3.setText("小(NT.30/30日)");
        rbs3.setTextSize(20);

        confirm = (Button) findViewById(R.id.confirm) ;

        mediator = Mediator.getInstance();
        mediator.setup();
        mediator.setRent_GUI(this);
        mediator.setDbMgr_activity(this);

        confirm.setOnClickListener(show_size_gui);
    }
    private View.OnClickListener show_size_gui = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(rbs1.isChecked() && rbp1.isChecked()){
                Rent("Big",rbp1.getText().toString());
                choice = rbp1.getText().toString();
            }else if(rbs1.isChecked() && rbp2.isChecked()){
                Rent("Big",rbp2.getText().toString());
                choice = rbp2.getText().toString();
            }else if(rbs1.isChecked() && rbp3.isChecked()){
                Rent("Big",rbp3.getText().toString());
                choice = rbp3.getText().toString();
            }else if(rbs2.isChecked() && rbp1.isChecked()){
                Rent("Middle",rbp1.getText().toString());
                choice = rbp1.getText().toString();
            }else if(rbs2.isChecked() && rbp2.isChecked()){
                Rent("Middle",rbp2.getText().toString());
                choice = rbp2.getText().toString();
            }else if(rbs2.isChecked() && rbp3.isChecked()){
                Rent("Middle",rbp3.getText().toString());
                choice = rbp3.getText().toString();
            }else if(rbs3.isChecked() && rbp1.isChecked()){
                Rent("Small",rbp1.getText().toString());
                choice = rbp1.getText().toString();
            }else if(rbs3.isChecked() && rbp2.isChecked()){
                Rent("Small",rbp2.getText().toString());
                choice = rbp2.getText().toString();
            }else if(rbs3.isChecked() && rbp3.isChecked()){
                Rent("Small",rbp3.getText().toString());
                choice = rbp3.getText().toString();
            }else{
                mediator.make_toast("您尚未選取完整選項");
            }

        }
    };

    public void setSize_id(ArrayList a){
        this.size_id = a;
    }
    public void setSize(ArrayList a){
        this.size = a;
    }
    public void setPlace(ArrayList a){
        this.place = a;
    }
    public void setRent_or_not(ArrayList a){ this.rent_or_not = a; }

    public void Rent(String size , String place){
        try {
            mediator.load_refrigerator(size,place);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void B_Rent_Or_Not(ArrayList a , ArrayList b){
        for(int i = 0 ; i < a.size() ; i++){
            if(a.get(i).equals("0")){
                mediator.show_big_GUI(choice ,(String) b.get(i));
                break;
            }else if(i == a.size() -1){
                mediator.make_toast("您選擇的冰箱類型已被租用完畢");
            }
        }

    }
    public void M_Rent_Or_Not(ArrayList a , ArrayList b){
        for(int i = 0 ; i < a.size() ; i++){
            if(a.get(i).equals("0")){
                mediator.show_middle_GUI(choice ,(String) b.get(i));
                break;
            }else if(i == a.size() -1){
                mediator.make_toast("您選擇的冰箱類型已被租用完畢");
            }
        }

    }
    public void S_Rent_Or_Not(ArrayList a , ArrayList b){
        for(int i = 0 ; i < a.size() ; i++){
            if(a.get(i).equals("0")){
                mediator.show_small_GUI(choice ,(String) b.get(i));
                break;
            }else if(i == a.size() -1){
                mediator.make_toast("您選擇的冰箱類型已被租用完畢");
            }
        }

    }

}
