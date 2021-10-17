package com.example.gb_libs_lesson5.ui.screens.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_libs_lesson5.App
import com.example.gb_libs_lesson5.R
import com.example.gb_libs_lesson5.data.GithubRepo
import com.example.gb_libs_lesson5.data.GithubUsersRepo
import com.example.gb_libs_lesson5.databinding.FragmentIntoRepoBinding
import com.example.gb_libs_lesson5.databinding.FragmentRepoBinding
import com.example.gb_libs_lesson5.navigation.BackButtonListener
import com.example.gb_libs_lesson5.ui.images.GlideImageLoader
import com.example.gb_libs_lesson5.ui.screens.users.adapter.RepoRVAdapter
import com.example.gb_libs_lesson5.ui.screens.users.adapter.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


private const val REPOS_URL = "repos_url"


class RepoFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var reposUrl: String? = ""
    private var vb: FragmentRepoBinding? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            reposUrl = it.getString(REPOS_URL)

        }
    }
    private val presenter by moxyPresenter { RepoPresenter(arguments?.getString(REPOS_URL)!!, GithubRepo(), App.instance.router) }
    private val adapter by lazy {
        RepoRVAdapter(
            arguments?.getString(REPOS_URL)!!,
            presenter.repoListPresenter,
            )
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentRepoBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    companion object {

        @JvmStatic
        fun newInstance(reposUrl: String) =
            RepoFragment().apply {
                arguments = Bundle().apply {
                    putString(REPOS_URL, reposUrl)
                }
            }
    }

    override fun init() {
        vb?.rvRepo?.layoutManager = LinearLayoutManager(requireContext())
        vb?.rvRepo?.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }
}