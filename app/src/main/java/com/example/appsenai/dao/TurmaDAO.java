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

    private static final String DATABASE = "appsenai1";
    private static final int VERSION = 1;
    private PessoaDAO pessoaDAO;

    public TurmaDAO(@Nullable Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql_tb_turma = "CREATE TABLE turma" +
                                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                                "PROFESSOR_ID INT NOT NULL," +
                                "DISCIPLINA TEXT UNIQUE NOT NULL,"+
                                "CURSO TEXT NOT NULL,"+
                                "DIA_DA_SEMANA TEXT NOT NULL," +
                                "TURNO TEXT NOT NULL)"      ;

        /*
        String sql_tb_turma_aluno = "CREATE TABLE turma_aluno" +
                "(ID_TURMA INTEGER NOT NULL," +
                "ID_ALUNO INTEGER NOT NULL)";
*/      sqLiteDatabase.beginTransaction();
        sqLiteDatabase.execSQL(sql_tb_turma);
        Log.w("tb_turma","criou tb turma");
        sqLiteDatabase.endTransaction();

  //      sqLiteDatabase.execSQL(sql_tb_turma_aluno);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public boolean salvar(Turma turma){

        ContentValues values = new ContentValues();
        values.put("PROFESSOR_ID", turma.getProfessor().getId());
        values.put("DISCIPLINA", turma.getDisciplina());
        values.put("CURSO",turma.getCurso());
        values.put("DIA_DA_SEMANA",turma.getDia_da_semana());
        values.put("TURNO",turma.getTurno());
        long salvo = getWritableDatabase().insert("turma",null,values);
        if(salvo>=0){
            return true;
        }else{
            return false;
        }
    }

    private void salvaTurmaAluno(Turma turma){

        for(Pessoa aluno : turma.getAlunos()){

            ContentValues values = new ContentValues();
            values.put("ID_TURMA", turma.getIdTurma());
            values.put("ID_ALUNO", aluno.getId());
            getWritableDatabase().insert("turma_aluno",null,values);

        }

    }

    /*
     * Retorna lista com dados do banco*/
    public ArrayList<Turma> getListTurmas(){
        String columns[] = {"ID","PROFESSOR_ID","DISCIPLINA","CURSO","DIA_DA_SEMANA","TURNO"};
        Cursor cursor = getReadableDatabase().query("turma",columns,null,null,null,null,null);
        ArrayList<Turma> listaTurma = new ArrayList<Turma>();
        while(cursor.moveToNext()){
            //PessoaDAO pDao = new PessoaDAO()
            Turma turma = new Turma(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );

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

    public boolean deletar(int id){
        String args[] = {""+id};
        int retorno = getWritableDatabase().delete("turma","ID=?",args);
        if(retorno >0){
            return true;
        }else{
            return false;
        }
    }

    public void deletarAluno(int id, int idAluno){

        String args[] = {""+id,""+idAluno};

        int i;

        i = getWritableDatabase().delete("turma_aluno","ID_TURMA=? and ID_ALUNO=?",args);

    }
}
