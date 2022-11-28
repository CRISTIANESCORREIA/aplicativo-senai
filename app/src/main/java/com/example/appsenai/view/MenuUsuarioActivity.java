package com.example.appsenai.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appsenai.R;
import com.example.appsenai.databinding.ActivityMenuUsuarioBinding;

public class MenuUsuarioActivity extends AppCompatActivity {

    ActivityMenuUsuarioBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgBtnCadastroAlunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CadastroAlunoActivity.class);
                startActivity(intent);
            }
        });

        binding.imgBtnCadastroTurmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CadastroTurmaActivity.class);
                startActivity(intent);
            }
        });
    }
}