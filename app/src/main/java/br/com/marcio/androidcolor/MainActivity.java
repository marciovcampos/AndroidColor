package br.com.marcio.androidcolor;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
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
      TextView labelCorAndroidRobot = (TextView) findViewById(R.id.labelCorAndroidRobot);

      //gera cor Aletoria
      String corHexa = gerarCorHexaDecimal();

      imageView.setColorFilter(Color.parseColor(corHexa));
      labelCorAndroidRobot.setText(corHexa.toUpperCase());

    }

    public String gerarCorHexaDecimal(){

        Random random = new Random();
        int nextInt = random.nextInt(256*256*256);
        String colorString = String.format("#%06x", nextInt);
        return colorString;
    }







}
