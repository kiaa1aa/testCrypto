package com.kasra.zimapycrypto.adapter



import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kasra.zimapycrypto.R
import com.kasra.zimapycrypto.databinding.CoinListRowBinding
import com.kasra.zimapycrypto.model.CoinListModelItem
import com.kasra.zimapycrypto.ui.CryptoFragmentDirections


class CoinListAdpter(val activity: FragmentActivity?) : RecyclerView.Adapter<CoinListAdpter.MyViewHolder>() {


    private var coinListModel : List<CoinListModelItem>? = null
    lateinit var adabpterBinding:CoinListRowBinding

    fun setCoinList(coinListModel : List<CoinListModelItem>?){
        this.coinListModel = coinListModel
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
         adabpterBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.coin_list_row,parent,false)
        return MyViewHolder(adabpterBinding )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(coinListModel?.get(position)!!, activity)
    }

    override fun getItemCount(): Int {
        if (coinListModel == null)return 0
        else return coinListModel?.size!!
    }

    class MyViewHolder( private  val binding: CoinListRowBinding) : RecyclerView.ViewHolder(binding.root){

        val coinImg = binding.coinimg
        val coinName = binding.coinname
        val coinPrice = binding.coinprice
        val coinSymbol = binding.coinsymbol



        init {


            coinImg.setOnClickListener(View.OnClickListener {

                val id = CryptoFragmentDirections.actionCryptoFragmentToDetailFragment(
                    adapterPosition)

                Navigation.findNavController(binding.root).navigate(id)
            })
        }


        fun bind(data: CoinListModelItem, activity: FragmentActivity?){


            coinName.text = data.name
            coinSymbol.text = data.symbol
            coinPrice.text = "$" + data.current_price
            Glide.with(binding.root).load(Uri.parse(data.image)).into(coinImg)


        }

    }
}