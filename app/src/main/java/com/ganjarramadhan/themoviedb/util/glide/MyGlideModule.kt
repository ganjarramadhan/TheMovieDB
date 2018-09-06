package com.ganjarramadhan.themoviedb.util.glide

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * Created by ganjarramadhan on 22/12/17.
 * ganjar.ramadhan05@gmail.com
 */
@GlideModule
class MyGlideModule : AppGlideModule() {

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

}