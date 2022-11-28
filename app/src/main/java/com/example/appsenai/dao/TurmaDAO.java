package com.example.appsenai.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.appsenai.entity.Pessoa;
import com.example.appsenai.entity.Turma;

import java.util.ArrayList;

public class TurmaDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "appsenai";
    private static final int VERSION = 1;
    private PessoaDAO pessoaDAO;

    public TurmaDAO(@Nullable Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql_tb_turma = "CREATE TABLE turma" +
                                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                                "PROFESSOR TEXT NOT NULL," +
                                "DISCIPLINA TEXT NOT NULL)";

        String sql_tb_turma_aluno = "CREATE TABLE turma" +
                "(ID_TURMA INTEGER NOT NULL," +
                "ID_ALUNO INTEGER NOT NULL)";

        sqLiteDatabase.execSQL(sql_tb_turma);
        sqLiteDatabase.execSQL(sql_tb_turma_aluno);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        String sql_tb_turma = "CREATE TABLE turma" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "ID_PROFESSOR INTEGER NOT NULL," +
                "DISCIPLINA TEXT NOT NULL)";

        String sql_tb_turma_aluno = "CREATE TABLE turma_aluno" +
                "(ID_TURMA INTEGER NOT NULL," +
                "ID_ALUNO INTEGER NOT NULL)";

        sqLiteDatabase.execSQL(sql_tb_turma);
        sqLiteDatabase.execSQL(sql_tb_turma_aluno);

    }

    public void salvar(Turma turma){

        ContentValues values = new ContentValues();
        values.put("PROFESSOR", turma.getProfessor().getId());
        values.put("DISCIPLINA", turma.getDisciplina());
        getWritableDatabase().insert("turma",null,values);

    }

    private void salvaTurmaAluno(Turma turma){

        for(Pessoa aluno : turma.getAlunos()){

            ContentValues values = new ContentValues();
            values.put("ID_TURMA", turma.getIdTurma());
            values.put("ID_ALUNO", turma.getDisciplina());
            getWritableDatabase().insert("turma_aluno",null,values);

        }

    }

    /*
     * Retorna lista com dados do banco*/
    public ArrayList<Turma> getListTurma(){

        String columns[] = {"ID","ID_PROFESSOR","DISCIPLINA"};

        Cursor cursor = getReadableDatabase().query("turma",columns,null,null,null,null,null);

        ArrayList<Turma> listaTurma = new ArrayList<Turma>();

        while(cursor.moveToNext()){

            Pessoa pessoa = pessoaDAO.buscaPorId(cursor.getInt(1));

            Turma turma = new Turma(Long.parseLong(cursor.getString(0)),pessoa,cursor.getString(2));

            listaTurma.add(turma);
        }
        cursor.close();

        return listaTurma;
    }

    public boolean alterar(Turma turma){

        ContentValues values = new ContentValues();

        values.put("PROFESSOR", turma.getProfessor().getId());

        values.put("DISCIPLINA", turma.getDisciplina());

        String args[] = {String.valueOf(turma.getIdTurma())};

        Log.w("idL",turma.getIdTurma()+"");

        Log.w("valoresAlterar",values.toString());

        int retorno = getWritableDatabase().update("turma",values,"ID=?",args);

        Log.w("retornoID",String.valueOf(retorno));

        if(retorno > 0){

            return true;

        }

        return false;

    }

    public void deletar(int id){

        String args[] = {""+id};

        int i;

        i = getWritableDatabase().delete("turma","ID=?",args);

    }

    public void deletarAluno(int id, int idAluno){

        String args[] = {""+id,""+idAluno};

        int i;

        i = getWritableDatabase().delete("turma_aluno","ID_TURMA=? and ID_ALUNO=?",args);

    }
}
