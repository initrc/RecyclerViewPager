package com.example.dshi.recyclerviewpager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val adapter = DummyAdapter().apply {
            data.addAll(listOf(1, 2, 3, 4, 5))
            recyclerView.adapter = this
        }
        val snapHelper = PagerSnapHelper().apply {
            attachToRecyclerView(recyclerView)
        }

        addToLeftBtn.setOnClickListener {
            recyclerView.layoutManager.let {
                val index = it.getPosition(snapHelper.findSnapView(it))
                adapter.data.add(index, adapter.data[index] * 2)
                adapter.notifyItemInserted(index)
            }
        }
        addToRightBtn.setOnClickListener {
            recyclerView.layoutManager.let {
                val index = it.getPosition(snapHelper.findSnapView(it))
                adapter.data.add(index + 1, adapter.data[index] * 2)
                adapter.notifyItemInserted(index + 1)
            }
        }
    }

    class DummyAdapter : RecyclerView.Adapter<DummyViewHolder>() {
        val data = ArrayList<Int>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyViewHolder {
            return DummyViewHolder(LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false))
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
            holder.textView.apply {
                text = data[position].toString()
                textSize = Math.min(10f + data[position] * 4, 200f)
            }
        }
    }

    class DummyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(android.R.id.text1)!!
    }
}
