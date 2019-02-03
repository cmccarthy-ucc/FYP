package com.example.ciara.drugsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);Thread xyz = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(Splashscreen.this, ActivityOptionsTwo.class);
                    startActivity(intent);
                }

            }
        };
        xyz.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
