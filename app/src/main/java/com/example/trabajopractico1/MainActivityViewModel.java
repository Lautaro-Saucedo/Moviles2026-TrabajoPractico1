package com.example.trabajopractico1;
import android.app.Application;
import android.text.Editable;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<String> valor;
    private MutableLiveData<String> sDolares,sEuros;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<String> getDolares() {
        if(sDolares==null){
            sDolares = new MutableLiveData<>();
        }
        return sDolares;
    }
    public MutableLiveData<String> getEuros() {
        if(sEuros==null){
            sEuros = new MutableLiveData<>();
        }
        return sEuros;
    }
    public void setValor(String s){
        valor.setValue(s);
    }
    public LiveData<String> getValor(){
        if (valor==null){
            valor = new MutableLiveData<>();
        }
        return valor;
    }
    public void cambiarConversion(boolean dolarBoolean, CharSequence razonDeCambio, Editable dolares, Editable euros){
        if (dolares.toString().isEmpty() && euros.toString().isEmpty()){
            Toast.makeText(getApplication().getApplicationContext(),
                    "Introduzca valor en el campo",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        double dDolares, dEuros;
        String sRazon = razonDeCambio.toString().substring(0,razonDeCambio.length()-4);
        double razon = Double.parseDouble(sRazon);
        if(dolarBoolean){
            dEuros = Double.parseDouble(euros.toString());
            dDolares = dEuros *razon;
            sDolares.setValue(String.valueOf(dDolares));
        } else {
            dDolares = Double.parseDouble(dolares.toString());
            dEuros = dDolares /razon;
            sEuros.setValue(String.valueOf(dEuros));
        }
    }
}