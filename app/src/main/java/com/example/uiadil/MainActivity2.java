package com.example.uiadil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private Button viewFromDb;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        viewFromDb = (Button)findViewById(R.id.button3);
        db=openOrCreateDatabase("customerDb", Context.MODE_PRIVATE,null);

        viewFromDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT * FROM customer", null);
                if (c.getCount() == 0) {
                    Toast.makeText(MainActivity2.this, "ERROR!!!NO RECORDS FOUND", Toast.LENGTH_LONG).show();
                    return ;
                }
                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()) {
                    buffer.append("customerId:" + c.getString(0) + "\n");
                    buffer.append("gender:" + c.getString(1) + "\n");
                    buffer.append("Type of customer:" + c.getString(2) + "\n");
                    buffer.append("Service feedback:" + c.getString(3) + "\n");
                    buffer.append("comment:" + c.getString(0) + "\n");

                }

            }

        });

    }
    public void ShowMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}