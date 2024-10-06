package com.example.jibupost;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddEventActivity extends AppCompatActivity {

    private EditText editEventTitle, editEventDate, editEventTime, editEventLocation;
    private Button btnSaveEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        // Initialize views
        editEventTitle = findViewById(R.id.edit_event_title);
        editEventDate = findViewById(R.id.edit_event_date);
        editEventTime = findViewById(R.id.edit_event_time);
        editEventLocation = findViewById(R.id.edit_event_location);
        btnSaveEvent = findViewById(R.id.btn_save_event);

        // Save event button click listener
        btnSaveEvent.setOnClickListener(view -> {
            // Logic to save event (e.g., save to database or shared preferences)
            String title = editEventTitle.getText().toString();
            String date = editEventDate.getText().toString();
            String time = editEventTime.getText().toString();
            String location = editEventLocation.getText().toString();

            if (!title.isEmpty() && !date.isEmpty() && !time.isEmpty() && !location.isEmpty()) {
                // Save event to the database or pass it back to the main activity
                Toast.makeText(AddEventActivity.this, "Event saved!", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity
            } else {
                Toast.makeText(AddEventActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
