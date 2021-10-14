package com.avaca.tp2_lab3.ui.registro;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.avaca.tp2_lab3.R;
import com.avaca.tp2_lab3.modelo.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private RegistroActivityViewModel viewModel;
    private TextView tvAviso;
    private EditText etMail, etPass, etDni, etNombre, etApellido;
    private Button btGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);

        inicializarViews();

        viewModel.getAvisoMutable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAviso.setText(s);
            }
        });

        viewModel.getAvisoVisibilityMutable().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {
                tvAviso.setVisibility(visibility);
            }
        });
    }

    private void inicializarViews() {
        btGuardar = findViewById(R.id.btRegGuardar);
        etMail = findViewById(R.id.etRegMail);
        etPass = findViewById(R.id.etRegPass);
        etDni = findViewById(R.id.etRegdni);
        etNombre = findViewById(R.id.etRegNom);
        etApellido = findViewById(R.id.etRegAp);
        tvAviso = findViewById(R.id.tvRegAviso);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario u = new Usuario(
                        Integer.parseInt(etDni.getText().toString()),
                        etNombre.getText().toString(),
                        etApellido.getText().toString(),
                        etMail.getText().toString(),
                        etPass.getText().toString()
                );
                viewModel.Guardar(u);
            }
        });
    }
}