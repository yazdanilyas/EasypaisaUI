package com.techswivel.yiw_task9.ui.activities.dashboard

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.ui.*
import com.techswivel.yiw_task9.R
import com.techswivel.yiw_task9.constants.Constants.REQUEST_IMAGE_CAPTURE
import com.techswivel.yiw_task9.databinding.ActivityDashboardBinding
import com.techswivel.yiw_task9.ui.fragments.bottomnav.account.MyAccountFragment
import com.techswivel.yiw_task9.ui.fragments.bottomnav.cashpoints.CashPointsFragment
import com.techswivel.yiw_task9.ui.fragments.bottomnav.home.HomeFragment
import com.techswivel.yiw_task9.ui.fragments.bottomnav.promotions.PromotionsFragment
import com.techswivel.yiw_task9.utils.AppUtils
import com.techswivel.yiw_task9.utils.AppUtils.showToast
import com.techswivel.yiw_task9.utils.DialogUtil
import com.techswivel.yiw_task9.utils.PermissionsUtil

class DashboardActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityDashboardBinding
    private val home = HomeFragment()
    private val cashPoints = CashPointsFragment()
    private val promotion = PromotionsFragment()
    private val account = MyAccountFragment()
    private val fragmentManager = supportFragmentManager
    private var activeFragment: Fragment = home
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        addFragments()
        setListeners()
        closeDrawer()
    }

    private fun addFragments() {
        fragmentManager.beginTransaction().apply {
            add(R.id.nav_host_fragment, home)
            add(R.id.nav_host_fragment, cashPoints).hide(cashPoints)
            add(R.id.nav_host_fragment, promotion).hide(promotion)
            add(R.id.nav_host_fragment, account).hide(account)
        }.commit()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionsUtil.REQUEST_CODE_CAMERA -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromCamera()
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!shouldShowRequestPermissionRationale(permissions[0])) {
                        showSettingDialog()
                    } else {
                        showToast(applicationContext, getString(R.string.permission_denied))
                    }
                }
            }
        }

    }

    private fun closeDrawer() {
        val navView = mBinding.navView.getHeaderView(0)
        val navBackImg = navView.findViewById<AppCompatImageView>(R.id.navcloseImg)
        navBackImg.setOnClickListener {
            mBinding.drawerLayout.close()
        }
    }


    private fun setListeners() {
        setUpBottomNavListener()
        mBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private fun setUpBottomNavListener() {
        mBinding.appBarMainLayout.contentMain.bottomNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(home).commit()
                    activeFragment = home
                }
                R.id.navigation_cash_points -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(cashPoints)
                        .commit()
                    activeFragment = cashPoints
                }
                R.id.navigation_qr_scan -> {
                    if (PermissionsUtil.hasPermission(
                            this, arrayOf(android.Manifest.permission.CAMERA),
                            PermissionsUtil.REQUEST_CODE_CAMERA
                        )
                    ) {
                        pickImageFromCamera()
                    }
                }
                R.id.navigation_promotions -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(promotion).commit()
                    activeFragment = promotion
                }
                R.id.navigation_profile -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(account).commit()
                    activeFragment = account
                }
            }
            true
        }

    }

    private fun pickImageFromCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
    }


    private fun showSettingDialog() {
        val callBacks = object : DialogUtil.DialogButtonListener {
            override fun onPositiveClick() {
                AppUtils.openAppSetting(
                    this@DashboardActivity,
                    this@DashboardActivity as AppCompatActivity
                )
            }

            override fun onNegativeClick() {
            }
        }
        DialogUtil.showAppSettingDialog(this@DashboardActivity, callBacks)
    }

}