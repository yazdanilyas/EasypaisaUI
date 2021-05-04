package com.techswivel.yiw_task9.ui.fragments.bottomnav.promotions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.techswivel.yiw_task9.R

class PromotionsFragment : Fragment() {

    private lateinit var promotionsViewModel: PromotionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        promotionsViewModel =
            ViewModelProvider(this).get(PromotionsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_promotions, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        promotionsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}