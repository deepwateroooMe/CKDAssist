package com.me.ckdassist.util

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.me.ckdassist.R
import kotlinx.coroutines.ExperimentalCoroutinesApi

// const val DEFAULT_TOPIC_IMAGE = R.drawable.ic_nav_diary_normal
val DEFAULT_TOPIC_IMAGE = R.drawable.empty_plate

@SuppressLint("UnrememberedMutableState")
@ExperimentalCoroutinesApi
@Composable
fun loadPicture(url: String, @DrawableRes defaultImage: Int): MutableState<Bitmap?> {
    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)

    // show default image while image loads
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(defaultImage)
        .into(object : CustomTarget<Bitmap>() {
                  override fun onLoadCleared(placeholder: Drawable?) { }
                  override fun onResourceReady(
                      resource: Bitmap,
                      transition: Transition<in Bitmap>?
                  ) {
                      bitmapState.value = resource
                  }
              })

    // get network image
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
                  override fun onLoadCleared(placeholder: Drawable?) { }
                  override fun onResourceReady(
                      resource: Bitmap,
                      transition: Transition<in Bitmap>?
                  ) {
                      bitmapState.value = resource
                  }
              })

    return bitmapState
}


@SuppressLint("UnrememberedMutableState")
@ExperimentalCoroutinesApi
@Composable
fun loadPicture(@DrawableRes drawable: Int): MutableState<Bitmap?> {

    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(drawable)
        .into(object : CustomTarget<Bitmap>() {
                  override fun onLoadCleared(placeholder: Drawable?) { }
                  override fun onResourceReady(
                      resource: Bitmap,
                      transition: Transition<in Bitmap>?
                  ) {
                      bitmapState.value = resource
                  }
              })

    return bitmapState
}
