package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.utils

import android.content.Context
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.R
import com.google.android.material.appbar.AppBarLayout

const val BASE_URL = "http://18.139.222.3:9801/"
const val API_KEY = "xxxxxx"
const val EMAIL = "user@gmail.com"
const val PASSWORD = "rahasia123"

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun Window.setWhiteStatusBarText(context: Context) {
    this.statusBarColor = ContextCompat.getColor(context, R.color.white)
    val decorView = this.decorView

    // make status bar font text color white
    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

}

fun AppBarLayout.changeTitleColorDynamically(context: Context, title: TextView) {
    var scrollRange = -1

    this.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
        if (scrollRange == -1) {
            scrollRange = appBarLayout.totalScrollRange
        }

        if (scrollRange + verticalOffset == 0) {
            title.setTextColor(ContextCompat.getColor(context, R.color.primary_text))
        } else {
            title.setTextColor(ContextCompat.getColor(context, R.color.white))
        }
    })
}
