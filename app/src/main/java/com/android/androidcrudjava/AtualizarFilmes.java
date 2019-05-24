package com.android.androidcrudjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.androidcrudjava.banco.crud.CriaBanco;
import com.android.androidcrudjava.modal.crud.Filme;

public class AtualizarFilmes extends AppCompatActivity {

    Filme filmes = new Filme();
    Filme editarFilme = new Filme();
    CriaBanco dbHelper;

    Button btnAtualizar;
    EditText txtTitulo,txtGenero,txtAno,txtDiretor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_layout);

        dbHelper = new CriaBanco(AtualizarFilmes.this);

        editarFilme = (Filme) getIntent().getExtras().getSerializable("filme");

        btnAtualizar = (Button) findViewById(R.id.btnAtualizar);
        txtTitulo = (EditText) findViewById(R.id.txt2titulo);
        txtGenero = (EditText) findViewById(R.id.txt2Genero);
        txtAno = (EditText) findViewById(R.id.txt2Ano);
        txtDiretor = (EditText) findViewById(R.id.txt2Diretor);

        btnAtualizar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                filmes.setTitulo(txtTitulo.getText().toString());
                filmes.setGenero(txtGenero.getText().toString());
                filmes.setAno(txtAno.getText().toString());
                filmes.setDiretor(txtDiretor.getText().toString());

                dbHelper.alterarFilme(filmes);
                dbHelper.close();
                Toast.makeText(getApplicationContext(), "Filme Alterado: "+ txtTitulo.getText().toString(),Toast.LENGTH_LONG).show();
                finish();



            }
        });
    }
}
