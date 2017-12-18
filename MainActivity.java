package com.tarea.erick.calcled;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Math.abs;


public class MainActivity extends AppCompatActivity {


    float ledV=0, ledVs=0,ledVf=0, ledRv=0, ledIf=0;
    String lw="";


    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText Vs = (EditText) findViewById(R.id.Vs);
        final EditText Vf = (EditText) findViewById(R.id.Vf);
        final EditText mA = (EditText) findViewById(R.id.mA);
        final EditText Ohms = (EditText) findViewById(R.id.Ohms);
        final EditText w = (EditText) findViewById(R.id.w);
        final Button ok = (Button) findViewById(R.id.ok);
        final Button clean = (Button) findViewById(R.id.clean);






        Vs.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        Ohms.setEnabled(false);
        w.setEnabled(false);

        if(!mA.getText().toString().isEmpty()&& !Ohms.getText().toString().isEmpty()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }

        mA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    //obtener valores de los textbox y pasarlos a las variables convertidos en Double
                    if(!Vs.getText().toString().isEmpty()
                            && !Vf.getText().toString().isEmpty()
                                && !mA.getText().toString().isEmpty()
                            )
                    {


                        ledVs = Float.parseFloat(Vs.getText().toString());
                        ledVf = Float.parseFloat(Vf.getText().toString());
                        ledIf = Float.parseFloat(mA.getText().toString());

                        ledV = ledVs - ledVf;
                        ledRv = ledV / (ledIf/1000);
                        lw = Float.toString((ledV * ledIf)/1000);

                        Ohms.setText(format(ledRv));

                        w.setText(lw.concat(" W"));


                        //ocultar teclado

                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(Ohms.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                        imm.hideSoftInputFromWindow(w.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                        w.requestFocus();
                        Ohms.setEnabled(true);
                        w.setEnabled(true);

                    }


                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"\nEl campo esta vacio "+e.getMessage(),Toast.LENGTH_LONG).show();
                }



            }
        });


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                        ledVs = Float.parseFloat(Vs.getText().toString());
                        ledVf = Float.parseFloat(Vf.getText().toString());
                        ledIf = Float.parseFloat(mA.getText().toString());

                        ledV = ledVs-ledVf;
                        ledRv = ledV/(ledIf/1000);
                        lw = Float.toString((ledV * ledIf)/1000);

                        Ohms.setText(format(ledRv));
                        w.setText(lw.concat(" W"));

                    //ocultar teclado
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(Ohms.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                        imm.hideSoftInputFromWindow(w.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                        Vs.requestFocus();
                        Ohms.setEnabled(true);
                        w.setEnabled(true);




                }catch (NumberFormatException e){
                    //Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"\nEl campo esta vacio "+e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vs.getText().clear();
                Vf.getText().clear();
                mA.getText().clear();
                Ohms.getText().clear();
                w.getText().clear();
                Vs.requestFocus();
                Toast.makeText(getApplicationContext(),"Limpio",Toast.LENGTH_SHORT).show();


            }
        });




    }




    String format(float value){
        if(value == 0) return "0";
            if(value < 0){
                 abs(value);
            }
            if(value<1.0E-9){
                value*=1000000000000L;
            return Float.toString(value)+"n Ω";
            }
            else if(value<1.0E-6){
                value*=1000000000;
                return Float.toString(value)+"u Ω";
            }
            else if(value<0.0010){
                value*=1000000;
                return Float.toString(value)+"m Ω";
            }
            else if(value<1){
                value*=1000;
                return Float.toString(value)+" Ω";
            }
            else if(value>=1000000){
                value/=1000000;
                return Float.toString(value)+"M Ω";
            }
            else if(value>=1000){
                value/=1000;
                return Float.toString(value)+"k Ω";
            }
            else{
                return Float.toString(value)+"k Ω";
        }

    }


}

