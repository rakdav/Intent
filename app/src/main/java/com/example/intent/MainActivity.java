package com.example.intent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText personText;
    private Button buttonMessage;
    private Button buttonEmail;
    private Button buttonPhone;
    public static final String MESSAGE="message";
    public static final String EMAIL="email";
    public static final String PHONE="phone";
    private String email;
    private String phone;
    ActivityResultLauncher<Intent> startForResult=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent=result.getData();
                    if(result.getResultCode()== Activity.RESULT_OK)
                    {
                        email=intent.getStringExtra(EMAIL);
                        phone=intent.getStringExtra(PHONE);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personText=findViewById(R.id.editTextTextPersonName);
        buttonMessage=findViewById(R.id.button);
        buttonEmail=findViewById(R.id.button2);
        buttonPhone=findViewById(R.id.button3);
        buttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MessageActivity.class);
                intent.putExtra(MESSAGE,personText.getText().toString());
                startForResult.launch(intent);
            }
        });
        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEmail = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
                String subject="Hi, how are you!";
                String content ="This is my test email";
                String[] recipients=new String[]{email};
                intentEmail.setType("text/plain");
                startActivity(Intent.createChooser(intentEmail, "Choose an email client from..."));
            }
        });
        buttonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEmail = new Intent(Intent.ACTION_CALL);
                intentEmail.setData(Uri.parse("tel:"+phone));
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    return;
                }
                startActivity(intentEmail);
            }
        });

    }

}