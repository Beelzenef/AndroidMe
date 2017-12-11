package com.example.startactforresult;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    private EditText edT_textEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        edT_textEnviar = (EditText) findViewById(R.id.edT_textEnviar);
    }

    public void onClick_Second (View v)
    {
        switch (v.getId())
        {
            case R.id.btn_Cancelar:
                pulsaCancelar();
                break;
            case R.id.btn_EnviarTexto:
                pulsaEnviar();
                break;
        }
    }

    private void pulsaEnviar()
    {
        // Comprobar que edT no está vacío
        if (!TextUtils.isEmpty(edT_textEnviar.getText().toString()))
        {
            // Añadir mensaje a Intent
            Intent i = new Intent();
            i.putExtra("msg", edT_textEnviar.getText().toString());

            // Agregar resultado y finalizar Activity
            setResult(Activity.RESULT_OK, i);
            finish();
        }

    }

    private void pulsaCancelar()
    {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}
