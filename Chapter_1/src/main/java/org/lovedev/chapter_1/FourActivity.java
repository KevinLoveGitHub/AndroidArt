package org.lovedev.chapter_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
    }

    public void launchMyself(View view) {
        Intent intent = new Intent(this, FourActivity.class);
        startActivity(intent);
    }
}
