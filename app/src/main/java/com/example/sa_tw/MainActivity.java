package com.example.sa_tw;

import com.example.sa_tw.Mediator.Mediator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Mediator mediator = Mediator.getInstance();
    EditText acc ;
    EditText pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediator.setup();
        mediator.setMainActivity(this);

        Button login_btn = (Button)findViewById(R.id.Login);
        Button signup_btn = (Button)findViewById(R.id.Signup);
        login_btn.setOnClickListener(login);
        signup_btn.setOnClickListener(show_signup_gui);
        acc=(EditText)findViewById(R.id.editAccount);
        pwd=(EditText)findViewById(R.id.editPassword);
        mediator.setDbMgr_activity(this);
    }

    private View.OnClickListener login = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mediator.login(acc.getText().toString(),pwd.getText().toString());
            pwd.setText("");
        }
    };

    private View.OnClickListener show_signup_gui = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mediator.show_signup_GUI();
        }
    };
}
