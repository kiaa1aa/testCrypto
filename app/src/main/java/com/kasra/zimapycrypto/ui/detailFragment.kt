package com.kasra.zimapycrypto.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kasra.zimapycrypto.R
import com.kasra.zimapycrypto.databinding.FragmentDetailBinding
import com.kasra.zimapycrypto.viewModel.AppViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


class detailFragment : Fragment() {

    lateinit var binding : FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)

        val bundle = arguments
        val args = detailFragmentArgs.fromBundle(bundle!!)



        val viewModel : AppViewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {

//            binding.coinpriced.text = model.name?.get(2).toString()
            val listClick = it[args.getId]
            binding.coinpriced.text = listClick.current_price
            binding.coinsymbold.text = listClick.symbol
            binding.coinnamed.text = listClick.name

            Glide.with(binding.root).load(Uri.parse(listClick.image)).into(coinimgd)


        })
        viewModel.makeApiCall()



        return binding.root
    }}
