package com.example.appsenai.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appsenai.dao.PessoaDAO;
import com.example.appsenai.databinding.ActivityCadastroDePessoaBinding;
import com.example.appsenai.entity.Pessoa;

public class CadastroDePessoaActivity extends AppCompatActivity {

    ActivityCadastroDePessoaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroDePessoaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnConfirmarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaCampos()) {
                    Pessoa p = montarObjetoPessoa();
                    PessoaDAO pdao = new PessoaDAO(getApplicationContext());
                    pdao.salvar(p);
                    Toast.makeText(CadastroDePessoaActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private Pessoa montarObjetoPessoa() {
        Pessoa p = new Pessoa(
                binding.spinnerTipo.getSelectedItem().toString(),
                binding.edtNomePessoa.getText().toString(),
                binding.edtEmail.getText().toString(),
                "abc123@@",
                binding.edtCpf.getText().toString());

        return p;
    }

    private boolean validaCampos() {
        if(binding.edtNomePessoa.getText().toString().trim().equals("")){
            binding.edtNomePessoa.setError("Nome não informado!");
            binding.edtNomePessoa.requestFocus();
            return false;
        }
        if(binding.edtEmail.getText().toString().trim().equals("")){
            binding.edtEmail.setError("E-mail não informado!");
            binding.edtEmail.requestFocus();
            return false;
        }
        if(binding.edtCpf.getText().toString().trim().equals("")){
            binding.edtCpf.setError("Cpf não informado!");
            binding.edtCpf.requestFocus();
            return false;
        }


        if(binding.spinnerTipo.getSelectedItem().toString().contains("Selecione")){
            Toast.makeText(this, "Tipo não selecionado!", Toast.LENGTH_SHORT).show();
            binding.spinnerTipo.performClick();
            return false;
        }

        return true;
    }
}