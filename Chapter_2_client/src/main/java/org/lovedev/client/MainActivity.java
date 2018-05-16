package org.lovedev.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void navToBookManager(View view) {
        startActivity(new Intent(this, BookManagerActivity.class));
    }

    public void navToBinderPool(View view) {
        startActivity(new Intent(this, BinderPoolActivity.class));
    }
}
