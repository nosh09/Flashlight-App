package com.example.flashlightapp;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Switch aSwitch;
    TextView aTextView;
    CameraManager cameraManager;
    String cameraid, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        aSwitch = findViewById(R.id.switch1);
        aTextView = findViewById(R.id.offText);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //flashlight code
                torch(isChecked);
                
            }
        });
    }

    //Torch Method
    private void torch(boolean isChecked) {
        try {
            cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
            cameraid = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraid, isChecked);
            result = isChecked?"ON":"OFF";
            aTextView.setText(result);
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
    }
}