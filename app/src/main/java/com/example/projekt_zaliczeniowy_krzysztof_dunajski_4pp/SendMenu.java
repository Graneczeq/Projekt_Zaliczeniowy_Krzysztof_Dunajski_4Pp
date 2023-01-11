package com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SendMenu extends AppCompatActivity {
    public static final String TAG = "SMS611";
    public static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    public static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 2;
    private static final String SEND_SMS = "SMS";
    SmsManager smsManager;
    String destinationAddress = "";
    String text = "";
    EditText phoneNumber;
    EditText personalInfo;
    Button sendSms;
    Button sendEmail;
    TextView order_check;
    String orderinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        sendSms = findViewById(R.id.button);
        sendEmail = findViewById(R.id.button_email);
        phoneNumber = findViewById(R.id.editText);
        order_check = findViewById(R.id.ordercheck);
        personalInfo = findViewById(R.id.editText3);

        sendSms.setEnabled(true);
        if (checkPermission(Manifest.permission.SEND_SMS)){
            sendSms.setEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
        Intent intent = getIntent();
            orderinfo = intent.getStringExtra("orderinfo");
            order_check.setText(orderinfo);
            Toast.makeText(this,orderinfo, Toast.LENGTH_SHORT).show();
        sendSms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendWithSmsManager();
            }
        });

        sendEmail.setOnClickListener(new View.OnClickListener() {
            String email_value = "";
            public void onClick(View v) {
                email_value = personalInfo +" "+ orderinfo;
                sendEmail(email_value);
            }
        });
    }
    private void sendWithSmsManager(){
        if (checkPermission(Manifest.permission.SEND_SMS)){
            destinationAddress = phoneNumber.getText().toString();
            text = destinationAddress + order_check.getText().toString();
            if (!destinationAddress.equals("")&& !text.equals("")){
                smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(
                        destinationAddress, null, text, null, null
                );
                Toast.makeText(SendMenu.this,"SMS został wysłany", Toast.LENGTH_SHORT).show();
                Log.v(TAG, "SMS został wysłany");
            } else {
                Toast.makeText(SendMenu.this,"SMS nie został wysłany", Toast.LENGTH_SHORT).show();
                Log.v(TAG, "SMS nie został wysłany");
            }
        }else {
            Toast.makeText(SendMenu.this, "SMS nie został wysłany", Toast.LENGTH_SHORT).show();
            Log.v(TAG, "SMS nie został wysłany");
        }
    }

    private boolean checkPermission(String perm) {
        int check = ContextCompat.checkSelfPermission(this, perm);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    private void sendEmail(String mailBody){
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setData(Uri.parse("mailto:"));
        mailIntent.putExtra(Intent.EXTRA_EMAIL, "login@example.com");
        mailIntent.putExtra(Intent.EXTRA_SUBJECT,"ZAMÓWIENIE");
        mailIntent.putExtra(Intent.EXTRA_TEXT,mailBody);
        startActivity(mailIntent);
    }


}
