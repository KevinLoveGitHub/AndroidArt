package org.lovedev.chapter_4

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.BaseAdapter
import android.widget.ListView

class ListViewActivity : AppCompatActivity() {

    private lateinit var list: ListView
    private lateinit var myAdapter: BaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        val myDataset = arrayOf("123", "123", "123", "123", "123")
        myAdapter = ListViewAdapter(myDataset, this)
        list = findViewById<ListView>(R.id.lv).apply {
            adapter = myAdapter
        }
    }
}
