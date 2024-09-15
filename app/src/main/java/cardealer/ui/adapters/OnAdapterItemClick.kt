package com.example.autohub.ui.adapters

fun interface OnAdapterItemClick<T> {
    fun onClick(t: T)
}