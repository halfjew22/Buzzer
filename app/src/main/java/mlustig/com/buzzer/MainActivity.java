package mlustig.com.buzzer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// TODO (1) Use Android Studio to create a Firebase project for your app
// TODO (2) FOR DEMO PURPOSES ONLY, set your read / write access to true

public class MainActivity extends AppCompatActivity {

//  TODO (6) Get an instance of your Firebase
//  TODO (7) From your Firebase, get a reference to the "buzzing" field

    private TextView buzzingMessage;
    private Button buzzerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buzzerButton = (Button) findViewById(R.id.buzzerButton);
        buzzingMessage = (TextView) findViewById(R.id.buzzingMessage);

//      TODO (8) Add a ValueEventListener to the buzzingRef
//              TODO (9) When the data changes, log the boolean value
    }

    public void onToggleBuzzButtonClick(View view) {

    }
}
