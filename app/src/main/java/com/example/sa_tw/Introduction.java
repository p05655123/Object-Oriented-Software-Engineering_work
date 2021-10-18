package com.example.sa_tw;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Introduction extends AppCompatActivity {
    TextView t1;
    TextView t2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        t2 = (TextView)findViewById(R.id.text2);
        t2.setTextSize(18);
        t2.setText("買了需要冷藏的食物，在宿舍沒有冰箱要當天吃完不太方便？\n" +
                "短期住宿，沒辦法自己買一台冰箱來用？\n" +
                "我們想到了解決方法——共享冰箱，使用者可以用低額的價格向我們租借冰箱。\n" +
                "我們預計將冰箱設置在社區及宿舍，讓家中沒有冰箱的使用者可直接向我們租借，省去購買冰箱及後續維修問題的煩惱。\n" +
                "冰箱分為大中小三類，其中又把冰箱分成格子狀，供使用者租借，不限制使用者租借格數限制，我們會依據冰箱大中小分類來定義租借費用。\n" +
                "租借一格使用期限為30日，在期限內使用者隨時都可以使用冰箱，期限到期後如果沒有繼續付費，將會自動退還冰箱，開放給下一位使用者租借。\n" +
                "使用者可透過APP查詢可供使用的冰箱，選擇想要租借的冰箱進行付費，透過APP輸入所冰物品的資訊及保存期限，系統將會寄送一次性密碼至信箱，使用者於APP上輸入密碼解鎖冰箱即可存放物品，物品到期前兩天與當天會通知使用者物品即將到期。");
    }
}
