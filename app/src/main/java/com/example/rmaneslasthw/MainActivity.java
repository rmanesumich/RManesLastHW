package com.example.rmaneslasthw;
//Worked on by Ryan Manes and Catherine Bruns
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextBirdName, editTextZip, editTextName;
    Button buttonReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBirdName = findViewById(R.id.editTextBirdName);
        editTextZip = findViewById(R.id.editTextZip);
        editTextName = findViewById(R.id.editTextName);

        buttonReport = findViewById(R.id.buttonReport);

        buttonReport.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itemReport) {
            Intent HomeIntent = new Intent(this, MainActivity.class);
            startActivity(HomeIntent);
        } else if (item.getItemId() == R.id.itemSearch){
            Intent SettingsIntent = new Intent(this, SearchActivity.class);
            startActivity(SettingsIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Birds");

        if (v == buttonReport){
            String PersonName = editTextName.getText().toString();
            String ZipCode = editTextZip.getText().toString();
            String BirdName = editTextBirdName.getText().toString();

            BirdSightings newSighting = new BirdSightings (PersonName, ZipCode, BirdName);

            myRef.push().setValue(newSighting);

            Toast.makeText(this, "Sighting reported", Toast.LENGTH_SHORT).show();
        }
    }
}
