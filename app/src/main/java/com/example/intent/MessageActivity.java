package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {
    private TextView namePerson;
    private EditText email;
    private EditText phone;
    private Button Ok;
    private Button Cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        namePerson=findViewById(R.id.textView);
        email=findViewById(R.id.editTextTextEmailAddress);
        phone=findViewById(R.id.editTextPhone);
        Ok=findViewById(R.id.button5);
        Cancel=findViewById(R.id.button6);
        Bundle arguments=getIntent().getExtras();
        if(arguments!=null)
        {
            namePerson.setText(arguments.getString(MainActivity.MESSAGE));
        }
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data=new Intent();
                data.putExtra(MainActivity.EMAIL,email.getText().toString());
                data.putExtra(MainActivity.PHONE,phone.getText().toString());
                setResult(RESULT_OK,data);
                finish();
            }
        });
    }
}