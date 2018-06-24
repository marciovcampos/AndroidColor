package br.com.marcio.androidcolor;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

public class CoresFavoritasActivity extends AppCompatActivity {

    ArquivoTexto arquivoTexto = new ArquivoTexto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cores_favoritas);

        //adiciona botão voltar na actionBar
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);


        TextView txtCoresFavoritas =  (TextView) findViewById(R.id.txtCoresFavoritas);

        //metodo para deixar o textview com scroll
        txtCoresFavoritas.setMovementMethod(new ScrollingMovementMethod());

        arquivoTexto.lerArquivo(getApplicationContext(), txtCoresFavoritas);

    }


    //função que impleta o clique no botão voltar da actionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Id correspondente ao botão Up/Home da actionbar
            case android.R.id.home:
                Intent intent = new Intent(
                        CoresFavoritasActivity.this, MainActivity.class
                );
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
