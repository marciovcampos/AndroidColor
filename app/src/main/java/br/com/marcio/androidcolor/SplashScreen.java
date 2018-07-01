package br.com.marcio.androidcolor;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.DynamicLayout;
import android.text.Layout;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SplashScreen extends AppCompatActivity {

    int x = 1;
    int coluna = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hideSystemUI();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        try {
            criarQuadradosColoridos();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarMainActivity();
            }
        }, 3500);

    }

    private void mostrarMainActivity() {
        Intent intent = new Intent(
                SplashScreen.this, MainActivity.class
        );
        startActivity(intent);
        finish();
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_IMMERSIVE
        // Set the content to appear under the system bars so that the
        // content doesn't resize when the system bars hide and show.
        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        // Hide the nav bar and status bar
        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


    private void criarQuadradosColoridos() throws InterruptedException {

        final LinearLayout linear1 = (LinearLayout) findViewById(R.id.coluna1);
        final LinearLayout linear2 = (LinearLayout) findViewById(R.id.coluna2);
        final LinearLayout linear3 = (LinearLayout) findViewById(R.id.coluna3);
        final LinearLayout linear4 = (LinearLayout) findViewById(R.id.coluna4);

        int laguraDaTela = getLarguraDaTela();

        for(x = 1; x < 4; x++){
            for (int i = 1; i <= 30; i++) {
                final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        (laguraDaTela / 4),
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );


                final Button btn = new Button(this);
                final int id_ = btn.getId();

                btn.setWidth(1);

                //gera cor random
                Random rand = new Random();
                btn.setBackgroundColor(geraCorHexa(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));

                //coloca atraso de 100millisegundos para exibir os botões
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        int coluna = proximaColuna();

                        if(coluna == 1){
                            linear1.addView(btn, params);
                        }else if(coluna == 2){
                            linear2.addView(btn, params);
                        }else if(coluna == 3){
                            linear3.addView(btn, params);
                        }
                        else if(coluna == 4){
                            linear4.addView(btn, params);
                        }

                    }
                }, 120 * i );

            }
        }

    }

    private int getLarguraDaTela(){

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        try {
            display.getRealSize(size);
        } catch (NoSuchMethodError err) {
            display.getSize(size);
        }
        int width = size.x;
        int height = size.y;

        return width;


    }

    private int escolheColunaRandom(){

        int min =1, max = 5;
        int randomNum = min + (int)(Math.random() * (max - min));

        return randomNum;
    }

    private int proximaColuna(){

        coluna++;
        if(coluna == 5){
            coluna = 1;
        }
        return coluna;

    }

    public static int geraCorHexa(float red, float green, float blue) {
        return 0xff000000 |
                ((int) (red   * 255.0f + 0.5f) << 16) |
                ((int) (green * 255.0f + 0.5f) <<  8) |
                (int) (blue  * 255.0f + 0.5f);
    }






}
