package com.example.gb_libs_lesson5.ui.screens.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_libs_lesson5.App
import com.example.gb_libs_lesson5.databinding.ItemUserBinding
import com.example.gb_libs_lesson5.navigation.AndroidScreens
import com.example.gb_libs_lesson5.ui.images.GlideImageLoader
import com.example.gb_libs_lesson5.ui.items.IUserListPresenter
import kotlinx.android.synthetic.main.item_user.view.*
import ru.terrakok.cicerone.Router

class UsersRVAdapter(
    private val presenter: IUserListPresenter,
    private val imageLoader: GlideImageLoader
) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.item_user.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }
    }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {
        override var pos: Int = -1

        override fun showLogin(login: String) {
            vb.tvLogin.text = login



        }

        override fun loadAvatar(url: String) {
            imageLoader.loadTo(url, vb.avatarImageView)
        }

    }
}