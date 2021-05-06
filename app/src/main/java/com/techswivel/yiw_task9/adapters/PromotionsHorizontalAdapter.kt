package com.techswivel.yiw_task9.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.techswivel.yiw_task9.R
import com.techswivel.yiw_task9.databinding.ItemPromotionsHorizontalRecyclerBinding
import com.techswivel.yiw_task9.models.Promotions

class PromotionsHorizontalAdapter(
    private val context: Context,
    private val promotionsList: MutableList<Any>
) :
    RecyclerView.Adapter<PromotionsHorizontalAdapter.PromotionsViewHolder>() {
    private lateinit var mBinding: ItemPromotionsHorizontalRecyclerBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        mBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_promotions_horizontal_recycler,
                parent,
                false
            )
        return PromotionsViewHolder(mBinding)
    }

    override fun getItemCount(): Int {
        return promotionsList.size
    }

    override fun onBindViewHolder(holder: PromotionsViewHolder, position: Int) {
        holder.binding.promotion = promotionsList[position] as Promotions
    }

    class PromotionsViewHolder(val binding: ItemPromotionsHorizontalRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


}