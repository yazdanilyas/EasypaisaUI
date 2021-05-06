package com.techswivel.yiw_task9.ui.fragments.bottomnav.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.techswivel.yiw_task9.adapters.CustomViewPagerAdapter
import com.techswivel.yiw_task9.adapters.PromotionsHorizontalAdapter
import com.techswivel.yiw_task9.databinding.FragmentHomeBinding
import com.techswivel.yiw_task9.ui.fragments.bottomnav.pagerfragments.Tab1Fragment
import com.techswivel.yiw_task9.ui.fragments.bottomnav.pagerfragments.Tab2Fragment
import com.techswivel.yiw_task9.ui.fragments.bottomnav.promotions.PromotionsViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mBinding: FragmentHomeBinding
    private var fragments = ArrayList<Fragment>()
    private lateinit var mAdapter: CustomViewPagerAdapter
    private lateinit var mRecyclerAdapter: PromotionsHorizontalAdapter
    private lateinit var mViewModel: PromotionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        mViewModel =
            ViewModelProvider(this).get(PromotionsViewModel::class.java)
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupRecycler()

    }

    override fun onResume() {
        super.onResume()
        getAllPromotions()
    }


    private fun setupViewPager() {
        fragments.add(Tab1Fragment())
        fragments.add(Tab2Fragment())
        mAdapter = CustomViewPagerAdapter(requireActivity().supportFragmentManager, fragments)
        mBinding.viewPagerLayout.viewPager.adapter = mAdapter
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
        mRecyclerAdapter = PromotionsHorizontalAdapter(requireContext(), mViewModel.promotionsList)
        mBinding.promotionsRecyclerLayout.promotionsRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        mBinding.promotionsRecyclerLayout.promotionsRecycler.adapter = mRecyclerAdapter
    }
}