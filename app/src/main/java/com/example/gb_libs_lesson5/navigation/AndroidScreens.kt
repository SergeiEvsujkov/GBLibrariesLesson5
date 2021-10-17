package com.example.gb_libs_lesson5.navigation

import com.example.gb_libs_lesson5.ui.screens.users.IntoRepoFragment
import com.example.gb_libs_lesson5.ui.screens.users.RepoFragment
import com.example.gb_libs_lesson5.ui.screens.users.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class UsersScreen : SupportAppScreen() {

        override fun getFragment() = UsersFragment()
    }

    class RepoScreen(
        val login: String
    ) : SupportAppScreen() {

        override fun getFragment() = RepoFragment.newInstance(login)
    }

    class IntoRepoScreen(
        val language: String,
        val size: Long
    ) : SupportAppScreen() {

        override fun getFragment() = IntoRepoFragment.newInstance(language, size.toString())
    }
}