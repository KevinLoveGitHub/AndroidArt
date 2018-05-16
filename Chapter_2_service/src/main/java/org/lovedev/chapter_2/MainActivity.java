package org.lovedev.chapter_2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Constant.index = 2;
        Log.d(TAG, "hashCode: " + Constant.class.hashCode() + " index: " + Constant.index);
    }

    public void navToFirst(View view) {
        startActivity(new Intent(this, FirstActivity.class));
    }

    public void navToSecond(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
    public void navToProvide(View view) {
        startActivity(new Intent(this, ProviderActivity.class));
    }

    public void serializable(View view) {
        User user = new User();
        user.name = "kevin";
        user.age = 25;

        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/user.txt";
        File file = new File(filePath);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(user);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserializable(View view) {
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/user.txt";
        File file = new File(filePath);
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
            User u = (User) input.readObject();
            input.close();
            Log.d(TAG, "serializable: " + u.name);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void navToMessenger(View view) {
        startActivity(new Intent(this, MessengerActivity.class));
    }
}
