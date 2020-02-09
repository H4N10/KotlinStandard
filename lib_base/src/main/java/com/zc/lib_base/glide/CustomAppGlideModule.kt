package com.zc.lib_base.glide

import android.content.Context

import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

import java.io.InputStream

/**
 *
 * @ProjectName:    KotlinStandard
 * @Description:
 * @Author:         zc/
 * @Version:        1.0
 */
/**
 * GlideApp.with(mContext)
    .load(path)
    .optionalTransform(GlideRoundTransform())
    .placeholder(R.drawable.placeholder_banner)
    .into(iv)
 */
@GlideModule
class CustomAppGlideModule : AppGlideModule() {

    /**
     * 通过GlideBuilder设置默认的结构(Engine,BitmapPool ,ArrayPool,MemoryCache等等).
     *
     * @param context
     * @param builder
     */
    override fun applyOptions(context: Context, builder: GlideBuilder) {

        //重新设置内存限制
        builder.setMemoryCache(LruResourceCache(10 * 1024 * 1024))

    }


    /**
     * 清单解析的开启
     *
     *
     * 这里不开启，避免添加相同的modules两次
     *
     * @return
     */
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    /**
     *
     * 为App注册一个自定义的String类型的BaseGlideUrlLoader
     * @param context
     * @param glide
     * @param registry
     */
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.append(
            String::class.java,
            InputStream::class.java,
            CustomBaseGlideUrlLoader.Factory()
        )
    }
}