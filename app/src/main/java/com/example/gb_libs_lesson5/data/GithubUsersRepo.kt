package com.example.gb_libs_lesson5.data

import com.example.gb_libs_lesson5.remote.ApiHolder

class GithubUsersRepo {
    fun getUsers() = ApiHolder.apiService.getUsers()
}

class GithubRepo {
    fun getRepo(repos_url : String) = ApiHolder.apiServiceRepo.getRepo(repos_url)
}

