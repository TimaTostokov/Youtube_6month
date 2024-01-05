package com.example.my.youtube_6month.glide

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(image:String) {
    Glide.with(this).load(image).into(this)
}