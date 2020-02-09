package com.zc.lib_base.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *
 * @ProjectName:    KotlinStandard
 * @Description:
 * @Author:         zc/
 * @Version:        1.0
 */
class IoMainScheduler<T> : BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread())
