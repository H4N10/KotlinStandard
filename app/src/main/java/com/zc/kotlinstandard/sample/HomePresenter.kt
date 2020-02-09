package com.zc.kotlinstandard.sample

import android.util.Log
import com.zc.lib_base.base.BasePresenter
import com.zc.lib_base.model.HomeModel
import com.zc.lib_base.net.exception.ExceptionHandle
import io.reactivex.functions.Consumer

/**
 *
 * @ProjectName:    KotlinStandard
 * @Description:     java类作用描述
 * @Author:         zc/
 * @Version:        1.0
 */
class HomePresenter :BasePresenter<HomeContract.View>(),HomeContract.Presenter{
    private val homeModel: HomeModel by lazy {

        HomeModel()
    }

    override fun loadMoreData() {
        homeModel.requestHomeData(0).subscribe({ homeBean->
            mRootView?.apply {
                //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                val newBannerItemList = homeBean.issueList[0].itemList
                Log.d("",newBannerItemList.size.toString())
            }

        }, { t ->
            mRootView?.apply {
                showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
            }
        })

        homeModel.requestHomeData(0).subscribe(Consumer {

        }, Consumer {

        })

    }

}