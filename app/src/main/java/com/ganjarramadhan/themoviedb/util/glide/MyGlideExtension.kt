package com.ganjarramadhan.themoviedb.util.glide

import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.decodeTypeOf

/**
 * Created by ganjarramadhan on 22/12/17.
 * ganjar.ramadhan05@gmail.com
 */
@GlideExtension
object MyGlideExtension {

//    private val DECODE_TYPE_GIF = decodeTypeOf(GifDrawable::class.java).lock()

    // make rounded
    @GlideOption
    @JvmStatic
    fun asRoundedImage(option: RequestOptions) {
        option.optionalCircleCrop()
    }

    // make glide support gif
    /*@GlideType(GifDrawable::class)
    @JvmStatic
    fun asGif(requestBuilder: RequestBuilder<GifDrawable>) {
        requestBuilder
                .transition(DrawableTransitionOptions())
                .apply(DECODE_TYPE_GIF)
    }*/

}