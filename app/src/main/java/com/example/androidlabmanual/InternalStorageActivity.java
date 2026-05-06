package com.example.androidlabmanual;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalStorageActivity extends AppCompatActivity {

    EditText editFileName, editContent;
    Button btnSave, btnLoad;
    TextView textContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_internal_storage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editFileName = findViewById(R.id.editFileName);
        editContent = findViewById(R.id.editContent);
        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);
        textContent = findViewById(R.id.textContent);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = editFileName.getText().toString();
                String content = editContent.getText().toString();

                if (fileName.isEmpty() || content.isEmpty()) {
                    Toast.makeText(InternalStorageActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                try (FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE)) {
                    fos.write(content.getBytes());
                    Toast.makeText(InternalStorageActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                    editContent.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(InternalStorageActivity.this, "Error saving file", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = editFileName.getText().toString();

                if (fileName.isEmpty()) {
                    Toast.makeText(InternalStorageActivity.this, "Enter filename to load", Toast.LENGTH_SHORT).show();
                    return;
                }

                try (FileInputStream fis = openFileInput(fileName)) {
                    StringBuilder sb = new StringBuilder();
                    int i;
                    while ((i = fis.read()) != -1) {
                        sb.append((char) i);
                    }
                    textContent.setText("File Content: " + sb.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(InternalStorageActivity.this, "File not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}