package com.zc.kotlinstandard.sample

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.zc.kotlinstandard.R
import com.zc.lib_base.base.BaseActivity
import com.zc.lib_base.service.LocationForegroundService
import pub.devrel.easypermissions.EasyPermissions

class HomeActivity : BaseActivity(),HomeContract.View {
    private val TAG = "HomeActivity"
    private val mPresenter by lazy { HomePresenter() }
    private var homeFragment = HomeFragment()
    private var isbind: Boolean = false

    override fun layoutId(): Int {
        return R.layout.activity_home
    }

    override fun initData() {
        mPresenter?.loadMoreData()
        checkPermission()
    }

    override fun initView() {
        mPresenter.attachView(this)
        supportFragmentManager.beginTransaction().replace(R.id.content,homeFragment,"").commit()
    }

    override fun start() {
        getLocation()
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
        val LocationPerms = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) !== PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, perms, 0)
        }
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Log.e(TAG, "--->onServiceConnected")
            val locationForegroundService =
                (service as LocationForegroundService.LocalBinder).getLocationForegroundService()
            val location = locationForegroundService.gps()
            if (location != null) {
                Log.d(TAG,"经度" + location!!.getLatitude() + "\n纬度:" + location!!.getLongitude())
            } else {
                Log.d(TAG,"无法获取地理位置1111")
            }

            locationForegroundService.setLocationCallback(object :
                LocationForegroundService.LocationCallback  {

                override fun onLocation(location: Location?) {
                    if (location != null) {
                        Log.d(TAG,"经度" + location.latitude + "\n纬度:" + location.longitude)
                    } else {
                        Log.d(TAG,"无法获取地理位置1111")
                    }
                }
            })
        }

        override fun onServiceDisconnected(name: ComponentName) {

        }
    }
    //启动位置信息获取 sample
    fun getLocation( ) {

        if (android.os.Build.VERSION.SDK_INT >= 26) {
            if (!GPSOpen()) {
                val i = Intent()
                i.action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
                startActivity(i)
            }
        }

        val intent = Intent(this, LocationForegroundService::class.java)
        isbind = bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private fun GPSOpen(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // 通过GPS卫星定位,定位级别可以精确到街(通过24颗卫星定位,在室外和空旷的地方定位准确、速度快)
        val gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        // 通过WLAN或移动网络(3G/2G)确定的位置(也称作AGPS,辅助GPS定位。主要用于在室内或遮盖物(建筑群或茂密的深林等)密集的地方定位)
        val network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        return gps || network
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isbind) {
            unbindService(connection)
            isbind = false
        }
    }
}
