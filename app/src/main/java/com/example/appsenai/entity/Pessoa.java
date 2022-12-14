package com.example.appsenai.entity;

import java.io.Serializable;
import java.util.Random;

public class Pessoa implements Serializable {
    private int id;
    private String tipo; // Pode ser A-ALUNO - P-PROFESSOR- G-GERENCIADOR DO SISTEMA
    private String nomeComleto;
    private String email;
    private String senha;
    private String cpf;

    public Pessoa() {
        this.id = 0;
        this.nomeComleto = "Sem professor cadastrado";
        this.email="nomail@email.com";
        this.cpf = "009.000.000-12";
        this.tipo="Nenhum";
        this.senha="";
    }

    @Override
    public String toString() {
        return  this.tipo +
                " - " + this.nomeComleto;
    }

    public Pessoa(int id, String tipo, String nomeComleto, String email, String senha, String cpf) {
        this.id = id;
        this.tipo = tipo;
        this.nomeComleto = nomeComleto;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
    }

    public Pessoa(String tipo, String nomeComleto, String email, String senha, String cpf) {
        this.tipo = tipo;
        this.nomeComleto = nomeComleto;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
    }

    public boolean validaEmail(){
        if(this.email.contains("@") && this.email.contains(".com")
                || this.email.contains(".com.br")
                || this.email.contains(".net")
        ){
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNomeComleto() {
        return nomeComleto;
    }

    public void setNomeComleto(String nomeComleto) {
        this.nomeComleto = nomeComleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
