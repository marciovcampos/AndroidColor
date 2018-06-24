package br.com.marcio.androidcolor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String PREFERENCES = "UltimaCor";
    private EditText edtTexto;
    private static final String nomeArquivo = "CoresFavoritas.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //adiciona menu a nossa activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //definindo a ação do botão no menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.mnCoresFavoristas:
                Intent intent = new Intent(
                        MainActivity.this, CoresFavoritasActivity.class
                );
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }

    public void mudarCor(View view, String cor){

        ImageView imageViewRobot = (ImageView) findViewById(R.id.androidRobot);
        TextView labelCorAndroidRobo = (TextView) findViewById(R.id.labelCorAndroidRobot);
        String corHexa = "";

        //se cor for vazia, gera cor Aletoria
        corHexa = cor;

        imageViewRobot.setColorFilter(Color.parseColor(corHexa));
        labelCorAndroidRobo.setText(corHexa.toUpperCase());

    }


    // mudar cor aletoria
    public void mudarCorRandom(View view){

      ImageView imageViewRobot = (ImageView) findViewById(R.id.androidRobot);
      TextView labelCorAndroidRobo = (TextView) findViewById(R.id.labelCorAndroidRobot);

      String corHexa = gerarCorHexaDecimal();

      imageViewRobot.setColorFilter(Color.parseColor(corHexa));
      labelCorAndroidRobo.setText(corHexa.toUpperCase());

      //Configura o arquivo para salvar os dados
      SharedPreferences shared = getSharedPreferences(PREFERENCES, 0);
      SharedPreferences.Editor editor = shared.edit();

      //Salva os dados no arquivo
        editor.putString("ultimaCor", corHexa);
        editor.commit();

    }

    public String gerarCorHexaDecimal(){

        Random random = new Random();
        int nextInt = random.nextInt(256*256*256);
        String colorString = String.format("#%06x", nextInt);
        return colorString;
    }

    public void salvarCorFavorita(View view){

        TextView corAtualDoRobo = (TextView) findViewById(R.id.labelCorAndroidRobot);

        String corFavorita = corAtualDoRobo.getText().toString();

        ArquivoTexto arquivoTexto = new ArquivoTexto();

        arquivoTexto.salvarArquivo(getApplicationContext(), corFavorita);

        mudarCorRandom(findViewById(android.R.id.content));

    }


    @Override
    protected void onResume() {
        super.onResume();

        //Configura o arquivo de configuração
        SharedPreferences shared = getSharedPreferences(PREFERENCES, 0);

        if(shared.contains("ultimaCor")){
            String ultimaCor = shared.getString("ultimaCor", "");
            mudarCor(findViewById(android.R.id.content), ultimaCor);
        }else{
            mudarCorRandom(findViewById(android.R.id.content));
        }

    }





    public void printToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }



}
