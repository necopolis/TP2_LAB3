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

public class EditarActivity extends AppCompatActivity {

    private RegistroActivityViewModel viewModel;
    private TextView tvAviso;
    private EditText etMail, etPass, etDni, etNombre, etApellido;
    private Button btGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
                .create(RegistroActivityViewModel.class);

        inicializarViews();

        viewModel.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario u) {
                etDni.setText(String.valueOf(u.getDni()));
                etApellido.setText(u.getApellido());
                etNombre.setText(u.getNombre());
                etMail.setText(u.getMail());
                etPass.setText(u.getPassword());
            }
        });

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

        viewModel.setUsuario();
    }

    private void inicializarViews() {
        btGuardar = findViewById(R.id.btRegGuardar);
        btGuardar.setText("Editar");

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
                viewModel.Editar(u);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvAviso.setVisibility(View.INVISIBLE);
    }
}