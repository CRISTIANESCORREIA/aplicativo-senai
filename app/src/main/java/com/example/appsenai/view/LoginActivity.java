package com.example.appsenai.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsenai.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private Button botao;
    private EditText editEmail, editSenha;
    private TextView txt_tela_cadastro;
    private static final String TAG = "EmailPasswordLogin";
    // [START declare_auth]
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializar();
        getSupportActionBar().hide();

        txt_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, FormCadastroActivity.class);
                startActivity(intent);
            }
        });




        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarDados()){
                    signIn(editEmail.getText().toString(),editSenha.getText().toString());
                }else{
                    Toast.makeText(LoginActivity.this, "Obrigatório preenchimento de todos campos!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    protected void onResume() {
        if(mAuth.getCurrentUser()!=null){
            updateUI(mAuth.getCurrentUser());
        }
        super.onResume();
    }


    private boolean validarDados() {
        if(editEmail.getText().toString().equals("")){
            editEmail.setError("E-mail não preenchido!");
            return false;
        }

        if(editSenha.getText().toString().equals("")){
            editSenha.setError("Senha não preenchido!");
            return false;
        }
        return true;
    }

    private void inicializar() {
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        txt_tela_cadastro = findViewById(R.id.txtTelaCadastro);
        botao = findViewById(R.id.btEntrar);
        mAuth = FirebaseAuth.getInstance();
        Log.w("mAuth",mAuth.toString());

    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Autenticação Falhou.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    private void updateUI(FirebaseUser user) {
        if(user!=null){
            Intent intent = new Intent(LoginActivity.this, MenuUsuarioActivity.class);
            startActivity(intent);
        }
    }
}