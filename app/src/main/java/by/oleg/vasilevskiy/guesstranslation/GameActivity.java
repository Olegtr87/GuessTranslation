package by.oleg.vasilevskiy.guesstranslation;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    MediaPlayer player;
    public static String typeGame;
    public static String langTypeGame;
    TextView text;
    Button btn1;
    Button btn2;
    Button btn3;
    String textInTextView;
    public String file;
    Map<String, String> map;
    public String btnText1;
    public String btnText2;
    public String btnText3;
    public String valueConfirm;
    TextView textCountOk;
    TextView textCountNotOk;
    int counterYes;
    int counterNo;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // скрываем заголовок
        getSupportActionBar().hide();
        // портретный режим
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_game);
        setGame(typeGame, langTypeGame);
        // установка начальных значений;
        setBeginersParametrs();
        // создание баннера
        AdView mAdView = (AdView) findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

// метод для нач значений
    void  setBeginersParametrs(){
        switch (langTypeGame){
            case Language.esp: beginnersCounters(ESP_FILE_NAME_YES,ESP_FILE_NAME_NO);break;
            case Language.eng: beginnersCounters(ENG_FILE_NAME_YES,ENG_FILE_NAME_NO);break;
            case Language.port: beginnersCounters(PORT_FILE_NAME_YES,PORT_FILE_NAME_NO);break;
            case Language.de: beginnersCounters(DE_FILE_NAME_YES,DE_FILE_NAME_NO);break;
            case Language.chezh: beginnersCounters(CH_FILE_NAME_YES,CH_FILE_NAME_NO);break;
        }
    }

