package com.example.appsenai.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appsenai.databinding.ActivityAtividadeBinding;

public class AtividadeActivity extends AppCompatActivity {
    ActivityAtividadeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAtividadeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Atividade - SuperApp");
    }
}