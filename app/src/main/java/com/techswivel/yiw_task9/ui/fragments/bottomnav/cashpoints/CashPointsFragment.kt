package com.techswivel.yiw_task9.ui.fragments.bottomnav.cashpoints

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.techswivel.yiw_task9.R
import com.techswivel.yiw_task9.databinding.FragmentCashPointsBinding

class CashPointsFragment : Fragment() {
    private lateinit var mBinding: FragmentCashPointsBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<NestedScrollView>
    private var hideShow = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentCashPointsBinding.inflate(inflater, container, false)
        setListeners()
        return mBinding.root
    }

    private fun setListeners() {
        mBinding.bottomSheetLayout.bottomHideShowImg.setOnClickListener {
            hideShowBottomSheet()
        }

    }

    private fun hideShowBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(mBinding.bottomSheetLayout.bottomSheet)
        if (hideShow) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            mBinding.bottomSheetLayout.bottomHideShowImg.setImageResource(R.drawable.ic_down)
            hideShow = false
        } else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            mBinding.bottomSheetLayout.bottomHideShowImg.setImageResource(R.drawable.ic_arrow_up)
            hideShow = true

        }
    }
}