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
import com.techswivel.yiw_task9.adapters.FavoritesAdapter
import com.techswivel.yiw_task9.adapters.PackagesAdapter
import com.techswivel.yiw_task9.adapters.PromotionsHorizontalAdapter
import com.techswivel.yiw_task9.databinding.FragmentHomeBinding
import com.techswivel.yiw_task9.ui.fragments.bottomnav.pagerfragments.Tab1Fragment
import com.techswivel.yiw_task9.ui.fragments.bottomnav.pagerfragments.Tab2Fragment
import com.techswivel.yiw_task9.ui.fragments.bottomnav.promotions.PromotionsViewModel

class HomeFragment : Fragment() {

    private lateinit var mBinding: FragmentHomeBinding
    private var fragments = ArrayList<Fragment>()
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mViewModel: PromotionsViewModel
    private lateinit var mRecyclerAdapter: PromotionsHorizontalAdapter
    private lateinit var mAdapter: CustomViewPagerAdapter
    private lateinit var mFavoritesAdapter: FavoritesAdapter
    private lateinit var mPackagesAdapter: PackagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        getAllPromotions()
        getFavoriteContacts()
        getPackages()
        setupRecycler()
        setupFavoritesRecycler()
        setUpPackagesRecycler()

    }


    private fun getPackages() {
        homeViewModel.getMobilePackages().observe(viewLifecycleOwner, Observer {

            if (it != null && it.isNotEmpty()) {
                homeViewModel.packagesList.clear()
                homeViewModel.packagesList.addAll(it)
                if (::mPackagesAdapter.isInitialized)
                    mPackagesAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun getFavoriteContacts() {
        homeViewModel.getFavorites().observe(viewLifecycleOwner, Observer {
            if (it != null && it.isNotEmpty()) {
                homeViewModel.favoritesList.clear()
                homeViewModel.favoritesList.addAll(it)
                if (::mFavoritesAdapter.isInitialized)
                    mFavoritesAdapter.notifyDataSetChanged()
            }
        })
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

    private fun setupViewPager() {
        fragments.add(Tab1Fragment())
        fragments.add(Tab2Fragment())
        mAdapter = CustomViewPagerAdapter(requireActivity().supportFragmentManager, fragments)
        mBinding.viewPagerLayout.viewPager.adapter = mAdapter
        mBinding.viewPagerLayout.tabDots.setupWithViewPager(
            mBinding.viewPagerLayout.viewPager,
            true
        )
    }

    private fun setupRecycler() {
        mRecyclerAdapter = PromotionsHorizontalAdapter(requireContext(), mViewModel.promotionsList)
        mBinding.promotionsRecyclerLayout.promotionsRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        mBinding.promotionsRecyclerLayout.promotionsRecycler.adapter = mRecyclerAdapter
    }

    private fun setupFavoritesRecycler() {
        mFavoritesAdapter = FavoritesAdapter(requireContext(), homeViewModel.favoritesList)
        mBinding.favoritesRecyclerLayout.favoritesRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        mBinding.favoritesRecyclerLayout.favoritesRecycler.adapter = mFavoritesAdapter
    }

    private fun setUpPackagesRecycler() {

        mPackagesAdapter = PackagesAdapter(requireContext(), homeViewModel.packagesList)
        mBinding.packagesRecyclerLayout.packagesRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        mBinding.packagesRecyclerLayout.packagesRecycler.adapter = mPackagesAdapter
    }
}