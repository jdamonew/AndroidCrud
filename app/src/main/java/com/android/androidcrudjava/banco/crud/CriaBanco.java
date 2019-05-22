package com.android.androidcrudjava.banco.crud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
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
                     +ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
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
}
