package com.task.utils

import android.app.Service
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar


fun View.showKeyboard() {
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.showSoftInput(this, 0)
}

fun View.hideKeyboard() {
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toInvisible() {
    this.visibility = View.GONE
}

fun View.showSnackbar(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    val snackBar = Snackbar.make(this, resources.getString(messageRes), length)
    snackBar.f()
    snackBar.show()
}

fun View.showSnackbar(messageRes: String, length: Int = Snackbar.LENGTH_LONG) {
    val snackBar = Snackbar.make(this,messageRes, length)
    snackBar.show()
}
