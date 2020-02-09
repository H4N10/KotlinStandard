package com.zc.kotlinstandard.sample.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.zc.kotlinstandard.R
import com.zc.kotlinstandard.sample.data.HomeData
import com.zc.lib_base.view.recyclerview.ViewHolder
import com.zc.lib_base.view.recyclerview.adapter.CommonAdapter
import kotlinx.android.synthetic.main.item_testview.view.*

/**
 *
 * @ProjectName:    KotlinStandard
 * @Description:     java类作用描述
 * @Author:         zc/
 * @Version:        1.0
 */
class HomeRVAdapter(context: Context, data: ArrayList<HomeData>) :CommonAdapter<HomeData>(context,data,-1){
    override fun bindData(holder: ViewHolder, data: HomeData, position: Int) {
        holder.apply {
            setText(R.id.item_tv_tip,data.name)
        }
    }
    /**
     * 加载布局
     */
    private fun inflaterView(mLayoutId: Int, parent: ViewGroup): View {
        //创建view
        val view = mInflater?.inflate(mLayoutId, parent, false)
        return view ?: View(parent.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflaterView(R.layout.item_testview,parent))
    }
}