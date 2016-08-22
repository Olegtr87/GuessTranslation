package by.oleg.vasilevskiy.guesstranslation;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AboutActivity extends AppCompatActivity {
    final String ENG_FILE_NAME_YES="eng_file_counter_yes";
    final String ENG_FILE_NAME_NO="eng_file_counter_no";
    final String ESP_FILE_NAME_YES="esp_file_counter_yes";
    final String ESP_FILE_NAME_NO="esp_file_counter_no";
    final String PORT_FILE_NAME_YES="port_file_counter_yes";
    final String PORT_FILE_NAME_NO="port_file_counter_no";
    final String CH_FILE_NAME_YES="ch_file_counter_yes";
    final String CH_FILE_NAME_NO="ch_file_counter_no";
    final String DE_FILE_NAME_YES="de_file_counter_yes";
    final String DE_FILE_NAME_NO="de_file_counter_no";
    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;
    TextView text7;
    TextView text8;
    TextView text9;
    TextView text10;
    static boolean sound=true;
    Switch switcher;
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // скрываем заголовок
        getSupportActionBar().hide();
        // портретный режим
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_about);

        //положние кнопки вкл
        switcher=(Switch)findViewById(R.id.switch1);
        if (sound==true) switcher.setChecked(true); else switcher.setChecked(false);
        // создание баннера
        AdView mAdView = (AdView) findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
    //установка выключатля в режим он/офф
    public void setSound(View view){
        if (switcher.isChecked()) sound=true; else sound=false;
    }

    public void setNull(View view){
        writeFile(ENG_FILE_NAME_YES,"0");
        writeFile(ESP_FILE_NAME_YES,"0");
        writeFile(PORT_FILE_NAME_YES,"0");
        writeFile(DE_FILE_NAME_YES,"0");
        writeFile(CH_FILE_NAME_YES,"0");
        writeFile(ENG_FILE_NAME_NO,"0");
        writeFile(ESP_FILE_NAME_NO,"0");
        writeFile(PORT_FILE_NAME_NO,"0");
        writeFile(DE_FILE_NAME_NO,"0");
        writeFile(CH_FILE_NAME_NO,"0");
    }

    void writeFile(String file,String string){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(file, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
