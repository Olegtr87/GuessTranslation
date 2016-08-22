package by.oleg.vasilevskiy.guesstranslation;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StatisticActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // скрываем заголовок
        getSupportActionBar().hide();
        // портретный режим
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_statistic);

        // создание баннера
        AdView mAdView = (AdView) findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        init();
        setValueInTable();
    }
    // инициализация приложения
    void init(){
        text1=(TextView)findViewById(R.id.textView13);
        text2=(TextView)findViewById(R.id.textView14);
        text3=(TextView)findViewById(R.id.textView15);
        text4=(TextView)findViewById(R.id.textView16);
        text5=(TextView)findViewById(R.id.textView17);
        text6=(TextView)findViewById(R.id.textView20);
        text7=(TextView)findViewById(R.id.textView21);
        text8=(TextView)findViewById(R.id.textView22);
        text9=(TextView)findViewById(R.id.textView23);
        text10=(TextView)findViewById(R.id.textView24);
    }

    //установить значения ячеек при загрузке
    void setValueInTable(){
        text1.setText(String.valueOf(readFile(ENG_FILE_NAME_YES)));
        text6.setText(String.valueOf(readFile(ENG_FILE_NAME_NO)));
        text2.setText(String.valueOf(readFile(ESP_FILE_NAME_YES)));
        text7.setText(String.valueOf(readFile(ESP_FILE_NAME_NO)));
        text3.setText(String.valueOf(readFile(CH_FILE_NAME_YES)));
        text8.setText(String.valueOf(readFile(CH_FILE_NAME_NO)));
        text4.setText(String.valueOf(readFile(DE_FILE_NAME_YES)));
        text9.setText(String.valueOf(readFile(DE_FILE_NAME_NO)));
        text5.setText(String.valueOf(readFile(PORT_FILE_NAME_YES)));
        text10.setText(String.valueOf(readFile(PORT_FILE_NAME_NO)));
    }

    String readFile(String file){
        FileInputStream fis = null;
        String str="";
        try {
            fis = openFileInput(file);
            int content;
            while ((content = fis.read()) != -1) {
                str=str+String.valueOf((char) content);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return  str;
    }

}
