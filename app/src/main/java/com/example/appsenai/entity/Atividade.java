package com.example.appsenai.entity;

import android.net.Uri;

import java.io.Serializable;

public class Atividade implements Serializable {
    private int id_atividade;
    private int id_disciplina;
    private String titulo;
    private String descricao;
    private int nota;
    private boolean permiteArquivoResposta;
   // private Uri arquivoResposta;
    private Uri arquivoAnexo;




}
