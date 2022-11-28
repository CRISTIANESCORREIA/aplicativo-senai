package com.example.appsenai.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appsenai.R;
import com.example.appsenai.databinding.ActivityListaTurmasBinding;

public class ListaTurmasActivity extends AppCompatActivity {

    ActivityListaTurmasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaTurmasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}