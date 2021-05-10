package com.techswivel.yiw_task9.ui.fragments.bottomnav.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.techswivel.yiw_task9.R

class MyAccountFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_account, container, false)

        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        return root
    }
}