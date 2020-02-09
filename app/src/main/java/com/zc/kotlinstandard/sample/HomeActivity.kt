package com.zc.kotlinstandard.sample

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zc.kotlinstandard.R
import com.zc.lib_base.base.BaseActivity
import pub.devrel.easypermissions.EasyPermissions

class HomeActivity : BaseActivity(),HomeContract.View {
    private val mPresenter by lazy { HomePresenter() }
    private var homeFragment = HomeFragment()

    override fun layoutId(): Int {
        return R.layout.activity_home
    }

    override fun initData() {
        mPresenter?.loadMoreData()

    }

    override fun initView() {
        mPresenter.attachView(this)
        supportFragmentManager.beginTransaction().replace(R.id.content,homeFragment,"").commit()
    }

    override fun start() {
    }

    override fun showError(msg: String, errorCode: Int) {
//        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

    /**
     * 6.0以下版本(系统自动申请) 不会弹框
     * 有些厂商修改了6.0系统申请机制，他们修改成系统自动申请权限了
     */
    private fun checkPermission(){
        val perms = arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        EasyPermissions.requestPermissions(this, "应用需要以下权限，请允许", 0, *perms)

    }
}
