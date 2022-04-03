package de.androidcrypto.androidstartactivityforresultdeprecated;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    Button takeImageDeprecated, takeImageNew;
    TextView messages;
    ImageView imageView;

    static final int REQUEST_IMAGE_CAPTURE_LOW_RESOLUTION = 1;

    ActivityResultLauncher<Intent> getImage;

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
                runTakeImageDeprecated(); }
        });


        takeImageNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMessage();
                addMessage("takeImageNew started");
                runTakeImageNew();
            }
        });

        getImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    addMessage("getImage registerForActivityResult started");
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data"); // this is the thumbnail
                    imageView.setImageBitmap(bitmap);
                    addMessage("takeImageNew completed");
                }
            }
        });
    }

    void runTakeImageDeprecated() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_LOW_RESOLUTION);
        } catch (ActivityNotFoundException e) {
            addMessage("error on starting startActivityForResult");
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE_LOW_RESOLUTION && resultCode == RESULT_OK) {
            addMessage("onActivityResult started");
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data"); // this is the thumbnail
            imageView.setImageBitmap(bitmap);
            addMessage("takeImageDeprecated completed");
        }
    }

    void runTakeImageNew() {
        getImage.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
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