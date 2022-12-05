package com.example.appsenai.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appsenai.databinding.ActivityMenuUsuarioBinding;
import com.example.appsenai.entity.Pessoa;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;

public class MenuUsuarioActivity extends AppCompatActivity {

    ActivityMenuUsuarioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        inicializar();
    }

    private void inicializar() {
        binding.txtUsername.setText(binding.txtUsername.getText()+"\n"+FirebaseAuth.getInstance().getCurrentUser().getEmail());
        binding.imgBtnCadastroAlunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreActivity(getApplicationContext(), CadastroPessoaListaActivity.class);
            }
        });

        binding.imgBtnCadastroTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreActivity(getApplicationContext(),CadastroTurmaActivity.class);
            }
        });

        binding.imgBtnCadastroAlunoComTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreActivity(getApplicationContext(),AssociaAlunoComTurmaActivity.class);
            }
        });

        binding.imgBtnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                abreActivity(getApplicationContext(),LoginActivity.class);

            }
        });

        binding.imgBtnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreActivity(getApplicationContext(),SobreSenaiActivity.class);
            }
        });

    }

    private void abreActivity(Context applicationContext, Class<?> activity) {
        Intent intent = new Intent(applicationContext,activity);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        valicaoBtnVoltarClicado();
    }


    private void valicaoBtnVoltarClicado(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuUsuarioActivity.this);
        builder.setMessage("Deseja fechar o aplicativo?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               signOut();// fecha o app

            }
        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //não faz nada
            }
        }).setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}