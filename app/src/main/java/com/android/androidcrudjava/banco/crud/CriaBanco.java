package com.android.androidcrudjava.banco.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.android.androidcrudjava.modal.crud.Filme;

import java.util.ArrayList;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "sistemafilme";
    private static final String TABELA = "FILMES";
    private static final String ID = "ID";
    private static final String TITULO = "TITULO";
    private static final String DIRETOR = "DIRETOR";
    private static final String ANO = "ANO";
    private static final String GENERO = "GENERO";
    private static final int VERSAO = 1;

    public CriaBanco(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ TABELA+"("
                     +ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                     +TITULO + " TEXT,"
                     +GENERO + " TEXT,"
                     + ANO +" TEXT,"
                     + DIRETOR + " TEXT)";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS";
        db.execSQL(dropTable + TABELA);
        onCreate(db);
    }


    public void salvarFilme(Filme filme){
        ContentValues values = new ContentValues();

        values.put("titulo", filme.getTitulo());
        values.put("genero", filme.getGenero());
        values.put("ano", filme.getAno());
        values.put("diretor", filme.getDiretor());

        getWritableDatabase().insert("filmes", null, values);
    }


    public ArrayList<Filme> getLista(){

        String[] colums = {"id", "titulo", "genero", "ano","diretor"};
        Cursor cursor = getWritableDatabase().query("filmes", colums,null,null,null,null,null,null);
        ArrayList<Filme> filmes = new ArrayList<Filme>();

        while (cursor.moveToNext()){
            Filme filme = new Filme();
            filme.setId(cursor.getLong(0));
            filme.setTitulo(cursor.getString(1));
            filme.setGenero(cursor.getString(2));
            filme.setAno(cursor.getString(3));
            filme.setDiretor(cursor.getString(4));

            filmes.add(filme);
        }

        return filmes;
    }
}
