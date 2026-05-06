package com.example.androidlabmanual;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ContentProviderActivity extends AppCompatActivity {

    private static final int PERMISSION_CODE = 101;
    private ListView listData;
    private Button btnFetchContacts, btnFetchCallLogs;
    private ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listData = findViewById(R.id.listData);
        btnFetchContacts = findViewById(R.id.btnFetchContacts);
        btnFetchCallLogs = findViewById(R.id.btnFetchCallLogs);
        dataList = new ArrayList<>();

        btnFetchContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ContentProviderActivity.this,
                        Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                    getContactList();
                } else {
                    ActivityCompat.requestPermissions(ContentProviderActivity.this,
                            new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_CODE);
                }
            }
        });

        btnFetchCallLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ContentProviderActivity.this,
                        Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
                    getCallLogs();
                } else {
                    ActivityCompat.requestPermissions(ContentProviderActivity.this,
                            new String[]{Manifest.permission.READ_CALL_LOG}, PERMISSION_CODE + 1);
                }
            }
        });
    }

    private void getContactList() {
        dataList.clear();
        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor cursor = contentResolver.query(uri, projection, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                dataList.add("Contact: " + name + " [" + number + "]");
            }
            cursor.close();
        }
        updateListView();
    }

    private void getCallLogs() {
        dataList.clear();
        ContentResolver contentResolver = getContentResolver();
        Uri uri = CallLog.Calls.CONTENT_URI;
        String[] projection = {CallLog.Calls.NUMBER, CallLog.Calls.TYPE, CallLog.Calls.DURATION};

        Cursor cursor = contentResolver.query(uri, projection, null, null, CallLog.Calls.DATE + " DESC");

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String number = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.TYPE));
                String duration = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.DURATION));
                
                String dir = null;
                int dircode = Integer.parseInt(type);
                switch (dircode) {
                    case CallLog.Calls.OUTGOING_TYPE: dir = "OUTGOING"; break;
                    case CallLog.Calls.INCOMING_TYPE: dir = "INCOMING"; break;
                    case CallLog.Calls.MISSED_TYPE: dir = "MISSED"; break;
                }
                dataList.add("Call: " + number + " (" + dir + ") Duration: " + duration + "s");
            }
            cursor.close();
        }
        updateListView();
    }

    private void updateListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listData.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContactList();
            } else {
                Toast.makeText(this, "Permission Denied for Contacts", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PERMISSION_CODE + 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCallLogs();
            } else {
                Toast.makeText(this, "Permission Denied for Call Logs", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
