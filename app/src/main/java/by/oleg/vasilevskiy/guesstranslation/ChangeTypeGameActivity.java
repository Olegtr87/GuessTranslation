package by.oleg.vasilevskiy.guesstranslation;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ChangeTypeGameActivity extends AppCompatActivity {
    static String langTypeGame;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn10;
    Button textCloud;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // скрываем заголовок
        getSupportActionBar().hide();
        // портретный режим
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_change_type_game);
        textBtn();
        // создание баннера
        AdView mAdView = (AdView) findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //анимация
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_cloud);
        textCloud = (Button)findViewById(R.id.button23);
        textCloud.startAnimation(animation);

    }

    public void setlangTypeGame(){
        GameActivity.langTypeGame=langTypeGame;
    }

    public void textBtn(){
        btn1= (Button) findViewById(R.id.button14);
        btn2= (Button) findViewById(R.id.button15);
        btn3= (Button) findViewById(R.id.button16);
        btn4= (Button) findViewById(R.id.button17);
        btn5= (Button) findViewById(R.id.button18);
        btn6= (Button) findViewById(R.id.button19);
        btn7= (Button) findViewById(R.id.button20);
        btn8= (Button) findViewById(R.id.button21);
        btn9= (Button) findViewById(R.id.button22);
        btn10=(Button) findViewById(R.id.button23);

        switch (langTypeGame) {
            case Language.eng:
                  btn1.setBackgroundResource(R.drawable.rus);
                  btn2.setBackgroundResource(R.drawable.arrow1);
                  btn3.setBackgroundResource(R.drawable.eng);
                btn4.setBackgroundResource(R.drawable.eng);
                btn5.setBackgroundResource(R.drawable.arrow1);
                btn6.setBackgroundResource(R.drawable.rus);
                btn7.setBackgroundResource(R.drawable.rus);
                btn8.setBackgroundResource(R.drawable.quest);
                btn9.setBackgroundResource(R.drawable.eng);
                btn10.setText("Язык английский.\nВыбери режим игры\nи нажми play!");
                break;
            case Language.port:
                btn1.setBackgroundResource(R.drawable.rus);
                btn2.setBackgroundResource(R.drawable.arrow1);
                btn3.setBackgroundResource(R.drawable.port);
                btn4.setBackgroundResource(R.drawable.port);
                btn5.setBackgroundResource(R.drawable.arrow1);
                btn6.setBackgroundResource(R.drawable.rus);
                btn7.setBackgroundResource(R.drawable.rus);
                btn8.setBackgroundResource(R.drawable.quest);
                btn9.setBackgroundResource(R.drawable.port);
                btn10.setText("Язык португальский.\nВыбери режим игры\n" +
                        "и нажми play!");
                break;
            case Language.de:
                btn1.setBackgroundResource(R.drawable.rus);
                btn2.setBackgroundResource(R.drawable.arrow1);
                btn3.setBackgroundResource(R.drawable.de);
                btn4.setBackgroundResource(R.drawable.de);
                btn5.setBackgroundResource(R.drawable.arrow1);
                btn6.setBackgroundResource(R.drawable.rus);
                btn7.setBackgroundResource(R.drawable.rus);
                btn8.setBackgroundResource(R.drawable.quest);
                btn9.setBackgroundResource(R.drawable.de);
                btn10.setText("Язык немецкий.\nВыбери режим игры\n" +
                        "и нажми play!");
                break;
            case Language.chezh:
                btn1.setBackgroundResource(R.drawable.rus);
                btn2.setBackgroundResource(R.drawable.arrow1);
                btn3.setBackgroundResource(R.drawable.ch);
                btn4.setBackgroundResource(R.drawable.ch);
                btn5.setBackgroundResource(R.drawable.arrow1);
                btn6.setBackgroundResource(R.drawable.rus);
                btn7.setBackgroundResource(R.drawable.rus);
                btn8.setBackgroundResource(R.drawable.quest);
                btn9.setBackgroundResource(R.drawable.ch);
                btn10.setText("Язык чешский.\nВыбери режим игры\n" +
                        "и нажми play!");
                break;
            case Language.esp:
                btn1.setBackgroundResource(R.drawable.rus);
                btn2.setBackgroundResource(R.drawable.arrow1);
                btn3.setBackgroundResource(R.drawable.esp);
                btn4.setBackgroundResource(R.drawable.esp);
                btn5.setBackgroundResource(R.drawable.arrow1);
                btn6.setBackgroundResource(R.drawable.rus);
                btn7.setBackgroundResource(R.drawable.rus);
                btn8.setBackgroundResource(R.drawable.quest);
                btn9.setBackgroundResource(R.drawable.esp);
                btn10.setText("Язык эсперанто.\nВыбери режим игры\n" +
                        "и нажми play!");
                break;
        }
    }

    public void onClickAboutChangeType(View view) {
        switch (view.getId()) {
            case R.id.button11:
                GameActivity.typeGame = "1";
                break;
            case R.id.button12:
                GameActivity.typeGame = "2";
                break;
            case R.id.button13:
                GameActivity.typeGame = "3";
                break;
        }
        setlangTypeGame();
        //звук выбора
        if (AboutActivity.sound==true) {
            MediaPlayer player;
            player = MediaPlayer.create(this, R.raw.press);
            player.start();}
        //создание новой активности
        Intent intent = new Intent(ChangeTypeGameActivity.this, LoadingActivity.class);
        startActivity(intent);
        finish();
    }
}