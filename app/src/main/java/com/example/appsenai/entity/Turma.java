package com.example.appsenai.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Turma implements Serializable {

    private Long         idTurma;
    private Pessoa       professor;
    private String       disciplina;
    private List<Pessoa> alunos;

    public Turma(Long idTurma, Pessoa professor, String disciplina, List<Pessoa> alunos) {

        this.idTurma = idTurma;

        this.professor = professor;

        this.disciplina = disciplina;

        this.alunos = alunos;
    }

    public Turma(Pessoa professor, String disciplina, List<Pessoa> alunos) {

        this.professor = professor;

        this.disciplina = disciplina;

        this.alunos = alunos;
    }

    public Turma(Long idTurma, Pessoa professor, String disciplina) {

        this.professor = professor;

        this.disciplina = disciplina;

    }

    public Integer retornaQuantidadeAlunos(){

        return this.alunos.size();

    }

    public Long getIdTurma() {
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

    public void setIdTurma(Long idTurma) {
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
