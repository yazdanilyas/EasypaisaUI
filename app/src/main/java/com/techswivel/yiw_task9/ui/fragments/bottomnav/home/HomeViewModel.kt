package com.techswivel.yiw_task9.ui.fragments.bottomnav.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techswivel.yiw_task9.data.repositories.AppRepositories
import com.techswivel.yiw_task9.models.Contact
import com.techswivel.yiw_task9.models.MobilePackage

class HomeViewModel : ViewModel() {
    val packagesList: MutableList<Any> = ArrayList()
    var favoritesList: MutableList<Any> = ArrayList()
    fun getFavorites(): MutableLiveData<ArrayList<Contact>> {
        return AppRepositories.getFavorites()
    }

    fun getMobilePackages(): MutableLiveData<ArrayList<MobilePackage>> {
        return AppRepositories.getMobilePackages()
    }


}