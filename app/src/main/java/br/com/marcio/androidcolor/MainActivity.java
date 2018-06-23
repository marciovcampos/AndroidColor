package br.com.marcio.androidcolor;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // mudar cor aletoria
    public void mudarCorRandom(View view){

      ImageView imageView = (ImageView) findViewById(R.id.androidRobot);

      int colorRandom = gerarCorAutomatica();

//        imageView.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimaryDark), android.graphics.PorterDuff.Mode.MULTIPLY);
        imageView.setColorFilter(colorRandom);

    }

    public int gerarCorAutomatica(){

        Random random = new Random();
        int nextInt = random.nextInt(256*256*256);
        String colorString = String.format("#%06x", nextInt);

        int color = Color.parseColor(colorString); //The color u want

        return color;
    }


}
