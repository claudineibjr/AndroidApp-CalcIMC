package com.claudineibjr.calculaimc;

//region Imports
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.valueOf;
//endregion

public class MainActivity extends AppCompatActivity {

    //region Propriedades do layout
    EditText txtPeso;       // EditText onde será digitado o peso da pessoa
    EditText txtAltura;     // EditText onde será digitado a altura da pessoa
    TextView txtIMC;        // TextView onde será exibido o IMC da pessoa
    TextView txtResultado;  // TextView onde será exibido o resultado em texto do IMC da pessoa

    Button btnCalcular;     // Botão que será acionado para calcular o IMC da pessoa
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instanciaElementosInterface();
    }

    private void instanciaElementosInterface() {
        //region Recuperação dos elementos da interface
        txtPeso = (EditText) findViewById(R.id.txtPeso);
        txtAltura = (EditText) findViewById(R.id.txtAltura);
        txtIMC = (TextView) findViewById(R.id.txtIMC);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        //endregion

        //region Ação de clique no botão calcular
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtPeso.getText().toString().length() == 0 || txtAltura.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "Deve ser digitado um peso e uma altura", Toast.LENGTH_LONG).show();
                    return;
                }

                float peso = Float.valueOf(txtPeso.getText().toString());
                float altura =  Float.valueOf(txtAltura.getText().toString());

                if (peso <= 0 || altura <= 0) {
                    Toast.makeText(MainActivity.this, "O peso e a altura devem ser maiores do que 0", Toast.LENGTH_LONG).show();
                    return;
                }

                float IMC = calculaIMC(peso, altura);
                txtIMC.setText(valueOf(IMC));

                String classificacao = "";

                if (IMC < 18.5)
                    classificacao = "Magreza";
                else if (IMC >= 18.5 && IMC < 24.9)
                    classificacao = "Normal";
                else if (IMC >= 24.9 && IMC < 29.9)
                    classificacao = "Sobrepeso";
                else if (IMC >= 29.9 && IMC < 39.9)
                    classificacao = "Obesidade";
                else if (IMC >= 39.9)
                    classificacao = "Obesidade grave";

                txtResultado.setText(valueOf(classificacao));
            }
        });
        //endregion
    }

    //region Cálculo do IMC
    private float calculaIMC(float peso, float altura){
        return peso/(altura*altura);
    }
    //endregion
}
