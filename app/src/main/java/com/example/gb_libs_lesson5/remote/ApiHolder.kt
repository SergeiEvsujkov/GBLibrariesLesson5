package com.example.gb_libs_lesson5.remote

import com.example.gb_libs_lesson5.data.GithubUserRepo
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiHolder {

    val apiService: GithubUsersService by lazy {


        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GithubUsersService::class.java)
    }
    val gson = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    fun apiServiceRepo(login: String): GithubRepoService =


        Retrofit.Builder()
            .baseUrl("https://api.github.com/users/$login/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GithubRepoService::class.java)


}