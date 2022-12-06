package com.example.appsenai.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appsenai.R;
import com.example.appsenai.databinding.ActivityConfiguracoesBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracoesActivity extends AppCompatActivity {

    ActivityConfiguracoesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfiguracoesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.textView7.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        binding.btnResetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ConfiguracoesActivity.this, "Enviado reset de senha para o e-mail " + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ConfiguracoesActivity.this, "Ocorreu um erro ao enviar o e-mail para o reset de senha.\n "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}