package com.example.uiadil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText customerId,comments;
    private Spinner sp;
    private RadioGroup rg;
    private RadioButton privileged,nonPrivileged;
    private RatingBar ratingBar;
    private Button Submit;
    private SQLiteDatabase db;
    private String spinVal,name,type;
    private String gender[] = {"male","female","others"};
    float ser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
customerId = (EditText)findViewById(R.id.editText);
comments = (EditText)findViewById(R.id.editText2);
sp = (Spinner)findViewById(R.id.spinner);
privileged = (RadioButton)findViewById(R.id.radioButton);
nonPrivileged =(RadioButton)findViewById(R.id.radioButton2);
ratingBar = (RatingBar)findViewById(R.id.ratingBar);
rg = (RadioGroup)findViewById(R.id.radioGroup);
Submit = (Button)findViewById(R.id.button);
db = openOrCreateDatabase("CustomerDb", Context.MODE_PRIVATE,null);
db.execSQL("CREATE  TABLE IF NOT EXISTS customer(customerId INT,gender VARCHAR,Type of customer VARCHAR, Service-feedback FLOAT,comments VARCHAR)");




sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinVal= gender[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
final ArrayAdapter<String> Spin_adapter = new ArrayAdapter<String>(MainActivity.this,
        android.R.layout.simple_spinner_item,gender);
sp.setAdapter(Spin_adapter);
rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(privileged.isChecked() == true){
            type="privilege";
        }
        if(nonPrivileged.isChecked() == true){
            type = "non-privileged";
        }
    }
});

ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        ser = rating;
    }
});


Submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String id =customerId.getText().toString();
        String c = comments.getText().toString();

        if(id.isEmpty() || c.isEmpty() || spinVal.isEmpty() || type.isEmpty()){
            Toast.makeText(MainActivity.this,"enter all the details",Toast.LENGTH_SHORT).show();
        }else db.execSQL("INSERT INTO customer VALUES(''+id+'',''+spinVal+'',''+type+'',''+ser+'',''+comments+'')");
        Toast.makeText(MainActivity.this,"Successfully added to database",Toast.LENGTH_SHORT).show();

        startActivity(new Intent(MainActivity.this,MainActivity2.class));
    }


});
    }


}