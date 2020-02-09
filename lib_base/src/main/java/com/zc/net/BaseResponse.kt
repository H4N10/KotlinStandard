package com.zc.lib_base.net


/**
 * @ProjectName: KotlinStandard
 * @Description: 封装返回的数据
 * @Author: zc/
 * @Version: 1.0
 */

class BaseResponse<T>(val code :Int,
                      val msg:String,
                      val data:T)