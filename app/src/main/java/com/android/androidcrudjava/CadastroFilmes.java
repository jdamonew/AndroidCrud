package com.android.androidcrudjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.androidcrudjava.banco.crud.CriaBanco;
import com.android.androidcrudjava.modal.crud.Filme;

public class CadastroFilmes extends AppCompatActivity {
    Filme filmes;
    CriaBanco dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_layout);

        dbHelper = new CriaBanco(CadastroFilmes.this);

        final Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        final EditText txtTitulo = (EditText) findViewById(R.id.txt1titulo);
        final EditText txtGenero = (EditText) findViewById(R.id.txt1Genero);
        final EditText txtAno = (EditText) findViewById(R.id.txt1Ano);
        final EditText txtDiretor = (EditText) findViewById(R.id.txt1Diretor);

        btnCadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                filmes.setTitulo(txtTitulo.getText().toString());
                filmes.setGenero(txtGenero.getText().toString());
                filmes.setAno(txtAno.getText().toString());
                filmes.setDiretor(txtDiretor.getText().toString());

                if(btnCadastrar.getText().toString().equals("Cadastrar")){
                    dbHelper.salvarFilme(filmes);
                    dbHelper.close();

                }

            }
        });
}
}
