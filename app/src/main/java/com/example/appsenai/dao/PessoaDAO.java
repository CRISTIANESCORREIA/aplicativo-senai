package com.example.appsenai.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.appsenai.entity.Pessoa;

import java.util.ArrayList;

public class PessoaDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "appsenai1";

    public PessoaDAO(@Nullable Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql_tb_usuario = "CREATE TABLE pessoa" +
                                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                                "NOME_COMPLETO TEXT NOT NULL," +
                                "EMAIL TEXT NOT NULL," +
                                "SENHA TEXT NOT NULL," +
                                "CPF   TEXT NOT NULL," +
                                "TIPO TEXT NOT NULL)";

        sqLiteDatabase.execSQL(sql_tb_usuario);
        Log.w("Base","Criou a tabela");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS pessoa";
        sqLiteDatabase.execSQL(sql);
        String sql_tb_usuario = "CREATE TABLE pessoa" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "NOME_COMPLETO TEXT NOT NULL," +
                "EMAIL TEXT NOT NULL," +
                "SENHA TEXT NOT NULL," +
                "CPF   TEXT NOT NULL," +
                "TIPO  TEXT NOT NULL)";
        sqLiteDatabase.beginTransaction();

        sqLiteDatabase.execSQL(sql_tb_usuario);
        Log.w("baseUp","Criou a tabela pelo onupgrade");
        sqLiteDatabase.endTransaction();

    }

    public void salvar(Pessoa pessoa){
        ContentValues values = new ContentValues();
        values.put("NOME_COMPLETO",pessoa.getNomeComleto());
        values.put("EMAIL", pessoa.getEmail());
        values.put("SENHA", pessoa.getSenha());
        values.put("CPF", pessoa.getCpf());
        values.put("TIPO", pessoa.getTipo());
        long i =  getWritableDatabase().insert("pessoa",null,values);
        Log.w("id_salvar","code: "+i);
    }
    /*
     * Retorna lista com dados do banco*/
    public ArrayList<Pessoa> getListPessoas(){

        String columns[] = {"ID","NOME_COMPLETO","EMAIL","SENHA","CPF","TIPO"};

        Cursor cursor = getReadableDatabase().query("pessoa",columns,null,null,null,null,null);

        ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();

        while(cursor.moveToNext()){

            Pessoa pessoa = new Pessoa(cursor.getInt(0),cursor.getString(5),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

            listaPessoas.add(pessoa);
        }
        cursor.close();

        return listaPessoas;
    }

    public boolean alterar(Pessoa pessoa){

        ContentValues values = new ContentValues();

        values.put("NOME_COMPLETO",pessoa.getNomeComleto());

        values.put("EMAIL", pessoa.getEmail());

        values.put("SENHA", pessoa.getSenha());

        values.put("CPF", pessoa.getCpf());

        values.put("TIPO", pessoa.getTipo());

        String args[] = {String.valueOf(pessoa.getId())};

        Log.w("idL", String.valueOf(pessoa.getId()));

        Log.w("valoresAlterar",values.toString());

        int retorno = getWritableDatabase().update("pessoa",values,"id=?",args);

        Log.w("retornoID",String.valueOf(retorno));

        if(retorno > 0){

            return true;

        }

        return false;

    }

    public Pessoa buscaPorId(int id){

        String columns[] = {"ID","NOME_COMPLETO","EMAIL","SENHA","CPF","TIPO"};

        String args[] = {""+id};

        Cursor cursor = getReadableDatabase().query("pessoa",columns,null,args,null,null,null);
        Pessoa pessoa = null;

        while(cursor.moveToNext()){
            pessoa = new Pessoa(cursor.getInt(0),
                    cursor.getString(5)
                    ,cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
        }

        cursor.close();

        return pessoa;

    }

    public String buscaTipoPorEmail(String email){
        String columns[] = {"TIPO"};
        String args[] = {email};
        Cursor cursor = getReadableDatabase().query("pessoa",columns,"email like ?",args ,null,null,null);
        cursor.moveToFirst();
        String tipo;
        if(cursor.getCount() > 0){
         tipo = cursor.getString(0).toString();
         Log.w("retorno",tipo);
         return tipo;
        }else{
            return "inexistenteOuInativo";
        }
/*

            while (cursor.moveToNext()){
                tipo = cursor.getString(0).toString();
            }
            cursor.close();
            return tipo;
        }else{
            return "Sysadmin";
        }
*/
    }


    public boolean deletar(int id){
        String args[] = {""+id};
        int retorno = getWritableDatabase().delete("pessoa","ID=?",args);
        if(retorno > 0){
            return true;
        }else{
            return false;
        }
    }
}
