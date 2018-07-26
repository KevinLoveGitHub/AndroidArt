package org.lovedev.chapter_4

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * @author Kevin
 * @data 2018/6/8
 */
class ListViewAdapter(private val data: Array<String>, private val context: Context): BaseAdapter() {

    override fun getItem(position: Int): Any {
        return data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val holder: TestViewHolder
        val v: View
        if (convertView == null) {
            v = View.inflate(context, R.layout.my_text_view, null)
            holder = TestViewHolder(v)
            v.tag = holder
        } else {
            v = convertView
            holder = v.tag as TestViewHolder
        }
        holder.str.text = data[position]
        return v
    }

    class TestViewHolder(viewItem: View) {
        var str: TextView = viewItem.findViewById(R.id.tv) as TextView
    }
}