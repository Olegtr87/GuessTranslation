package by.oleg.vasilevskiy.guesstranslation;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ChangeLangActivity extends AppCompatActivity {
    ImageView image;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // скрываем заголовок
        getSupportActionBar().hide();
        // портретный режим
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_change_lang);
        // создание баннера
        AdView mAdView = (AdView) findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //анимация
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_cactus);
        image = (ImageView)findViewById(R.id.imageView);
        image.startAnimation(animation);
    }

    public void onClickAboutChangeLang(View view) {
        switch (view.getId()) {
            case R.id.button3:
                ChangeTypeGameActivity.langTypeGame = Language.esp;
                break;
            case R.id.button5:
                ChangeTypeGameActivity.langTypeGame = Language.port;
                break;
            case R.id.button7:
                ChangeTypeGameActivity.langTypeGame = Language.eng;
                break;
            case R.id.button4:
                ChangeTypeGameActivity.langTypeGame = Language.chezh;
                break;
            case R.id.button6:
                ChangeTypeGameActivity.langTypeGame = Language.de;
                break;
        }
        //звук выбора
        if (AboutActivity.sound==true) {
        MediaPlayer player;
        player = MediaPlayer.create(this, R.raw.press);
        player.start();}
        // создание активности
        Intent intent = new Intent(ChangeLangActivity.this, ChangeTypeGameActivity.class);
        startActivity(intent);
        finish();

    }
}
