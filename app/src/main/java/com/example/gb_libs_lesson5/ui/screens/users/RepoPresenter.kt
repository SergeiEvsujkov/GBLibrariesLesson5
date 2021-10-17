package com.example.gb_libs_lesson5.ui.screens.users

import android.util.Log
import com.example.gb_libs_lesson5.data.GithubRepo
import com.example.gb_libs_lesson5.data.GithubUser
import com.example.gb_libs_lesson5.data.GithubUserRepo
import com.example.gb_libs_lesson5.data.GithubUsersRepo
import com.example.gb_libs_lesson5.navigation.AndroidScreens
import com.example.gb_libs_lesson5.ui.items.IRepoListPresenter
import com.example.gb_libs_lesson5.ui.items.IUserListPresenter
import com.example.gb_libs_lesson5.ui.screens.users.adapter.RepoItemView
import com.example.gb_libs_lesson5.ui.screens.users.adapter.UserItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepoPresenter(
    private val reposUrl: String,
    private val repo: GithubRepo,
    private val router: Router,

    ) : MvpPresenter<UsersView>() {


    class RepoListPresenter : IRepoListPresenter {

        val repoUser = mutableListOf<GithubUserRepo>()

        override var itemClickListener: ((RepoItemView) -> Unit)? = null
        override fun bindView(view: RepoItemView) {
            val title = repoUser[view.pos]
            view.showTitle(title.name.orEmpty())

        }

        override fun getCount(): Int = repoUser.size

    }

    val repoListPresenter = RepoListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()
        repoListPresenter.itemClickListener = { itemView ->
            repoListPresenter.repoUser[itemView.pos].size?.let {
                repoListPresenter.repoUser[itemView.pos].language?.let { it1 ->
                    AndroidScreens.IntoRepoScreen(
                        it1, it.toLong()
                    )
                }
            }
                ?.let { router.navigateTo(it) }
        }


    }

    private fun loadData() {
        repo.getRepo(reposUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ users ->
                repoListPresenter.repoUser.addAll(users)
                viewState.updateList()
            }, {
                Log.e("RepoPresenter", "Ошибка получения репозиториев", it)
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}