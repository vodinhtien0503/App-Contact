package com.example.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

//import android.support.design.widget.FloatingActionButton;

public class MainActivity extends AppCompatActivity    {
   // ListView lv;
    ArrayList<String> listContact;
    ArrayList<Contact> contactArrayList;
    ContactAdapter adapter;
    RecyclerView recyclerView;
    MyDatabase db;
   // FloatingActionButton BtnAdd;
     private static final int REQ_CODE=1;
     private  static final int DELETE=1;
     int vitri=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        //BtnAdd = (FloatingActionButton) findViewById(R.id.fab);
        listContact = new ArrayList<String>();
        contactArrayList = new ArrayList<Contact>();
        db=new MyDatabase(this);
      //Contact contact1 =new Contact(1,"Nguyễn Văn A","Công ty A","012345678","MR","trumyeu216@gmail.com");
      //Contact contact2=new Contact(2,"Nguyễn Văn B","Công ty B","0123456789","Ms","trumyeu218@gamil.com");
       //db.addContact(contact1);
       //db.addContact(contact2);
        contactArrayList=db.getAllContacts();
        if(contactArrayList.size()>0) {
            adapter = new ContactAdapter(contactArrayList, MainActivity.this);
            adapter.setOnClickItemListner(new OnClickItemListner() {
                @Override
                public void OnClickedItem(int position) {
                    Intent intent = new Intent(MainActivity.this, EditContact.class);
                    String item = contactArrayList.get(position).getName();
                    Contact getContact = findContact(item);
                    intent.putExtra("package", getContact);
                    startActivityForResult(intent, REQ_CODE);
                    vitri = position;
                    adapter.notifyDataSetChanged();

                }
            });

        }
        else {
            Toast.makeText(this, "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
        }
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public Contact findContact(String name) {
        for (Contact contact : contactArrayList) {
            if (name.equals(contact.getName())) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public void onActivityResult(int request, int resulted, @Nullable Intent data) {
        super.onActivityResult(request, resulted, data);
        if (request == REQ_CODE && resulted == RESULT_OK) {
            Contact contact = (Contact) data.getSerializableExtra("RETURN");
            if(contactArrayList.size()>0) {
                contactArrayList.set(vitri, contact);
            }
            else {
                Toast.makeText(this, "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
            }
            adapter.notifyDataSetChanged();


        }
        if(request==REQ_CODE&&resulted==RESULT_CANCELED){
                contactArrayList.remove(vitri);
        }
    }

}

