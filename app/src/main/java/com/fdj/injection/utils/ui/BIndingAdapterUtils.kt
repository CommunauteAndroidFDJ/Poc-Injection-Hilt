package com.fdj.injection.utils.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("manageState")
fun manageRequestState(progressBar: ProgressBar, stateUi: UiRequestState) {
    progressBar.visibility = when (stateUi) {
        UiRequestState.SHOW_LOADING -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("setCircleImage")
fun setCircleImage(imageView: ImageView, imagePath: String) {
    Glide.with(imageView.context)
        .load(imagePath)
        .apply(RequestOptions().placeholder(ColorDrawable(Color.DKGRAY)))
        .circleCrop()
        .into(imageView)
}

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, imagePath: String) {
    Glide.with(imageView.context)
        .load(imagePath)
        .apply(RequestOptions().placeholder(ColorDrawable(Color.DKGRAY)))
        .centerCrop()
        .into(imageView)
}