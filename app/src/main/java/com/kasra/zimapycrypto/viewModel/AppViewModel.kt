package com.kasra.zimapycrypto.viewModel



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kasra.zimapycrypto.model.CoinListModelItem
import com.kasra.zimapycrypto.retrofit.IService
import com.kasra.zimapycrypto.retrofit.RequestApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppViewModel : ViewModel(){


     var liveDataList : MutableLiveData<List<CoinListModelItem>>

    init {
        liveDataList = MutableLiveData()


    }

    fun getLiveDataObserver() : MutableLiveData<List<CoinListModelItem>>{

        return liveDataList
    }

    fun makeApiCall() {
        val retrofitApi =  RequestApi.getRetrofit()
        val retrofitServis =  retrofitApi.create(IService::class.java)
        val call = retrofitServis.getCoinListModel()

        call.enqueue(object : Callback<List<CoinListModelItem>?> {
            override fun onResponse(
                call: Call<List<CoinListModelItem>?>,
                response: Response<List<CoinListModelItem>?>
            ) {
                liveDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<CoinListModelItem>?>, t: Throwable) {
                liveDataList.postValue(null)
            }
        })



    }

}