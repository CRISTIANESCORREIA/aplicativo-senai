package com.example.appsenai.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appsenai.databinding.ActivityResetSenhaBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ResetSenhaActivity extends AppCompatActivity {
    ActivityResetSenhaBinding binding;

    private static final String TAG = "EmailPassword";
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetSenhaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        binding.btCadastrar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarDados();

                mAuth.sendPasswordResetEmail("gabrielgamerlive@gmail.com").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ResetSenhaActivity.this, "Enviado reset de senha para o e-mail " + binding.editEmail.getText().toString(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ResetSenhaActivity.this, "Ocorreu um erro ao enviar o e-mail para o reset de senha.\n "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
               // createAccount(editEmail.getText().toString(),editSenha.getText().toString());
            }
        });
    }

    private void validarDados() {
        if(binding.editEmail.getText().toString().trim().equals("")){
            binding.editEmail.setError("E-mail não preenchido!");
        }
    }

}