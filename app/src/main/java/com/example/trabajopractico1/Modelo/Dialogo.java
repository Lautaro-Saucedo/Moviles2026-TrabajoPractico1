package com.example.trabajopractico1.Modelo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import java.util.function.Consumer;

public class Dialogo {
    public static void mostrar(Context context, Consumer<String> s) {
        EditText et = new EditText(context);
        et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        new AlertDialog.Builder(context)
                .setTitle("Cambiar valor")
                .setView(et)
                .setPositiveButton("Cambiar", (dialog, which) -> {
                    if (!et.getText().toString().isEmpty()) {
                        s.accept(et.getText().toString().concat(" EUR"));
                    } else {
                        Toast.makeText(context, "Valor no valido", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancelar", (dialog, which) ->
                        Toast.makeText(context, "Cancelado", Toast.LENGTH_LONG).show()
                )
                .show();
    }
}

