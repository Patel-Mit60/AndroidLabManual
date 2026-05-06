package com.example.androidlabmanual;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidlabmanual.Data.MyDbHandler;
import com.example.androidlabmanual.model.Contact;

import java.util.List;

public class Db extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_db);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MyDbHandler db = new MyDbHandler(Db.this);

        // Creating contacts to insert
        Contact mit = new Contact();
        mit.setPhoneNumber("9090909090");
        mit.setName("Mitu");
        db.addContact(mit);

        Contact ak = new Contact();
        ak.setPhoneNumber("9090909090");
        ak.setName("Ayush");
        db.addContact(ak);
        ak.setId(17);
        ak.setName("Changed Ayush");
        ak.setPhoneNumber("9999999888999");
        int affectedRows = db.updateContact(ak);

        Log.d("dbmit","No of affected rows are: "+ affectedRows);
//        Log.d("dbmit", "mitu and ayush are successfully added to db");

        List<Contact> allContact = db.getAllContacts();
        for (Contact contact: allContact){
            Log.d("Dbmit","Id: " + contact.getId() + "\n" +
                                    "Phone Number:" + contact.getPhoneNumber() + "\n" +
                                    "Name:" + contact.getName() + "\n");

        }
    }
}