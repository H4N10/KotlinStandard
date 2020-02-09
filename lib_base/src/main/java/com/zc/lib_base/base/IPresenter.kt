package com.zc.lib_base.base



/**
 * @ProjectName: KotlinStandard
 * @Author: zc/
 * @Version: 1.0
 */

interface IPresenter<in V: IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}
