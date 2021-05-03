package com.example.githubissuesapp.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.githubissuesapp.R
import com.example.githubissuesapp.model.IssuesItem

class IssuesAdapter(
        private val issues: ArrayList<IssuesItem>,
        private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<IssuesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.issues_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:IssuesAdapter.ViewHolder, position: Int) {
        holder.bind(issues[position], onItemClicked)
    }

    override fun getItemCount(): Int {
        return issues.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(issue: IssuesItem, onItemClicked: (Int) -> Unit) = with(itemView){

            findViewById<TextView>(R.id.tvIssueName).text = issue.title

            if(issue.state.equals("open", true)) {
                findViewById<TextView>(R.id.tvState).setTextColor(Color.GREEN)
            }

            findViewById<TextView>(R.id.tvState).text = issue.state

            findViewById<ConstraintLayout>(R.id.container).setOnClickListener {
                onItemClicked(this@ViewHolder.adapterPosition)
            }

        }

    }

}