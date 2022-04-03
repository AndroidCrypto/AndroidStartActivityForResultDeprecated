package de.androidcrypto.androidstartactivityforresultdeprecated;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button takeImageDeprecated, takeImageNew;
    TextView messages;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        takeImageDeprecated = findViewById(R.id.btnTakePhotoDeprecated);
        takeImageNew = findViewById(R.id.btnTakePhotoNew);
        messages = findViewById(R.id.tvMessages);
        imageView = findViewById(R.id.ivImage);


        takeImageDeprecated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMessage();
                addMessage("takeImageDeprecated started");
                // dispatchTakePictureIntentFullResolution(); // is called from verifyPermissions
            }
        });

        takeImageNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMessage();
                addMessage("takeImageNew started");
                // dispatchTakePictureIntentFullResolution(); // is called from verifyPermissions
            }
        });
    }

    void clearMessage() {
        messages.setText("");
    }

    void addMessage(String message) {
        String oldMessage = messages.getText().toString();
        String newMessage = message + "\n" + oldMessage;
        messages.setText(newMessage);
    }
}