package com.ewide.test.fachridan.core.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.os.Build.VERSION.SDK_INT
import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.load.engine.GlideException
import com.ewide.test.fachridan.core.di.GlideApp
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject
import retrofit2.HttpException

fun HttpException.getErrorMessage(): String? {
    val response = this.response()?.errorBody()?.string()
    if (response.isNullOrEmpty()) return "Something went wrong"
    return try {
        val jsonObject = JSONObject(response)
        jsonObject.getString("message")
    } catch (e: Exception) {
        e.printStackTrace()
        e.message.toString()
    }
}

fun String.showToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun String.showSnackBar(context: Context) {
    Snackbar.make(
        (context as Activity).findViewById(android.R.id.content),
        this,
        Snackbar.LENGTH_SHORT
    ).show()
}

fun TextView.showStrikeThrough() {
    paintFlags = paintFlags or STRIKE_THRU_TEXT_FLAG
}

fun ImageView.loadGameImage(url: String) {
    try {
        GlideApp.with(this.context)
            .load(url)
            .into(this)
    } catch (e: GlideException) {
        e.logRootCauses("GlideException")
    }
}

fun String.convertToPrice(): String {
    return "$$this"
}

@Suppress("DEPRECATION")
inline fun <reified T : Parcelable> Intent.getParcelableData(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key)
    else -> getParcelableExtra(key) as? T
}