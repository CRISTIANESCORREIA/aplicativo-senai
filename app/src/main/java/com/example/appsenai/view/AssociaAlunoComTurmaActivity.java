package com.example.appsenai.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appsenai.R;
import com.example.appsenai.databinding.ActivityAssociaAlunoComTurmaBinding;

public class AssociaAlunoComTurmaActivity extends AppCompatActivity {
    ActivityAssociaAlunoComTurmaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAssociaAlunoComTurmaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAssociar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AssociaAlunoComTurmaActivity.this, "Aluno associado a turma com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}