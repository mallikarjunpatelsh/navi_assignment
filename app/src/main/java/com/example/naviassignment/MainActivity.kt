package com.example.naviassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.naviassignment.adapter.PullRequestAdapter
import com.example.naviassignment.databinding.ActivityMainBinding
import com.example.naviassignment.viewmodel.PullRequestViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    val TAG = MainActivity::class.simpleName
    private val pullRequestViewModel by lazy { ViewModelProvider(this).get(PullRequestViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        pullRequestViewModel.closedPullRequestList.observe(this, Observer {
            Log.i(TAG, "onCreate: "+it)
            val recyclerView = _binding.recyclerView
            val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 2)
            recyclerView.setHasFixedSize(true)
            val adapter = PullRequestAdapter(it)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager
        })

    }
}