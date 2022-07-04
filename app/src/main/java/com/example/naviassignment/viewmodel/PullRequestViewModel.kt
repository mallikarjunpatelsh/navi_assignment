package com.example.naviassignment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.naviassignment.model.PullRequest
import com.example.naviassignment.repository.PullRequestApi
import kotlinx.coroutines.launch

class PullRequestViewModel: ViewModel() {
    private val TAG = PullRequestViewModel::class.simpleName
    var closedPullRequestList = MutableLiveData<List<PullRequest>>()
    var error = MutableLiveData<String>()

    init {
        getClosedPullRequest()
    }

    fun getClosedPullRequest(){
        viewModelScope.launch {
            try {
                val request = PullRequestApi.pullRequestRepository.getClosedPullRequest()
                closedPullRequestList.value = request
            }catch (e: java.lang.Exception){
                error.value = e.message
            }
        }
    }
}