package com.example.githubissuesapp.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubissuesapp.R
import com.example.githubissuesapp.model.IssuesItem

class IssuesAdapter(val issues: ArrayList<IssuesItem>
) : RecyclerView.Adapter<IssuesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.issues_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:IssuesAdapter.ViewHolder, position: Int) {
        holder.bind(issues[position])
    }

    override fun getItemCount(): Int {
        return issues.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(issue: IssuesItem) = with(itemView){

            findViewById<TextView>(R.id.tvIssueName).text = issue.title
            if(issue.state.equals("open", true)){
                findViewById<TextView>(R.id.tvState).setTextColor(Color.GREEN)
            }else{
                findViewById<TextView>(R.id.tvState).setTextColor(Color.RED)
            }
            findViewById<TextView>(R.id.tvState).text = issue.state
        /*  if(fact.value.length > 80){
                findViewById<TextView>(R.id.tvChuckJoke).setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            }
            findViewById<TextView>(R.id.tvChuckJoke).text = fact.value

            val empty = "uncategorized"
            if(fact.categories.isNullOrEmpty()) {
                findViewById<TextView>(R.id.tvCategory).text = empty
            }
            else {
                findViewById<TextView>(R.id.tvCategory).text = fact.categories[0]
            }





            findViewById<ImageView>(R.id.ivShareButton).setOnClickListener{
                val intent= Intent()
                val link = fact.url
                intent.action = Intent.ACTION_SEND
                intent.type= "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT,"Chuck Norris Fact:\n $link")
                context.startActivity(Intent.createChooser(intent, "Compartilhar com"))
            }*/




        }

    }

}