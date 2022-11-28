package com.example.appsenai.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appsenai.R;
import com.example.appsenai.databinding.ActivityCadastrodaturmaBinding;

public class CadastroTurmaActivity extends AppCompatActivity {

    ActivityCadastrodaturmaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrodaturma);
    }
}