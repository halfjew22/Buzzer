package mlustig.com.buzzer;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebase = FirebaseDatabase.getInstance();
    DatabaseReference buzzingRef = firebase.getReference("buzzing");

    //  TODO (2) Declare a Vibrator field called buzzer
    private Vibrator buzzer;

    private boolean isBuzzing = false;

    private TextView buzzingMessage;
    private Button buzzerButton;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buzzerButton = (Button) findViewById(R.id.buzzerButton);
        buzzingMessage = (TextView) findViewById(R.id.buzzingMessage);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

//      TODO (3) Instantiate the buzzer field
        buzzer = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

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
            long pattern[] = {0, 1000};
            buzzer.vibrate(pattern, 0);
            isBuzzing = true;
            buttonText = "end the buzzing";
            messageText = "BZZZZZZZZVVVVVVZZZZZZZZZZBZZZZZZZZVVVVVVZZZZZZZZZZBZZZZZZZZVVVVVVZZZZZZZZZZBZZZZZZZZVVVVVVZZZZZZZZZZBZZZZZZZZVVVVVVZZZZZZZZZZBZZZZZZZZVVVVVVZZZZZZZZZZ";
        } else {
//          TODO (5) If not, cancel any vibration
            buzzer.cancel();
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

    public void sendPush(View view) throws JSONException, IOException {

        String messagingTokenNexus6PRealDevice = "dcK7YuKJZLc:APA91bGA5qNYkedG8P9rMnhlqu5XHcQekidTt8G9mh0MEU7R34gucaYcoExz78PgeCWmbRNppmjNtRCm9R32BuDsHmuP6eo0Shp5ehcZLXbO4zQdOZwXg79BBm7jQ5BfpKCdpWZxh2Yo";
        JSONObject message = new JSONObject();

        message.put("to", messagingTokenNexus6PRealDevice);

        JSONObject data = new JSONObject();
        data.put("text", "WOO TEXT");
        data.put("title", "WOO TITLE");

        message.put("data", data);


        MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, message.toString());
        Request request = new Request.Builder()
                .url("https://fcm.googleapis.com/fcm/send")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "key=AIzaSyBHT27RFOj6BdJ4jGhkdbIkFI69K7TALiw")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        JSONObject responseJSON = new JSONObject(response.body().string());
        System.out.println("installed?: " + responseJSON.get("success"));

        System.out.println(response.body().string());

    }
}
