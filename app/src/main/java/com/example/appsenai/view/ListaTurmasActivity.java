package com.example.appsenai.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appsenai.R;
import com.example.appsenai.databinding.ActivityListaTurmasBinding;

public class ListaTurmasActivity extends AppCompatActivity {

    ActivityListaTurmasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaTurmasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCadastroTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CadastroTurmaActivity.class);
                startActivity(intent);
            }
        });

    }
}