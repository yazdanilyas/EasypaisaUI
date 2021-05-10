package com.techswivel.yiw_task9.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.techswivel.yiw_task9.R
import com.techswivel.yiw_task9.databinding.ItemPackagesHorizontalRecyclerBinding
import com.techswivel.yiw_task9.models.MobilePackage

class PackagesAdapter(
    private val context: Context,
    private val packagesList: MutableList<Any>
) :
    RecyclerView.Adapter<PackagesAdapter.PackagesViewHolder>() {
    private lateinit var mBinding: ItemPackagesHorizontalRecyclerBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackagesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        mBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_packages_horizontal_recycler,
                parent,
                false
            )
        return PackagesViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: PackagesViewHolder, position: Int) {
        holder.binding.mobilePackage = packagesList[position] as MobilePackage
    }

    override fun getItemCount(): Int {
        return packagesList.size
    }


    class PackagesViewHolder(val binding: ItemPackagesHorizontalRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}