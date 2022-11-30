package com.example.appsenai.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appsenai.databinding.ActivityMenuUsuarioBinding;
import com.example.appsenai.entity.Pessoa;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;

public class MenuUsuarioActivity extends AppCompatActivity {

    ActivityMenuUsuarioBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtUsername.setText(binding.txtUsername.getText()+"\n"+FirebaseAuth.getInstance().getCurrentUser().getEmail());
        binding.imgBtnCadastroAlunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroPessoaListaActivity.class);
                startActivity(intent);
            }
        });

        binding.imgBtnCadastroTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CadastroTurmaActivity.class);
                startActivity(intent);
            }
        });

        binding.imgBtnCadastroAlunoComTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AssociaAlunoComTurmaActivity.class);
                startActivity(intent);
            }
        });

        binding.imgBtnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

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
                finish();
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
        // [START auth_sign_out]
        FirebaseAuth.getInstance().signOut();
        // [END auth_sign_out]
    }
}