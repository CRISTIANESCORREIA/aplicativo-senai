package com.example.appsenai.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.appsenai.databinding.ActivityCadastroUsuariosBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroUsuariosActivity extends AppCompatActivity {
    ActivityCadastroUsuariosBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroUsuariosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonCadastrarUsuario.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("message");

                        myRef.setValue("Hello, World!");
                    }
                }
        );
    }


}