// начальные значения счетчиков
    void beginnersCounters(String fileNameYes,String fileNameNo){
        textCountNotOk=(TextView)findViewById(R.id.textView3);
        textCountOk=(TextView)findViewById(R.id.textView2);
        // если переводится в целое значение значит файл существует
        try{
        int i= Integer.valueOf(readFile(fileNameYes));
        textCountOk.setText(String.valueOf(readFile(fileNameYes)));
        }
        // вызываем ошибку если файла нет и устанавливаем значение на 0
        catch(Exception e){
            e.printStackTrace();
            writeFile(fileNameYes, "0");
            textCountOk.setText(String.valueOf(readFile(fileNameYes)));
        }
        // если переводится в целое значение значит файл существует
        try{
            int i1= Integer.valueOf(readFile(fileNameNo));
            textCountNotOk.setText(String.valueOf(readFile(fileNameNo)));
        }
        // вызываем ошибку если файла нет и устанавливаем значение на 0
        catch(Exception e){
            e.printStackTrace();
            writeFile(fileNameNo, "0");
            textCountNotOk.setText(String.valueOf(readFile(fileNameNo)));
        }
    }

    //запись в файл
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

    // чтение файла
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

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        openQuitDialog();
    }
    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                GameActivity.this);
        quitDialog.setTitle("Выход: Вы уверены?");

        quitDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });
        quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        quitDialog.show();
    }

    // Выбор игры
    public void setGame(String typeGame, String langTypeGame) {
        text = (TextView) findViewById(R.id.textView);

        if (typeGame.equals("1"))
            switch (langTypeGame) {
                case Language.eng:
                    file="eng.dat";
                    type1();
                    break;
                case Language.esp:
                    file="esp.dat";
                    type1();
                    break;
                case Language.de:
                    file="de.dat";
                    type1();
                    break;
                case Language.port:
                    file="port.dat";
                    type1();
                    break;
                case Language.chezh:
                    file="czech.dat";
                    type1();
                    break;
            }
        if (typeGame.equals("2"))
            switch (langTypeGame) {
                case Language.eng:
                    file="eng.dat";
                    type2();
                    break;
                case Language.esp:
                    file="esp.dat";
                    type2();
                    break;
                case Language.de:
                    file="de.dat";
                    type2();
                    break;
                case Language.port:
                    file="port.dat";
                    type2();
                    break;
                case Language.chezh:
                    file="czech.dat";
                    type2();
                    break;
            }
        if (typeGame.equals("3"))
            switch (langTypeGame) {
                case Language.eng:
                    file="eng.dat";
                    getMetodType1OrType2();
                    break;
                case Language.esp:
                    file="esp.dat";
                    getMetodType1OrType2();
                    break;
                case Language.de:
                    file="de.dat";
                    getMetodType1OrType2();
                    break;
                case Language.port:
                    file="port.dat";
                    getMetodType1OrType2();
                    break;
                case Language.chezh:
                    file="czech.dat";
                    getMetodType1OrType2();
                    break;
            }

    }

    // Вывод на экран для типа игры 2
    public void type2() {
        getElementsType2();
        text = (TextView) findViewById(R.id.textView);
        text.setText(textInTextView);
        btn1 = (Button) findViewById(R.id.button8);
        btn1.setText(btnText1);
        btn2 = (Button) findViewById(R.id.button9);
        btn2.setText(btnText2);
        btn3 = (Button) findViewById(R.id.button10);
        btn3.setText(btnText3);
    }

    // Вывод на экран для типа игры 1
    public void type1() {
        getElementsType1();
        text = (TextView) findViewById(R.id.textView);
        text.setText(textInTextView);
        btn1 = (Button) findViewById(R.id.button8);
        btn1.setText(btnText1);
        btn2 = (Button) findViewById(R.id.button9);
        btn2.setText(btnText2);
        btn3 = (Button) findViewById(R.id.button10);
        btn3.setText(btnText3);
    }

    // Установка кнопок некликабельными
    public void isNotClickable(){
        btn1.setClickable(false);
        btn2.setClickable(false);
        btn3.setClickable(false);
    }

    // Установка кнопок кликабельными
    public void isClickable(){
        btn1.setClickable(true);
        btn2.setClickable(true);
        btn3.setClickable(true);
    }

    // Установить фон кнопки зеленым цветом (правильный ответ)
    public void greenFireBtn(final Button btn){
        final Drawable btnGreen=btn.getBackground();
        isNotClickable();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn.setBackgroundResource(R.drawable.led_green);

                }
            }, 500);
        new Handler().postDelayed(new Runnable() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                //btn.setBackgroundResource(R.drawable.but);
                btn.setBackground(btnGreen);
                isClickable();
                changeTypeGame();
            }
        }, 4000);
    }

    // Метод проверяет, какой кнопке соответсвует правильный ответ
    public void whatButtonIsTrue(){
        if (valueConfirm.equals(btn1.getText())) greenFireBtn(btn1);
        if (valueConfirm.equals(btn2.getText())) greenFireBtn(btn2);
        if (valueConfirm.equals(btn3.getText())) greenFireBtn(btn3);
    }

    // Проигрывание звука для правильного отета
    public void startSoundGreen(){
        if (AboutActivity.sound==true) {
        player = MediaPlayer.create(this, R.raw.yes);
        player.start();}
    }

    // Прогрывание звука для неправильного ответа
    public void startSoundRed(){
        if (AboutActivity.sound==true) {
        player = MediaPlayer.create(this, R.raw.no);
        player.start();}
    }

    // Установить фон кнопки красным цветом (неправильный ответ)
    public void redFireBtn(final Button btn){
        final Drawable btnRed=btn.getBackground();
        isNotClickable();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn.setBackgroundResource(R.drawable.led_red);
                }
            }, 500);
        new Handler().postDelayed(new Runnable() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                //btn.setBackgroundResource(R.drawable.but);
                //btn.setBackgroundColor(Color.LTGRAY);
                btn.setBackground(btnRed);
                isClickable();
                changeTypeGame();
            }
        }, 4000);
    }

    // Какой тип игры выбрать при нажатии кнопки
    public void changeTypeGame(){
        switch (typeGame){
            case "1":type1();break;
            case "2":type2();break;
            case "3":getMetodType1OrType2();break;
        }
    }


    // выбираем файл для счетчика YES
    void fileForCounterYes(){
        switch (langTypeGame){
            case Language.esp: counterYesPlus(ESP_FILE_NAME_YES);break;
            case Language.eng: counterYesPlus(ENG_FILE_NAME_YES);break;
            case Language.port: counterYesPlus(PORT_FILE_NAME_YES);break;
            case Language.de: counterYesPlus(DE_FILE_NAME_YES);break;
            case Language.chezh: counterYesPlus(CH_FILE_NAME_YES);break;
        }
    }
    // выбираем файл для счетчика NO
    void fileForCounterNo(){
        switch (langTypeGame){
            case Language.esp: counterNoPlus(ESP_FILE_NAME_NO);break;
            case Language.eng: counterNoPlus(ENG_FILE_NAME_NO);break;
            case Language.port: counterNoPlus(PORT_FILE_NAME_NO);break;
            case Language.de: counterNoPlus(DE_FILE_NAME_NO);break;
            case Language.chezh: counterNoPlus(CH_FILE_NAME_NO);break;
        }
    }
    // добавляем к счетчику +1
    void counterYesPlus(String fileName){
        counterYes=Integer.valueOf(readFile(fileName));
        counterYes++;
        writeFile(fileName,String.valueOf(counterYes));
        textCountOk.setText(String.valueOf(counterYes));
    }
    // добавляем к счетчику +1 for no
    void counterNoPlus(String fileName){
        counterNo=Integer.valueOf(readFile(fileName));
        counterNo++;
        writeFile(fileName,String.valueOf(counterNo));
        textCountNotOk.setText(String.valueOf(counterNo));
    }
    // Действие при выборе варианта ответа
    public void pressBtn(View view){
        switch (view.getId()) {
            case R.id.button8:
                if (valueConfirm.equals(btn1.getText())) {startSoundGreen();greenFireBtn(btn1);fileForCounterYes();} else {
                    startSoundRed();
                    whatButtonIsTrue();
                    redFireBtn(btn1);
                    fileForCounterNo();}
                break;
            case R.id.button9:
                if (valueConfirm.equals(btn2.getText())) {startSoundGreen();greenFireBtn(btn2);fileForCounterYes();} else {
                    startSoundRed();
                    whatButtonIsTrue();
                    redFireBtn(btn2);
                    fileForCounterNo();}
                break;
            case R.id.button10:
                if (valueConfirm.equals(btn3.getText())) {startSoundGreen();
                    greenFireBtn(btn3);fileForCounterYes();} else {
                    startSoundRed();
                    whatButtonIsTrue();
                    redFireBtn(btn3);
                    fileForCounterNo();}
                break;
        }
    }

    // Вариант игры 3 (тип 1 или тип 2) рандом
    public void getMetodType1OrType2(){
        int i=1 + (int)(Math.random() * ((2 - 1) + 1));
        if (i==1) type1();
        if (i==2) type2();
    }
    // Получаем элемент для типа игры 1
    public void getElementsType1() {
        String[] arr = langToRus();

        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 6; i = i + 2) {
            list.add(arr[i]);
        }
        valueConfirm = arr[0];
        textInTextView = arr[1];

        Random r = new Random();
        int ind = r.nextInt(list.size());
        btnText1 = list.get(ind);
        list.remove(ind);

        ind = r.nextInt(list.size());
        btnText2 = list.get(ind);
        list.remove(ind);

        ind = r.nextInt(list.size());
        btnText3 = list.get(ind);
        list.remove(ind);
    }

    // Получаем элемент для типа игры 2
    public void getElementsType2() {
        String[] arr = langToRus();

        ArrayList<String> list = new ArrayList<String>();
        for (int i = 1; i < 6; i = i + 2) {
            list.add(arr[i]);
        }

        valueConfirm = arr[1];
        textInTextView = arr[0];

        Random r = new Random();
        int ind = r.nextInt(list.size());
        btnText1 = list.get(ind);
        list.remove(ind);

        ind = r.nextInt(list.size());
        btnText2 = list.get(ind);
        list.remove(ind);

        ind = r.nextInt(list.size());
        btnText3 = list.get(ind);
        list.remove(ind);
    }

    // Метод возвращает массив из шести рандомных элементов из HashMap
    public String[] langToRus() {

        addElementsInMap();

        Object[] obj = map.keySet().toArray();

        Random r = new Random();
        int random = r.nextInt(map.size());
        String key = String.valueOf(obj[random]);
        String value = String.valueOf(map.get(key));
        String[] arr = {key, value, "", "", "", ""};
        map.remove(key);

        obj = map.keySet().toArray();

        Random r1 = new Random();
        int random1 = r1.nextInt(map.size() + 1);
        String key1 = String.valueOf(obj[random1]);
        String value1 = String.valueOf(map.get(key1));
        arr[2] = key1;
        arr[3] = value1;
        map.remove(key1);

        obj = map.keySet().toArray();

        Random r2 = new Random();
        int random2 = r2.nextInt(map.size() + 1);
        String key2 = String.valueOf(obj[random2]);
        String value2 = String.valueOf(map.get(key2));
        arr[4] = key2;
        arr[5] = value2;
        map.remove(key2);

        return arr;
    }

    // Метод втавляет строки из файла в HashMap
    public void addElementsInMap() {
        InputStream is;
        map = new HashMap<String, String>();
        try {
            is = getAssets().open(file);
            int b;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((b = is.read()) != -1) {
                baos.write(b);
            }
            String stroka=baos.toString("Cp1251");
            String s="";
            String[] array=stroka.split("\n");
            String[] array1=null;
            for (int i=0;i<array.length;i++){
                s=array[i].substring(0, array[i].length()-1);
                array1=s.split("_");
                map.put(array1[0], array1[1]);}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
