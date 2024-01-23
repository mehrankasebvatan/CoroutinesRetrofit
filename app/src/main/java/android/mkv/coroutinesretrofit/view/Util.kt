package android.mkv.coroutinesretrofit.view

import android.mkv.coroutinesretrofit.R
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(url: String) {
    val options = RequestOptions()
        .error(R.mipmap.ic_launcher_round)
    Glide
        .with(this.context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}