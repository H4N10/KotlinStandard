package com.zc.kotlinstandard.sample

import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.zc.kotlinstandard.R
import com.zc.kotlinstandard.sample.adapter.HomeRVAdapter
import com.zc.kotlinstandard.sample.data.HomeData
import com.zc.lib_base.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *
 * @ProjectName:    KotlinStandard
 * @Description:     java类作用描述
 * @Author:         zc/
 * @Version:        1.0
 */
class HomeFragment :BaseFragment(),HomeContract.View{
    private val mPresenter by lazy { HomePresenter() }
    private var mHomeAdapter: HomeRVAdapter? = null
    private val data = ArrayList<HomeData>()
    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        mPresenter.attachView(this)
        initData()
        mHomeAdapter = activity?.let { HomeRVAdapter(it, data) }
        rv_home.layoutManager = linearLayoutManager
        rv_home.adapter = mHomeAdapter
    }
    fun initData(){
        data.add(HomeData("1"))
        data.add(HomeData("2"))
        data.add(HomeData("3"))
        data.add(HomeData("4"))
        data.add(HomeData("5"))
    }
    override fun lazyLoad() {
    }

    override fun showError(msg: String, errorCode: Int) {
        Toast.makeText(context,msg, Toast.LENGTH_LONG).show()
    }

}