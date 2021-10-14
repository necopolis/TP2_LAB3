package com.avaca.tp2_lab3.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.avaca.tp2_lab3.R;


public class MainActivity extends AppCompatActivity {

    private TextView tvAviso;
    private EditText etMail, etPass;
    private Button btLogin, btRegistrarse;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        inicializarViews();

        viewModel.getAviso().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAviso.setText(s);
            }
        });

        viewModel.getAvisoVisibility().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {
                tvAviso.setVisibility(visibility);
            }
        });

    }

    private void inicializarViews() {
        etMail = findViewById(R.id.etMail);
        etPass = findViewById(R.id.etPass);
        btLogin = findViewById(R.id.btIngresar);
        btRegistrarse = findViewById(R.id.btRegistrarse);
        tvAviso = findViewById(R.id.tvAviso);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.Login(etMail.getText().toString(), etPass.getText().toString());
            }
        });

        btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.Registrarse();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        etMail.setText("");
        etPass.setText("");
        tvAviso.setVisibility(View.INVISIBLE);
    }
}