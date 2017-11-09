package arias.jenifer.sumasrestas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SumasRestasActivity extends AppCompatActivity {

    EditText result;
    Button btn_nuevasuma;
    TextView num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumas_restas);

        result = (EditText) findViewById(R.id.result);
        btn_nuevasuma = (Button) findViewById(R.id.btn_nuevasuma);
        num1 = (TextView) findViewById(R.id.num1);
        num2 = (TextView) findViewById(R.id.num2);

        nuevaSuma();

    }

    public void suma (View v) {
        nuevaSuma();
    }

    private void nuevaSuma() {
        int random_num1 = (int)  Math.floor(Math.random() * 10);
        int random_num2 = (int)  Math.floor(Math.random() * 10);

        while (random_num1 + random_num2 >= 10) {
            random_num2 = (int)  Math.floor(Math.random() * 10);
        }
        num1.setText(String.valueOf(random_num1));
        num2.setText(String.valueOf(random_num2));
    }


    public void clicat (View v){
        Button boton = (Button) v;
        String num = boton.getText().toString();
        result.setText(num);
        result.setSelection(result.length());
    }

    public void clicat_borrar (View v) {
        String numeros = result.getText().toString();
        if (numeros.length() > 0) {
            String actual = numeros.substring(0,result.length()-1);
            result.setText(actual);
            result.setSelection(result.length());
        }
    }
    
    public void check (View v) {
        int num_1 = Integer.parseInt(num1.getText().toString());
        int num_2 = Integer.parseInt(num2.getText().toString());
        int suma = num_1 + num_2;
        String resultadoS = result.getText().toString();

        if (!resultadoS.isEmpty()) {
             int resultado = Integer.parseInt(result.getText().toString());

             if (suma == resultado) {
                Toast.makeText(this, "Muy bien!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Inténtalo de nuevo...", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Inserta un número...", Toast.LENGTH_SHORT).show();
        }
    }


}
