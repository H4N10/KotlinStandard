package com.zc.lib_base.view.recyclerview.adapter

/**
 *
 * @ProjectName:    KotlinStandard
 * @Description:     Adapter条目的点击事件
 * @Author:         zc/
 * @Version:        1.0
 */

interface OnItemLongClickListener {

    fun onItemLongClick(obj: Any?, position: Int): Boolean

}
