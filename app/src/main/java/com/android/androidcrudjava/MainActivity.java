package com.android.androidcrudjava;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.androidcrudjava.banco.crud.CriaBanco;
import com.android.androidcrudjava.modal.crud.Filme;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    CriaBanco dbHelper;
    ArrayList<Filme> listview_filmes;
    Filme filme;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar2);
        btnCadastrar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroFilmes.class);
                startActivity(intent);
            }
        });

        lista = (ListView) findViewById(R.id.listview_filme);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                Filme filmeEscolhido = (Filme) adapter.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this, AtualizarFilmes.class);
                i.putExtra("filme", filmeEscolhido);
                startActivity(i);

            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                filme = (Filme) adapter.getItemAtPosition(position);
                return false;
            }
        });

    }

    protected void onResume() {
        super.onResume();
        carregarFilme();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar Filme");

        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                dbHelper = new CriaBanco(MainActivity.this);
                dbHelper.deletarFilme(filme);
                dbHelper.close();
                carregarFilme();
                return true;
            }
        });
    }

    private void carregarFilme(){

        dbHelper = new CriaBanco(MainActivity.this);
        listview_filmes = dbHelper.getLista();
        dbHelper.close();

        if(listview_filmes!=null){

            adapter = new ArrayAdapter<Filme>(MainActivity.this, android.R.layout.simple_list_item_1, listview_filmes);
            lista.setAdapter(adapter);
        }


    }

}

