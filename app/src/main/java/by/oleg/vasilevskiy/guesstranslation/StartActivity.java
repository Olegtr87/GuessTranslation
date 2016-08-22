package by.oleg.vasilevskiy.guesstranslation;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class StartActivity extends AppCompatActivity {
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {        
        super.onCreate(savedInstanceState);
        // скрываем заголовок
        getSupportActionBar().hide();
        // портретный режим
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_start);
        // создание баннера
        AdView mAdView = (AdView) findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    public void startSoundPress(){
        if (AboutActivity.sound==true) {
            MediaPlayer player;
            player = MediaPlayer.create(this, R.raw.press);
            player.start();}
    }
    public void onClickAboutActivity(View view) {
        startSoundPress();
        Intent intent = new Intent(StartActivity.this, AboutActivity.class);
        startActivity(intent);
    }
    public void onClickStatisticActivity(View view) {
        startSoundPress();
        Intent intent = new Intent(StartActivity.this, StatisticActivity.class);
        startActivity(intent);
    }
    public void onClickChangeLangActivity(View view) {
        startSoundPress();
        Intent intent = new Intent(StartActivity.this, ChangeLangActivity.class);
        startActivity(intent);

    }
}
