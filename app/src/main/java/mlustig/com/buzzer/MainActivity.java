package mlustig.com.buzzer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// TODO (1) Use Android Studio to create a Firebase project for your app
// TODO (2) FOR DEMO PURPOSES ONLY, set your read / write access to true

public class MainActivity extends AppCompatActivity {

//  TODO (6) Get an instance of your Firebase
    FirebaseDatabase firebase = FirebaseDatabase.getInstance();
//  TODO (7) From your Firebase, get a reference to the "buzzing" field
    DatabaseReference buzzingRef = firebase.getReference("buzzing");

    private TextView buzzingMessage;
    private Button buzzerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buzzerButton = (Button) findViewById(R.id.buzzerButton);
        buzzingMessage = (TextView) findViewById(R.id.buzzingMessage);

//      TODO (8) Add a ValueEventListener to the buzzingRef
        buzzingRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//              TODO (9) When the data changes, log the boolean value
                boolean shouldBuzz = dataSnapshot.getValue(Boolean.class);
                Log.d("Lustig", "onDataChange: shouldBuzz: " + shouldBuzz);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onToggleBuzzButtonClick(View view) {

    }
}
