package com.techswivel.yiw_task9.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.techswivel.yiw_task9.R
import com.techswivel.yiw_task9.databinding.ItemFavoritesHorizontalRecyclerBinding
import com.techswivel.yiw_task9.models.Contact

class FavoritesAdapter(context: Context, private val favoritesList: MutableList<Any>) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    private lateinit var mBinding: ItemFavoritesHorizontalRecyclerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        mBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_favorites_horizontal_recycler,
                parent,
                false
            )
        return FavoritesViewHolder(mBinding)
    }

    override fun getItemCount(): Int {
        return favoritesList.size
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.binding.contact = favoritesList[position] as Contact
    }


    class FavoritesViewHolder(val binding: ItemFavoritesHorizontalRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}