package com.example.naviassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.naviassignment.R
import com.example.naviassignment.model.PullRequest
import java.text.SimpleDateFormat

class PullRequestAdapter(private val requestList:List<PullRequest>): RecyclerView.Adapter<PullRequestAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val userName: TextView
        val title: TextView
        val createDate: TextView
        val closedDate: TextView
        val image: ImageView

        init {
            userName = view.findViewById(R.id.user_name)
            title = view.findViewById(R.id.title)
            createDate = view.findViewById(R.id.created_date)
            closedDate = view.findViewById(R.id.closed_date)
            image = view.findViewById(R.id.user_image)
        }

        override fun onClick(v: View?) { }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.pull_request_user_layout, viewGroup, false)

        return PullRequestAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val respone = requestList[position]
        viewHolder.userName.text = "User Name: "+respone.user.login
        viewHolder.title.text = "title: "+respone.title
        viewHolder.createDate.text = "created at: "+ convertIsoToUI(respone.created_at)
        viewHolder.closedDate.text = "closed at: "+ convertIsoToUI(respone.closed_at)
        val imgUri = respone.user.avatar_url.toUri().buildUpon().scheme("https").build()
        Glide.with(viewHolder.image.context)
            .load(imgUri)
            .into(viewHolder.image)
    }

    override fun getItemCount() = requestList.size

    companion object{
        val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val uiFormat = SimpleDateFormat("dd-MMM-yy hh:mm a")
        fun convertIsoToUI(isoDate: String): String{
            return uiFormat.format(isoFormat.parse(isoDate))
        }
    }
}