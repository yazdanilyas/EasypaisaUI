package com.techswivel.yiw_task9.ui.fragments.bottomnav.promotions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.techswivel.yiw_task9.adapters.PromotionsAdapter
import com.techswivel.yiw_task9.databinding.FragmentPromotionsBinding

class PromotionsFragment : Fragment() {

    private lateinit var mViewModel: PromotionsViewModel
    private lateinit var mBinding: FragmentPromotionsBinding
    private lateinit var mAdapter: PromotionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mViewModel =
            ViewModelProvider(this).get(PromotionsViewModel::class.java)
        mBinding = FragmentPromotionsBinding.inflate(inflater, container, false)
        setupRecycler()
        getAllPromotions()
        return mBinding.root
    }

    private fun getAllPromotions() {
        mViewModel.getPromotions().observe(viewLifecycleOwner, Observer {
            if (it != null && it.isNotEmpty()) {
                mViewModel.promotionsList.clear()
                mViewModel.promotionsList.addAll(it)
                if (::mAdapter.isInitialized)
                    mAdapter.notifyDataSetChanged()

            }

        })
    }

    private fun setupRecycler() {
        mAdapter = PromotionsAdapter(requireContext(), mViewModel.promotionsList)
        mBinding.promotionsRecycler.layoutManager = LinearLayoutManager(requireContext())
        mBinding.promotionsRecycler.adapter = mAdapter
    }
}