package arias.jenifer.sumasrestas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SumasRestasActivity extends AppCompatActivity {

    EditText result_dig1, result_dig2;
    Button btn_nuevasuma;
    TextView num1_dig1, num2_dig1, num1_dig2, num2_dig2;
    int[] opciones = new int[2];                                    //Todas las posibles sumas sin acarreo Nivel 1
    int[] niveles = new int[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumas_restas);

        result_dig1 = (EditText) findViewById(R.id.result_dig1);
        result_dig2 = (EditText) findViewById(R.id.result_dig2);
        btn_nuevasuma = (Button) findViewById(R.id.btn_nuevasuma);

        //TODO: Añadir un tercer dígito a cada número: num1_dig3, num2_dig3
        num1_dig1 = (TextView) findViewById(R.id.num1_dig1);
        num2_dig1 = (TextView) findViewById(R.id.num2_dig1);
        num1_dig2 = (TextView) findViewById(R.id.num1_dig2);
        num2_dig2 = (TextView) findViewById(R.id.num2_dig2);

        nuevaSuma();                                                // Método para generar nuevas sumas
        generarPosiciones();
        Log.i("generar", String.valueOf(opciones[0]));
        Log.i("generar", String.valueOf(opciones[1]));

  /*      int i=0;
        while(i<10) {                                               //Prueba para la generación de posiciones/dígitos
            generarPosiciones();
            Log.i("jenn", String.valueOf(opciones[0]));
            Log.i("jenn", String.valueOf(opciones[1]));
            i++;
        } */
/*
        for (int j = 0; j<2; j++) {
            switch (j) {
                case 0:

                case 1:

            }
        }
*/
    }

    private void generarPosiciones () {                                 //Método para generar posiciones de la suma
        int random_digito1 = (int)  Math.floor(Math.random() * 4);       //Generación del primer número: de 1 a 3 dígitos
        opciones[0] = random_digito1;
        int random_digito2;
        while (random_digito1 == 0) {                                    //Si el primer número es 0, vuelve a generarlo hasta que salga de 1 a 3.
            random_digito1 = (int)  Math.floor(Math.random() * 4);
            opciones[0] = random_digito1;
        }

        if (random_digito1 == 1) {                                       //Si el primer número tiene 1 dígito, el segundo número tendrá de 1 a 3.
            num1_dig1.setVisibility(View.VISIBLE);
            num1_dig2.setVisibility(View.INVISIBLE);
            random_digito2 = (int)  Math.floor(Math.random() * 4);
            opciones[1] = random_digito2;

            while (random_digito2 == 0) {                               //Si el segundo número es 0,
                random_digito2 = (int)  Math.floor(Math.random() * 4);  //vuelve a generarlo hasta que salga de 1 a 3.
                opciones[1] = random_digito2;
            }

            if(random_digito2 == 1) {
                num2_dig1.setVisibility(View.VISIBLE);
                num2_dig2.setVisibility(View.INVISIBLE);
                result_dig2.setVisibility(View.INVISIBLE);
            } else if (random_digito2 == 2) {
                num2_dig1.setVisibility(View.VISIBLE);
                num2_dig2.setVisibility(View.VISIBLE);
            }
        }

        else {                                                          //En cualquier otro caso el primer número tiene 2 o 3 dígitos, por lo tanto el segundo número tendrá 1 dígito.
            if(random_digito1 == 2) {
                random_digito2 = (int)  Math.floor(Math.random() * 1);
                opciones[1] = random_digito2;
                num1_dig2.setVisibility(View.VISIBLE);
                num2_dig2.setVisibility(View.INVISIBLE);
            }
        }

       // opciones[0] = random_digito1;
       // opciones[1] = random_digito2;

    }

    public void suma (View v) {
        nuevaSuma();
    }

    private void nuevaSuma() {
        generarPosiciones();// Método para generar nuevas sumas
        Log.i("generar", String.valueOf(opciones[0]));
        Log.i("generar", String.valueOf(opciones[1]));
        int random_num1_dig1 = (int)  Math.floor(Math.random() * 10);       // Generación random de los dígitos, número del 0 al 9.
        int random_num1_dig2 = (int)  Math.floor(Math.random() * 10);
        int random_num2_dig1 = (int)  Math.floor(Math.random() * 10);
        int random_num2_dig2 = (int)  Math.floor(Math.random() * 10);

        while (random_num1_dig1 + random_num2_dig1 >= 10)  {                 // Si la suma de los dos dígitos es mayor o igual a 10, vuelve a generar el segundo número
            random_num2_dig1 = (int)  Math.floor(Math.random() * 10);
        }
        while (random_num1_dig2 + random_num2_dig2 >=10)  {                 // Si la suma de los dos dígitos es mayor o igual a 10, vuelve a generar el segundo número
            random_num2_dig2 = (int)  Math.floor(Math.random() * 10);
        }

        num1_dig1.setText(String.valueOf(random_num1_dig1));
        num1_dig2.setText(String.valueOf(random_num1_dig2));
        num2_dig1.setText(String.valueOf(random_num2_dig1));
        num2_dig2.setText(String.valueOf(random_num2_dig2));
    }

    // TODO: Insertar el resultado del dígito2 en result_dig2 cuando se clica un número
    public void clicat (View v){                                // Listener para los botones de los números
        Button boton = (Button) v;
        String num = boton.getText().toString();
        result_dig1.setText(num);
        result_dig1.setSelection(result_dig1.length());
    }

    public void clicat_borrar (View v) {                        //Método para borrar los números introducidos
        String numero1 = result_dig1.getText().toString();
        String numero2 = result_dig2.getText().toString();
        if (numero1.length() > 0) {
            String actual = numero1.substring(0, result_dig1.length()-1);
            result_dig1.setText(actual);
            result_dig1.setSelection(result_dig1.length());
        } else if (numero2.length() > 0) {
            String actual = numero2.substring(0, result_dig2.length()-1);
            result_dig2.setText(actual);
            result_dig2.setSelection(result_dig2.length());
        }
    }

    public void check (View v) {                                            //Método para comprobar el resultado
        int num_1_1 = Integer.parseInt(num1_dig1.getText().toString());
        int num_2_1 = Integer.parseInt(num2_dig1.getText().toString());
        int num_1_2 = Integer.parseInt(num1_dig2.getText().toString());
        int num_2_2 = Integer.parseInt(num2_dig2.getText().toString());

        int suma1 = num_1_1 + num_2_1;
        int suma2 = num_1_2 + num_2_2;

        String resultadoS = result_dig1.getText().toString();
        String resultadoS2 = result_dig2.getText().toString();

        if (!resultadoS.isEmpty() && !resultadoS2.isEmpty()) {                                                        //Si el resultado NO está vacio...
             int resultado1 = Integer.parseInt(result_dig1.getText().toString());
             int resultado2 = Integer.parseInt(result_dig2.getText().toString());

             if (suma1 == resultado1 && suma2 == resultado2) {
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
