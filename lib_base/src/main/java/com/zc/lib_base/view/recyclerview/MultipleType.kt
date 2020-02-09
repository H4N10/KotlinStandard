package com.zc.lib_base.view.recyclerview


/**
 *
 * @ProjectName:    KotlinStandard
 * @Description:    多布局条目类型
 * @Author:         zc/
 * @Version:        1.0
 */
interface MultipleType<in T> {
    fun getLayoutId(item: T, position: Int): Int
}
