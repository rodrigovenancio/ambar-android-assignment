package com.git.repolist.ui.repository.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.git.repolist.R
import com.git.repolist.data.model.Repository
import kotlinx.android.synthetic.main.item_repository_list.view.*

class RepositoryListAdapter(private val products: ArrayList<Repository>, private val clickListener: (Repository) -> Unit) : RecyclerView.Adapter<RepositoryListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(repository: Repository, clickListener: (Repository) -> Unit) {
            itemView.apply {
                textViewRepositoryName.text = repository.name
                textViewRepositoryOwner.text = repository.owner.login
                setOnClickListener { clickListener(repository) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository_list, parent, false))

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(products[position], clickListener)
    }

    fun addRepositories(users: List<Repository>) {
        this.products.apply {
            clear()
            addAll(users)
        }

    }
}