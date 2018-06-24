package br.com.marcio.androidcolor;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ArquivoTexto {

    private EditText edtTexto;
    private static final String nomeArquivo = "CoresFavoritas.txt";


    //PROCEDIMENTO PARA SALVAR OS DADOS NO ARQUIVO
    public void salvarArquivo(Context ctx, String texto){

        if(texto == null || texto.equals("")){
            printToast(ctx, "Erro ao salvar cor favorita!");
        }else{

            try{

                String textoAntigo = getConteudoArquivoTexto(ctx);

                FileOutputStream fileOutputStream = ctx.openFileOutput(nomeArquivo, Context.MODE_PRIVATE);
                OutputStreamWriter output = new OutputStreamWriter(fileOutputStream);

                output.write(textoAntigo+texto+"\n");
                output.close();

                printToast(ctx, "Cor "+texto+" adicionada aos favoritos!");

                Log.i("MainActivity", "Texto salvo!");

            }catch (IOException e){
                Log.e("MainActivity",e.toString());
            }
        }
    }

    // PROCEDIMENTO PARA LER OS DADOS DO ARQUIVO
    public void lerArquivo(Context ctx, TextView textView){

        String texto = getConteudoArquivoTexto(ctx);
        textView.setText(texto);

    }


    public String getConteudoArquivoTexto(Context ctx){

        try{

            FileInputStream fileInputStream = ctx.openFileInput(nomeArquivo);
            InputStreamReader input = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(input);

            String line = "", texto = "";

            while( (line = bufferedReader.readLine()) != null ){
                texto += line+"\n";
            }

            Log.i("CoresFavoritasActivity", "Cores Favoritas lidas com sucesso!");

            return texto;


        }catch (IOException e){
            Log.e("CoresFavoritasActivity", e.toString());

            return "";
        }

    }



    public void printToast(Context ctx, String msg){
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }



}
