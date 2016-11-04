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

//  TODO (2) Declare a Vibrator field called buzzer

    private boolean isBuzzing = false;

    private TextView buzzingMessage;
    private Button buzzerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buzzerButton = (Button) findViewById(R.id.buzzerButton);
        buzzingMessage = (TextView) findViewById(R.id.buzzingMessage);

//      TODO (3) Instantiate the buzzer field

        buzzingRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean shouldBuzz = dataSnapshot.getValue(Boolean.class);
                setBuzzing(shouldBuzz);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setBuzzing(boolean shouldBuzz) {

        String buttonText;
        String messageText;

        if (shouldBuzz) {
//          TODO (4) If we should buzz, vibrate the phone
            isBuzzing = true;
            buttonText = "end the buzzing";
            messageText = "BZZZZZZZZVVVVVVZZZZZZZZZZBZZZZZZZZVVVVVVZZZZZZZZZZBZZZZZZZZVVVVVVZZZZZZZZZZBZZZZZZZZVVVVVVZZZZZZZZZZBZZZZZZZZVVVVVVZZZZZZZZZZBZZZZZZZZVVVVVVZZZZZZZZZZ";
        } else {
//          TODO (5) If not, cancel any vibration
            isBuzzing = false;
            buttonText = "start buzzing";
            messageText = "<SIGH> peace and quiet.";
        }

        buzzerButton.setText(buttonText);
        buzzingMessage.setText(messageText);
    }

    public void onToggleBuzzButtonClick(View view) {

        boolean shouldBuzz = !isBuzzing;
        buzzingRef.setValue(shouldBuzz);
    }
}
