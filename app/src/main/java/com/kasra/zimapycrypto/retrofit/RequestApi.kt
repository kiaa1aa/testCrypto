package com.kasra.zimapycrypto.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RequestApi {
    val base_url = "http://api.coingecko.com/"
    fun getRetrofit() : Retrofit {
       return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(base_url)
        .build()
    }


}