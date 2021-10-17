package com.example.gb_libs_lesson5.ui.screens.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_libs_lesson5.databinding.ItemRepoBinding
import com.example.gb_libs_lesson5.navigation.AndroidScreens

import com.example.gb_libs_lesson5.ui.items.IRepoListPresenter


class RepoRVAdapter(
    private val login: String,
    private val presenter: IRepoListPresenter
) : RecyclerView.Adapter<RepoRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }
    }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(private val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root),
        RepoItemView {
        override var pos: Int = -1
        override fun showTitle(title: String) {
            vb.tvRepo.text = title

        }




    }
}