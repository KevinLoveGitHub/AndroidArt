package org.lovedev.chapter_2;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        Uri uri = Uri.parse("content://org.lovedev.chapter_2.BookProvider");
        getContentResolver().query(uri, null, null, null, null, null);
    }
}
