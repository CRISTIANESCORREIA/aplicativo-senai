package com.example.appsenai.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.example.appsenai.dao.TurmaDAO;
import com.example.appsenai.databinding.ActivityCadastroTurmaListaBinding;
import com.example.appsenai.entity.Turma;

import java.util.ArrayList;

public class CadastroTurmaListaActivity extends AppCompatActivity {
    ActivityCadastroTurmaListaBinding binding;
    ArrayList<Turma> turmas;
    TurmaDAO tDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroTurmaListaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Turmas");
        binding.btnNovaTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroTurmaActivity.class);
                startActivity(intent);
            }
        });

        binding.imgBtnCadastroAlunoComTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AssociaAlunoComTurmaActivity.class);
                startActivity(intent);
            }
        });

        binding.listviewTurma.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CadastroTurmaListaActivity.this);
                builder.setMessage("Deseja excluir a turma?\n Seus alunos também serão excluidos!").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Turma turma = (Turma) turmas.get(position);
                        if(tDao.deletar( (int) turma.getIdTurma())){
                            Toast.makeText(CadastroTurmaListaActivity.this, "A turma foi excluida com sucesso! ", Toast.LENGTH_SHORT).show();
                            atualizarListaAdapter();
                        }else{
                            Toast.makeText(CadastroTurmaListaActivity.this, "Ocorreu falha ao excluir! ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarListaAdapter();
    }

    private void atualizarListaAdapter() {
        tDao = new TurmaDAO(CadastroTurmaListaActivity.this);
        turmas = tDao.getListTurmas() ;
        if(turmas != null) {
            ArrayAdapter<Turma> adapter = new ArrayAdapter<Turma>(CadastroTurmaListaActivity.this,
                    android.R.layout.simple_list_item_1, turmas);
            binding.listviewTurma.setAdapter(adapter);
        }
    }
}