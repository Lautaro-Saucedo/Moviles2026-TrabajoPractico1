package com.example.trabajopractico1;
import android.os.Bundle;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.trabajopractico1.Modelo.Dialogo;
import com.example.trabajopractico1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding b;
    private MainActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        b = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(b.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        //---------------OBSERVERS
        vm.getValor().observe(this, s ->
            b.tvConversion.setText(s)
        );
        vm.getDolares().observe(this, s -> b.etDolares.setText(s));
        vm.getEuros().observe(this, s -> b.etEuros.setText(s));
        
        //---------------LISTENERS
        b.btCambiar.setOnClickListener(v ->
            Dialogo.mostrar(MainActivity.this, s -> vm.setValor(s))
        );
        b.btConvertir.setOnClickListener(v ->
            vm.cambiarConversion(b.rbDolares.isChecked(),
                b.tvConversion.getText(),
                b.etDolares.getText(),
                b.etEuros.getText()
            )
        );
        b.rgConvertir.setOnCheckedChangeListener((RadioGroup group, int checkedId) -> {
            b.etEuros.setText("");
            b.etDolares.setText("");
        });
        b.rbDolares.setOnClickListener(v -> {
            b.etDolares.setEnabled(false);
            b.etEuros.setEnabled(true);
        });
        b.rbEuros.setOnClickListener(v -> {
            b.etEuros.setEnabled(false);
            b.etDolares.setEnabled(true);
        });
    }
}