package com.labdemo.logbookexercise2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> imageList;
    Button backward_btn;
    Button forward_btn;
    ImageView view_image_item;
    Button add_url_image_btn;
    TextInputLayout input_url_image;
    int currentImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageList = new ArrayList<String>();
        imageList.add("https://huyhoangblog.com/wp-content/uploads/2021/09/avatar-cap-doi-ngau-dep-1.jpg");
        imageList.add("https://huyhoangblog.com/wp-content/uploads/2021/09/avatar-cap-doi-ngau-dep-1-1.jpg");
        imageList.add("https://huyhoangblog.com/wp-content/uploads/2021/09/avatar-cap-amine-nam-ngau.jpg");
        imageList.add("https://huyhoangblog.com/wp-content/uploads/2021/09/avatar-cap-amine-nu-ngau-1.jpg");

        view_image_item = findViewById(R.id.imageViewItem);
        getImageItem(currentImage);
        input_url_image = findViewById(R.id.inputUrlImage);
        backward_btn = findViewById(R.id.backwardBtn);
        forward_btn = findViewById(R.id.forwardBtn);

        backward_btn.setOnClickListener(this::renderImageWhenOnclick);
        forward_btn.setOnClickListener(this::renderImageWhenOnclick);
        input_url_image.getEditText().setText("https://static.tuoitre.vn/tto/i/s626/2017/03/21/2-1-1490080249.jpg");

        add_url_image_btn = findViewById(R.id.buttonAddUrl);
        add_url_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageURl = input_url_image.getEditText().getText().toString();

                if (imageURl.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter Image URL !!!", Toast.LENGTH_SHORT).show();
                } else {
                    imageList.add(imageURl);
                    input_url_image.getEditText().setText("");
                }
            }
        });
    }

    private void renderImageWhenOnclick(View view) {
        if (view == forward_btn) {
            currentImage++;
            if (currentImage == imageList.size()) {
                currentImage = 0;
            }
        } else {
            if (currentImage == 0) {
                currentImage = imageList.size();
            }
            currentImage--;
        }
        getImageItem(currentImage);
    }

    public void getImageItem(int value) {
        Glide.with(MainActivity.this).load(imageList.get(value)).into(view_image_item);
    }
}