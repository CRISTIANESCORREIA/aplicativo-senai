package com.example.appsenai.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Turma implements Serializable {

    private int         idTurma;
    private int professor_id;
    private Pessoa       professor;
    private String       disciplina;
    private String curso;
    private String dia_da_semana;
    private String turno;
    private List<Pessoa> alunos;

    public String getCurso() {
        return curso;
    }

    public Turma(int idTurma, Pessoa professor, String disciplina, String curso, String dia_da_semana, String turno, List<Pessoa> alunos) {
        this.idTurma = idTurma;
        this.professor = professor;
        this.disciplina = disciplina;
        this.curso = curso;
        this.dia_da_semana = dia_da_semana;
        this.turno = turno;
        this.alunos = alunos;
    }

    public Turma(Pessoa professor, String disciplina, String curso, String dia_da_semana, String turno) {
        this.professor = professor;
        this.disciplina = disciplina;
        this.curso = curso;
        this.dia_da_semana = dia_da_semana;
        this.turno = turno;

    }

    public Turma(int idTurma, int professor_id, String disciplina, String curso, String dia_da_semana, String turno) {
        this.idTurma = idTurma;
        this.professor_id = professor_id;
        this.disciplina = disciplina;
        this.curso = curso;
        this.dia_da_semana = dia_da_semana;
        this.turno = turno;

    }

    @Override
    public String toString() {
        return  curso +"\n\t\t"+  disciplina+" - "+ dia_da_semana  +" - "+ turno;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDia_da_semana() {
        return dia_da_semana;
    }

    public void setDia_da_semana(String dia_da_semana) {
        this.dia_da_semana = dia_da_semana;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }



    public Integer retornaQuantidadeAlunos(){

        return this.alunos.size();

    }

    public int getIdTurma() {
        return idTurma;
    }

    public Pessoa getProfessor() {
        return professor;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public List<Pessoa> getAlunos() {
        return alunos;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public void setProfessor(Pessoa professor) {
        this.professor = professor;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public void setAlunos(List<Pessoa> alunos) {
        this.alunos = alunos;
    }
}
