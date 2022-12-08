package com.example.appsenai.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appsenai.dao.PessoaDAO;
import com.example.appsenai.databinding.ActivityMenuUsuarioBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MenuUsuarioActivity extends AppCompatActivity {

    ActivityMenuUsuarioBinding binding;
    PessoaDAO pDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Menu principal - SuperApp");
        pDao = new PessoaDAO(getApplicationContext());
        validaAcessos();
        inicializar();
    }

    private void inicializar() {

        binding.txtUsername.setText(binding.txtUsername.getText()+"\n"+FirebaseAuth.getInstance().getCurrentUser().getEmail());
        binding.imgBtnCadastroAlunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreActivity( CadastroPessoaListaActivity.class);
            }
        });

        binding.imgBtnCadastroTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreActivity(CadastroTurmaListaActivity.class);
            }
        });



        binding.imgBtnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                abreActivity(LoginActivity.class);

            }
        });

        binding.imgBtnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreActivity(SobreSenaiActivity.class);
            }
        });

        binding.imgBtnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreActivity(ConfiguracoesActivity.class);
            }
        });

        binding.btnAtividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreActivity(AtividadeActivity.class);
            }
        });
    }

    private void validaAcessos() {
       String tipo = "";
       if(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString().equals("superappsenai@gmail.com")){
           tipo = "Sysadmin";
       }else{
           tipo = pDao.buscaTipoPorEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());
       }
        Log.w("tipo",tipo);
       if(tipo.contains("Aluno")){
           binding.imgBtnCadastroAlunos.setVisibility(View.GONE);
          // binding.imgBtnCadastroAlunoComTurma.setVisibility(View.GONE);
           binding.imgBtnCadastroTurma.setVisibility(View.GONE);
       }else if(tipo.contains("Professor")){

       }else if(tipo.contains("inexistenteOuInativo")){
           binding.imgBtnCadastroAlunos.setVisibility(View.GONE);
          // binding.imgBtnCadastroAlunoComTurma.setVisibility(View.GONE);
           binding.imgBtnCadastroTurma.setVisibility(View.GONE);
           binding.imgBtnSobre.setVisibility(View.GONE);
           binding.imgBtnConfig.setVisibility(View.GONE);
           binding.txtUsername.setText("INATIVO OU INEXISTENTE - Saia do sistema!");
           FirebaseAuth.getInstance().getCurrentUser().delete();
           Toast.makeText(this, "Conta excluida, fechando sistema!", Toast.LENGTH_LONG).show();
           Handler handler = new Handler();
           handler.postDelayed(new Runnable() {
               @Override
               public void run() {
                   binding.imgBtnSair.performClick();
               }
           },3000);

       }
    }

    private void abreActivity( Class<?> activity) {
        Intent intent = new Intent(getApplicationContext(),activity);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        valicaoBtnVoltarClicado();
    }


    private void valicaoBtnVoltarClicado(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuUsuarioActivity.this);
        builder.setMessage("Deseja fechar o aplicativo?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               signOut();// fecha o app

            }
        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //não faz nada
            }
        }).setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}