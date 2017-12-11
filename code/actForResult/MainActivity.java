package com.example.startactforresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtV_MessageSurname;
    private TextView txtV_MessageName;

    private final static int NAME = 0;
    private final static int SURNAME = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtV_MessageName = (TextView) findViewById(R.id.txtV_Name);
        txtV_MessageSurname = (TextView) findViewById(R.id.txtV_Surname);
    }

    public void onClick_Main(View v) {
        switch (v.getId()) {
            case R.id.btn_SecondActivityName:
                pulsaName();
                break;
            case R.id.btn_SecondActivitySurname:
                pulsaSurname();
                break;
        }
    }

    private void pulsaName() {
        Intent i = new Intent(MainActivity.this, SecondActivity.class);
        startActivityForResult(i, MainActivity.NAME);
    }

    private void pulsaSurname()
    {
        Intent i = new Intent(MainActivity.this, SecondActivity.class);
        startActivityForResult(i, MainActivity.SURNAME);
    }

    /**
     * Es un método callback que será llamado cuando SecondActivity finalice
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Comprobar código de petición
        // Comprobar resultado de operacion
        String msg;

        switch (requestCode) {
            case NAME:
                if (resultCode == RESULT_OK) {
                    msg = data.getExtras().getString("msg");
                    txtV_MessageName.setText(msg);
                }
                break;
            case SURNAME:
                if (resultCode == RESULT_OK) {
                    msg = data.getExtras().getString("msg");
                    txtV_MessageSurname.setText(msg);
                }
                break;
        }
    }
}
