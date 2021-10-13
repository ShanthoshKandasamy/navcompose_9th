package com.bank.navcompose.util

import android.widget.ImageView
import com.bank.navcompose.R
import com.squareup.picasso.Picasso

object Utils {


    fun loadImage(imageURL: String, view: ImageView) =
        Picasso.get()
            .load(imageURL)
            .placeholder(R.drawable.placeholder)
            .into(view)
}