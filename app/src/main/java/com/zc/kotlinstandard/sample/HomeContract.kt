package com.zc.kotlinstandard.sample

import com.zc.lib_base.base.IBaseView
import com.zc.lib_base.base.IPresenter


/**
 * Created by xuhao on 2017/11/8.
 * 契约类
 */

interface HomeContract {

    interface View : IBaseView {

        /**
         * 显示错误信息
         */
        fun showError(msg: String,errorCode:Int)


    }

    interface Presenter : IPresenter<View> {


        /**
         * 加载更多数据
         */
        fun loadMoreData()


    }


}