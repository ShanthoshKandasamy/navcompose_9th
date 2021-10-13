package com.bank.navcompose.bindings

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bank.navcompose.util.Resource


@BindingAdapter("dataVisibility")
fun<T> dataVisibility(view: RecyclerView, items: Resource<T>?){
    items?.let{
        if(items is Resource.Success<T>){
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.INVISIBLE
        }
    }
}

@BindingAdapter("errorVisibility")
fun<T> errorVisibility(view: TextView, items: Resource<T>?){
    items?.let {
        if(items is Resource.Error<T>){
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.INVISIBLE
        }
    }
}