package com.techswivel.yiw_task9.ui.activities.dashboard

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
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

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mBinding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.appBarMainLayout.toolbar)
        addFragment(HomeFragment())
        setListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tool_bar_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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

    private fun setListeners() {
        setUpBottomNavListener()
    }

    private fun setUpBottomNavListener() {
        mBinding.appBarMainLayout.contentMain.bottomNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val fragment = HomeFragment()
                    addFragment(fragment)
                }
                R.id.navigation_cash_points -> {
                    val fragment = CashPointsFragment()
                    addFragment(fragment)
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
                    val fragment = PromotionsFragment()
                    addFragment(fragment)
                }
                R.id.navigation_profile -> {
                    val fragment = MyAccountFragment()
                    addFragment(fragment)
                }
            }
            true
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
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