package com.example.appsenai.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appsenai.R;
import com.example.appsenai.dao.PessoaDAO;
import com.example.appsenai.dao.TurmaDAO;
import com.example.appsenai.databinding.ActivityCadastrodaturmaBinding;
import com.example.appsenai.entity.Pessoa;
import com.example.appsenai.entity.Turma;

import java.util.ArrayList;
import java.util.List;

public class CadastroTurmaActivity extends AppCompatActivity {

    ActivityCadastrodaturmaBinding binding;
    Turma turma;
    TurmaDAO tDao;
    Pessoa professor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastrodaturmaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        inicializarSpinnerProfessores();
        professor = new Pessoa();
        tDao = new TurmaDAO(CadastroTurmaActivity.this);
        binding.btnCadastraTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                professor = (Pessoa) binding.spinnerProfessores.getSelectedItem();
                Log.w("professor",professor.toString());

                turma = montaTurmaSemAlunos();
                if(tDao.salvar(turma)){
                    Toast.makeText(CadastroTurmaActivity.this, "Cadastro da Turma realizado com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(CadastroTurmaActivity.this, "Ocorreu algum erro ao realizar o cadastro!", Toast.LENGTH_SHORT).show();
                    finish();
                };
            }
        });
    }

    private Turma montaTurmaSemAlunos() {
        return  new Turma(professor,
                binding.edtNomeDisciplina.getText().toString(),
                binding.spinnerCurso.getSelectedItem().toString(),
                binding.spinnerTurno.getSelectedItem().toString(),
                binding.spinnerDiaDaSemana.getSelectedItem().toString());
    }

    private void inicializarSpinnerProfessores() {
        PessoaDAO pessoaDAO = new PessoaDAO(getApplicationContext());
        List<Pessoa> pessoasList = pessoaDAO.getListProfessores();
        if(pessoasList.size()>0){
            ArrayAdapter<Pessoa> adapter =new ArrayAdapter<Pessoa>(this,
                    android.R.layout.simple_spinner_item,
                    pessoasList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerProfessores.setAdapter(adapter);
        }

    }


}