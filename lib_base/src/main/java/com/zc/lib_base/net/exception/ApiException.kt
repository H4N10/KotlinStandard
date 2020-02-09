package com.zc.lib_base.net.exception

/**
 * @ProjectName: KotlinStandard
 * @Description:
 * @Author: zc/
 * @Version: 1.0
 */
class ApiException : RuntimeException {

    private var code: Int? = null


    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}