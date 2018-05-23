package org.lovedev.chapter_4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;

public class ViewStubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);
        ViewStub viewStub1 = findViewById(R.id.vs1);
        viewStub1.setVisibility(View.VISIBLE);
//        viewStub1.inflate();
    }
}
