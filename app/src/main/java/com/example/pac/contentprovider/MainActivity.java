package com.example.pac.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn_add_name);
        button2= (Button) findViewById(R.id.btn_retrivie_student);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(StudentsProvider.NAME,((EditText)findViewById(R.id.name)).getText().toString());

                values.put(StudentsProvider.GRADE,((EditText) findViewById(R.id.grade)).getText().toString());

                Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI, values);

                Toast.makeText(getApplicationContext(), uri.toString(), Toast.LENGTH_LONG).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "content://com.example.pac.contentprovider";
                Uri students = Uri.parse(URL);
                Cursor c = managedQuery(students, null,null,null, "name");

                if (c.moveToFirst()){
                    do{
                        Toast.makeText(getApplicationContext(),c.getString(c.getColumnIndex(StudentsProvider._ID))+ ", "
                                + c.getString(c.getColumnIndex(StudentsProvider.NAME))+
                                ", " + c.getString(c.getColumnIndex(StudentsProvider.GRADE)),Toast.LENGTH_SHORT).show();
                    }while (c.moveToNext());
                }
            }
        });
    }
}
