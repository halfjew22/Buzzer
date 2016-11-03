package mlustig.com.buzzer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebase = FirebaseDatabase.getInstance();
    DatabaseReference buzzingRef = firebase.getReference("buzzing");

//  TODO (1) Instantiate a boolean field called isBuzzing to false
    private boolean isBuzzing = false;

    private TextView buzzingMessage;
    private Button buzzerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buzzerButton = (Button) findViewById(R.id.buzzerButton);
        buzzingMessage = (TextView) findViewById(R.id.buzzingMessage);

        buzzingRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean shouldBuzz = dataSnapshot.getValue(Boolean.class);
//              TODO (7) Pass shouldBuzz to setBuzzing (and remove the log message)
                setBuzzing(shouldBuzz);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

//  TODO (2) Create a method that accepts a boolean to buzz this device
    private void setBuzzing(boolean shouldBuzz) {

        String buttonText;
        String messageText;

        if (shouldBuzz) {
//          TODO (3) If we should start buzzing, set buzzing to true and setup buzzing messages
            isBuzzing = true;
            buttonText = "end the buzzing";
            messageText = "BZZZZZZZZVVVVVVZZZZZZZZZZBZZZZZZZZVVVVVVZZZZZZZZZZBZZZZZZZZVVVVVVZZZZZZZZZZBZZZZZZZZVVVVVVZZZZZZZZZZBZZZZZZZZVVVVVVZZZZZZZZZZBZZZZZZZZVVVVVVZZZZZZZZZZ";
        } else {
//          TODO (4) If we shouldn't buzz, set buzzing to false and setup buzzing messages
            isBuzzing = false;
            buttonText = "start buzzing";
            messageText = "<SIGH> peace and quiet.";
        }

//      TODO (5) Display the buzzing messages
        buzzerButton.setText(buttonText);
        buzzingMessage.setText(messageText);
    }

//  TODO (6) Fill in this method with logic to toggle everyone's buzzzzzzzzzzz
    public void onToggleBuzzButtonClick(View view) {

        boolean shouldBuzz = !isBuzzing;
        buzzingRef.setValue(shouldBuzz);
    }
}
