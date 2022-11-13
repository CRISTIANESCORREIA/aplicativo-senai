package com.example.appsenai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FormCadastro extends AppCompatActivity {

    Button btCadastrar;
    EditText editSenha,editEmail,editNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);
        inicializar();
     //   getActionBar().hide();
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarDados();
            }
        });

    }

    private void validarDados() {
        if(editEmail.getText().toString().equals("")){
            editEmail.setError("E-mail não preenchido!");
        }
        if(editNome.getText().toString().equals("")){
            editNome.setError("Nome não preenchido!");
        }
        if(editSenha.getText().toString().equals("")){
            editSenha.setError("Senha não preenchido!");
        }
    }

    private void inicializar() {
        btCadastrar = findViewById(R.id.btCadastrar1);
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.edit_senha);
    }
}