package com.zc.kotlinstandard

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.zc.module_app.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        ARouter.getInstance().build("/app/login").navigation()
        var intent = Intent(this,LoginActivity::class.java)
        btn_arouter.setOnClickListener(View.OnClickListener {
            ARouter.getInstance().build("/app/login").navigation()
//            startActivity(intent)
        })
    }


}
