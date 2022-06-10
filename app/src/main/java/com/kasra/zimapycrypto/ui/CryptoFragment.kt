package com.kasra.zimapycrypto.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.kasra.zimapycrypto.adapter.CoinListAdpter
import com.kasra.zimapycrypto.R
import com.kasra.zimapycrypto.databinding.FragmentCryptoBinding
import com.kasra.zimapycrypto.viewModel.AppViewModel


class CryptoFragment : Fragment() {



    lateinit var recyclerAdapter : CoinListAdpter
    private lateinit var binding : FragmentCryptoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_crypto,container,false)



        initViewModel()
        initRecyclerView()
        return binding.root
    }
    fun fabOnClick(view : View){
        Navigation.findNavController(view).navigate(R.id.action_cryptoFragment_to_detailFragment)
    }


    private fun initRecyclerView(){


        binding.coinListRcycler.layoutManager = LinearLayoutManager(activity)
        recyclerAdapter = CoinListAdpter(activity)
        binding.coinListRcycler.adapter = recyclerAdapter



    }
    private fun initViewModel(){
        val viewModel : AppViewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if (it !=null){
                recyclerAdapter.setCoinList(it)
                recyclerAdapter.notifyDataSetChanged()

            }else{
                Toast.makeText(activity,"error in geting list",Toast.LENGTH_LONG).show()
            }
        })
        viewModel.makeApiCall()

    }




}

