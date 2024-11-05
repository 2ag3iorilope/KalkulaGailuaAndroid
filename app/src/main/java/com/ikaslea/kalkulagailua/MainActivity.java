package com.ikaslea.kalkulagailua;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText EditText1;
    EditText EditText2;
    RadioGroup RadioG;
    Button ButtonGarbitu;
    Button ButtonKalkulatu;
    Double emaitza;
    AlertDialog.Builder dialogoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText1 = findViewById(R.id.editTextText3);
        EditText2 = findViewById(R.id.editTextText2);
        ButtonGarbitu = findViewById(R.id.button);
        ButtonKalkulatu = findViewById(R.id.button2);
        RadioG = findViewById(R.id.Aukerak);

        ButtonKalkulatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditText1.getText().length() == 0) {
                    sacarDialogo("Lehen zenbakia sartu behar da");
                    EditText1.requestFocus();
                } else if (EditText2.getText().length() == 0) {
                    sacarDialogo("Bigarren Zenbakia sartu behar da");
                    EditText2.requestFocus();
                } else if (RadioG.getCheckedRadioButtonId() == -1) {
                    sacarDialogo("Aukeratu aukera bat");
                } else {
                    calcular();
                }
            }
        });

        ButtonGarbitu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText1.setText("");
                EditText2.setText("");
                EditText1.requestFocus();
                RadioG.clearCheck();
            }
        });
    }

    private void calcular() {
        double a = Double.parseDouble(EditText1.getText().toString());
        double b = Double.parseDouble(EditText2.getText().toString());

        int checkedId = RadioG.getCheckedRadioButtonId();
        if (checkedId == R.id.radioButton) {
            emaitza = Gehitu(a, b);
            sacarDialogo("Batuketaren Emaitza:" + emaitza);
        } else if (checkedId == R.id.radioButton2) {
            emaitza = Kendu(a, b);
            sacarDialogo("Kenketaren Erantzuna:" + emaitza);
        } else if (checkedId == R.id.radioButton3) {
            emaitza = Bidertu(a, b);
            sacarDialogo("Biderketaren Emaitza:" + emaitza);
        } else if (checkedId == R.id.radioButton4) {
            if (b == 0) {
                sacarDialogo("Ezin da zatiketa egin 0-rekin");
            } else {
                emaitza = Zatitu(a, b);
                sacarDialogo("Zatiketaren Emaitza:" + emaitza);
            }
        }
    }

    public double Gehitu(double a, double b) {
        return a + b;
    }

    public double Kendu(double a, double b) {
        return a - b;
    }

    public double Bidertu(double a, double b) {
        return a * b;
    }

    public double Zatitu(double a, double b) {
        return a / b;
    }

    public void sacarDialogo(String texto) {
        dialogoa = new AlertDialog.Builder(MainActivity.this);
        dialogoa.setTitle("Notifikazioak");
        dialogoa.setMessage(texto);
        dialogoa.setCancelable(false);
        dialogoa.setPositiveButton("Irten", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                dialogo.cancel();
            }
        });
        dialogoa.show();
    }
}
