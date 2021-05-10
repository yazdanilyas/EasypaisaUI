package com.techswivel.yiw_task9.data.repositories

import androidx.lifecycle.MutableLiveData
import com.techswivel.yiw_task9.R
import com.techswivel.yiw_task9.models.Contact
import com.techswivel.yiw_task9.models.MobilePackage
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

        fun getFavorites(): MutableLiveData<ArrayList<Contact>> {
            val favoritesList = ArrayList<Contact>()
            val favorites = MutableLiveData<ArrayList<Contact>>()
            val names = arrayOf("Imran Anjum", "Husnain", "Amjad", "Rana Yousaf", "Farhan Bhatti")
            for (i in names.indices) {

                favoritesList.add(Contact(R.drawable.ic_user, names[i]))
            }
            favorites.postValue(favoritesList)
            return favorites
        }

        fun getMobilePackages(): MutableLiveData<java.util.ArrayList<MobilePackage>> {
            val packageList = ArrayList<MobilePackage>()
            val packages = MutableLiveData<ArrayList<MobilePackage>>()
            packageList.add(
                MobilePackage(
                    "Discounted EasyCard 800",
                    "Rs. 100 discount",
                    "validity for 30 days only",
                    5000,
                    300,
                    5000,
                    18.5f,
                    "Rs. 665",
                    "For 3 subscription only. 9 Gbs are only useable between 1 to 9 am"
                )
            )
            packageList.add(
                MobilePackage(
                    "Monthly Social Pack Plus",
                    "Rs. 100 discount",
                    "validity for 30 days only",
                    5000,
                    300,
                    5000,
                    18.5f,
                    "Rs. 85",
                    "For 3 subscription only. 9 Gbs are only useable between 1 to 9 am"
                )
            )
            packageList.add(
                MobilePackage(
                    "Super Recharge Offer",
                    "Rs. 100 discount",
                    "validity for 30 days only",
                    5000,
                    300,
                    5000,
                    18.5f,
                    "Rs. 53",
                    "For 3 subscription only. 9 Gbs are only useable between 1 to 9 am"
                )
            )

            packages.postValue(packageList)
            return packages
        }

    }


}