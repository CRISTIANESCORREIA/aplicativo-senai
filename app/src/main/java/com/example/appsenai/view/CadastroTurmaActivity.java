package com.example.appsenai.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.appsenai.R;
import com.example.appsenai.dao.PessoaDAO;
import com.example.appsenai.databinding.ActivityCadastrodaturmaBinding;
import com.example.appsenai.entity.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class CadastroTurmaActivity extends AppCompatActivity {

    ActivityCadastrodaturmaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastrodaturmaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        inicializarSpinnerProfessores();
    }

    private void inicializarSpinnerProfessores() {
        PessoaDAO pessoaDAO = new PessoaDAO(getApplicationContext());
        List<Pessoa> pessoasList = pessoaDAO.getListPessoas();
        if(pessoasList.size()>0){
            ArrayAdapter<Pessoa> adapter =new ArrayAdapter<Pessoa>(this,android.R.layout.simple_spinner_item, pessoasList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerProfessores.setAdapter(adapter);
        }

    }


}