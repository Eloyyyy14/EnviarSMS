package com.example.elo14.enviarsms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNombreOrigen, etNombreDestino, etNumeroTelefono, etMensaje;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombreOrigen=(EditText)findViewById(R.id.etNombreOrigen);
        etNombreDestino=(EditText)findViewById(R.id.etNombreDestino);
        etNumeroTelefono=(EditText)findViewById(R.id.etNumeroTelefono);
        etMensaje=(EditText)findViewById(R.id.etMensaje);
        btnEnviar=(Button)findViewById(R.id.btnEnviar);
    }

    public void enviarMensaje(View v){
        String numTelefono=etNumeroTelefono.getText().toString();
        String datosCompletos="Origen: "+etNombreOrigen.getText().toString()+" Destino: "+etNombreDestino.getText().toString()
                +" Mensaje: "+etMensaje.getText().toString();
        //Verificar si tenemos los perimisos necesarios para enviar SMS
        try{
            int permisoEnvioSMS= ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
            if(permisoEnvioSMS != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(), "No hay perimiso para enviar SMS", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 225);
            }

            SmsManager sms=SmsManager.getDefault();
            sms.sendTextMessage(numTelefono, null, datosCompletos, null, null);
            Toast.makeText(getApplicationContext(), "Mensaje enviado", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Mensaje no enviado verifique perimisos o datos" + e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }

    }
}
