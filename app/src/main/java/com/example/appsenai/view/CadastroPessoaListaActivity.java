package com.example.appsenai.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.appsenai.databinding.ActivityCadastroPessoaListaBinding;

public class CadastroPessoaListaActivity extends AppCompatActivity{

    ActivityCadastroPessoaListaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroPessoaListaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.listviewAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CadastroPessoaListaActivity.this, "item "+position+" Selecionado para exluir", Toast.LENGTH_SHORT).show();
                //fazer abrir caixa de texto perguntando se confirma a exclusão

                AlertDialog.Builder builder = new AlertDialog.Builder(CadastroPessoaListaActivity.this);
                builder.setMessage("Deseja excluir a pessoa?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(CadastroPessoaListaActivity.this, "A pessoa sera exluida! ", Toast.LENGTH_SHORT).show();
                        //Adicionar ação da exclusão da pessoa e recarregar a lista atualizada
                    }
                }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // Toast.makeText(CadastroAlunoActivity.this, "Cancelado exclusão!", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return false;
            }
        });

        binding.listviewAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CadastroPessoaListaActivity.this, "item selecionado para editar", Toast.LENGTH_SHORT).show();
                //fazer ação para abrir nova tela com os dados do aluno selecionado.
                Intent intent = new Intent(getApplicationContext(),CadastroDePessoaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("status","alterar");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.btnCadastrarPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(CadastroPessoaListaActivity.this, "Abre a tela para novo cadastro", Toast.LENGTH_SHORT).show();
                //criar intent para abrir nova tela de cadastro do aluno
                Intent intent = new Intent(getApplicationContext(),CadastroDePessoaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("status","inserir");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}