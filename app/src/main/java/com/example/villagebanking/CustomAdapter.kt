package com.example.villagebanking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_layout.view.*

class CustomAdapter (val modelList: ArrayList<Model>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: Model = modelList[position]

        holder?.name?.text = model.name
        holder?.role?.text = model.role
        holder?.share?.text = model.share
        holder?.loanApplication?.text = model.loanApplication
    }

    override fun getItemCount(): Int {
        return modelList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.tvName as TextView
        val role = itemView.tvRole as TextView
        val share = itemView.tvShare as TextView
        val loanApplication = itemView.tvLoanApplication as TextView

    }

}