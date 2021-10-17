package com.example.gb_libs_lesson5.remote


import com.example.gb_libs_lesson5.data.GithubUser
import com.example.gb_libs_lesson5.data.GithubUserRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GithubUsersService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>
}

interface GithubRepoService {

    @GET("repos")
    fun getRepo(): Single<List<GithubUserRepo>>
}
