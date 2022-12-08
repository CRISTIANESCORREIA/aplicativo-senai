package com.example.appsenai.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appsenai.dao.PessoaDAO;
import com.example.appsenai.databinding.ActivityCadastroDePessoaBinding;
import com.example.appsenai.entity.Pessoa;
import com.example.appsenai.entity.util.MaskEditUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastroDePessoaActivity extends AppCompatActivity {

    ActivityCadastroDePessoaBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroDePessoaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        Bundle bundle = getIntent().getExtras();
        String valida =  bundle.getString("status");
        Pessoa pAlterar  = (Pessoa) bundle.getSerializable("Pessoa");;
        if(valida.equals("inserir")){
            binding.edtId.setVisibility(View.GONE);

            binding.btnAlterar.setVisibility(View.GONE);
            binding.edtCpf.addTextChangedListener(MaskEditUtil.mask(binding.edtCpf, MaskEditUtil.FORMAT_CPF));
        }else{
            binding.edtId.setVisibility(View.VISIBLE);
            binding.btnAlterar.setVisibility(View.VISIBLE);
            binding.edtId.setText(String.valueOf(pAlterar.getId()));

            binding.btnConfirmarCadastro.setVisibility(View.GONE);
            binding.textViewPessoas.setText("Edição de Pessoa");
            binding.edtNomePessoa.setText(pAlterar.getNomeComleto().toString());
            if(pAlterar.getTipo().toString().equals("Aluno")){
                binding.spinnerTipo.setSelection(1);
            }else if(pAlterar.getTipo().toString().equals("Professor")){
                binding.spinnerTipo.setSelection(2);
            }else if(pAlterar.getTipo().toString().equals("Sysadmin")){
                binding.spinnerTipo.setSelection(3);
            }else{
                binding.spinnerTipo.setSelection(0);
            }
            binding.edtEmail.setText(pAlterar.getEmail());
            binding.edtCpf.addTextChangedListener(MaskEditUtil.mask(binding.edtCpf, MaskEditUtil.FORMAT_CPF));
            binding.edtCpf.setText(pAlterar.getCpf());

        }

        binding.btnConfirmarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaCampos()) {
                        Pessoa p = montarObjetoPessoa(false);
                        PessoaDAO pdao = new PessoaDAO(getApplicationContext());
                        pdao.salvar(p);
                        createAccount(p.getEmail(),p.getSenha());
                        Toast.makeText(CadastroDePessoaActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                }
            }
        });

        binding.btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PessoaDAO pdao = new PessoaDAO(getApplicationContext());
                Log.w("alteradoAntes",pAlterar.toString());
                Pessoa Alterar = montarObjetoPessoa(true);
                Log.w("alterado",Alterar.toString());
                pdao.alterar(Alterar);
                Toast.makeText(CadastroDePessoaActivity.this, "Alteração feita com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void createAccount(String email, String password) {
        // [START create_user_with_email]

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // Log.d(TAG, "createUserWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Toast.makeText(CadastroDePessoaActivity.this, "Conta criada com sucesso.",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CadastroDePessoaActivity.this, "Ocorreu um erro: "+task.getException(),
                                    Toast.LENGTH_LONG).show();
                            //updateUI(null);
                        }
                    }
                });
        // [END create_user_with_email]
    }


    private Pessoa montarObjetoPessoa(boolean editar) {
        Pessoa pessoa = new Pessoa();
        if(editar){
           pessoa = new Pessoa(
                    Integer.parseInt(binding.edtId.getText().toString()),
                    binding.spinnerTipo.getSelectedItem().toString(),
                    binding.edtNomePessoa.getText().toString(),
                    binding.edtEmail.getText().toString(),
                   "abc123@@",
                    binding.edtCpf.getText().toString()
            );
        }else{
            pessoa = new Pessoa(
                    binding.spinnerTipo.getSelectedItem().toString(),
                    binding.edtNomePessoa.getText().toString(),
                    binding.edtEmail.getText().toString(),
                    "abc123@@",
                    binding.edtCpf.getText().toString()
            );
        }

        Log.w("PessoaMontada",pessoa.toString());
        return pessoa;
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