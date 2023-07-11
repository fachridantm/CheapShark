package com.ewide.test.fachridan.core.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Parcelable
import android.widget.Toast
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

@Suppress("DEPRECATION")
inline fun <reified T : Parcelable> Intent.getParcelableData(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key)
    else -> getParcelableExtra(key) as? T
}