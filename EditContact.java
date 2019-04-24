package com.example.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class EditContact extends AppCompatActivity {
    EditText name,company,title,email,phone;
    Button save,cancle;
    MyDatabase db;
    ArrayList <Contact> arr;
    private static final int DELETE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        name=(EditText) findViewById(R.id.edt_fullname);
        company=(EditText)findViewById(R.id.edt_company);
        email=(EditText)findViewById(R.id.edt_email);
        title=(EditText)findViewById(R.id.edt_title);
        phone=(EditText)findViewById(R.id.edt_mobile);
        save=(Button)findViewById(R.id.btn_save);
        cancle=(Button)findViewById(R.id.btn_cancle);
        Intent intent=getIntent();
        db=new MyDatabase(this);
        final Contact data=(Contact) intent.getSerializableExtra("package");
        name.setText(data.getName()+"");
        company.setText(data.getCompany()+"");
        email.setText(data.getEmail()+"");
        title.setText(data.getTitle()+"");
        phone.setText(data.getMobile()+"");
        arr=new ArrayList<Contact>();
        Toast.makeText(this, data.getId()+"ss", Toast.LENGTH_SHORT).show();
       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent1=new Intent();
               Contact contact=new Contact(data.getId(), name.getText()+"",company.getText()+"",phone.getText()+"",title.getText()+"",email.getText()+"");
               intent1.putExtra("RETURN",contact);
               setResult(RESULT_OK,intent1);
               finish();

           }
       });
       cancle.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Contact contact=new Contact(data.getId(), data.getName()+"",data.getCompany()+"",data.getMobile()+"",data.getTitle()+"",data.getEmail()+"");
               db.deleteContact(contact);
               Intent intent2 = new Intent();
               intent2.putExtra("RETURN2","delecte");
               setResult(RESULT_CANCELED,intent2);
               finish();

           }
       });
    }
}