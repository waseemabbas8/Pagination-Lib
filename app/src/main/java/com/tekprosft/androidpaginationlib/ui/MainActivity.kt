package com.tekprosft.androidpaginationlib.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tekprosft.androidpaginationlib.R
import com.tekprosft.androidpaginationlib.model.Cheese
import com.tekprosft.androidpaginationlib.utils.MyViewBinder.Companion.bind
import com.tekprosft.androidpaginationlib.utils.SampleData
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mRecyclerView: RecyclerView by bind(R.id.cheeseList)
    private val mAdapter = CheeseAdapter()
    private lateinit var mainViewModel: MainViewModel

    private val mInputText: EditText by bind(R.id.inputText)
    private val mAdd: Button by bind(R.id.addButton)
    private val mAddSample: Button by bind(R.id.add_sample)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initViewModel()

        mAddSample.setOnClickListener {
            mainViewModel.addSampleData(SampleData().getCheeseList())
        }
    }

    private fun initViewModel() {
        val observer = Observer(mAdapter::submitList)
        mainViewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)
        // Subscribe the adapter to the ViewModel, so the items in the adapter are refreshed
        // when the list changes
        mainViewModel.mCheeseList.observe(this@MainActivity,observer)
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this@MainActivity)
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.hasFixedSize()
        mRecyclerView.adapter = mAdapter
    }
}
