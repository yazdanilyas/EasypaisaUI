package com.techswivel.yiw_task9.data.repositories

import androidx.lifecycle.MutableLiveData
import com.techswivel.yiw_task9.R
import com.techswivel.yiw_task9.models.Promotions

class AppRepositories {
    companion object {
        fun getPromotions(): MutableLiveData<ArrayList<Promotions>> {
            val promotionList = ArrayList<Promotions>()
            val promotions = MutableLiveData<ArrayList<Promotions>>()
            val images = arrayOf(
                R.drawable.qr_payments,
                R.drawable.donat_ramzan,
                R.drawable.scanandwin,
                R.drawable.invite_win
            )
            val mainSlogans = arrayOf(
                "Make QR Payments & Win Big!",
                "Donate for a cause this Ramzan!",
                "Scan & Win!",
                "Invite & Win Cashback!"
            )
            val secondarySlogans = arrayOf(
                "Be the luck one to win cash prize Rs 25,000 and iPhone 12!",
                "With Easypaisa",
                "Scan Easypaisa Qr code & win Huawei watch fit in just Rs 5!",
                "Invite your friends to easypaisa app and win Rs 50!"
            )

            for (i in images.indices) {
                promotionList.add(Promotions(images[i], mainSlogans[i], secondarySlogans[i]))
            }

            promotions.postValue(promotionList)
            return promotions
        }

    }


}