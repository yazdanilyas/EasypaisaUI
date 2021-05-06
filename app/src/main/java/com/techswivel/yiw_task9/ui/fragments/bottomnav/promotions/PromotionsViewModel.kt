package com.techswivel.yiw_task9.ui.fragments.bottomnav.promotions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techswivel.yiw_task9.data.repositories.AppRepositories
import com.techswivel.yiw_task9.models.Promotions

class PromotionsViewModel : ViewModel() {
    var promotionsList: MutableList<Any> = ArrayList()
    fun getPromotions(): MutableLiveData<ArrayList<Promotions>> {
        return AppRepositories.getPromotions()
    }

}