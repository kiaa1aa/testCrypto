package com.kasra.zimapycrypto.retrofit


import com.kasra.zimapycrypto.model.CoinListModelItem

import retrofit2.Call
import retrofit2.http.GET

interface IService {

@GET("api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false")
fun getCoinListModel(): Call<List<CoinListModelItem>>


}