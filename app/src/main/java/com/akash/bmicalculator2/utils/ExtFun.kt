package com.akash.bmicalculator2.utils

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import com.akash.bmicalculator2.Event
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

/**
 * Transforms static java function Snackbar.make() to an extension function on View.
 */
fun View.showSnackBar(snackBarText: String, snackBarLength: Int) {
    Snackbar.make(this, snackBarText, snackBarLength).show()
}

/**
 * Transforms static java function Snackbar.make() to an extension function on View.
 */
fun View.setupSnackbar(
    lifecycleOwner: LifecycleOwner,
    snackbarEvent: LiveData<Event<Int>>,
    timeLength: Int
) {
    snackbarEvent.observe(lifecycleOwner) { _event ->
        _event.getContentIfNotHandled()?.let {
            showSnackBar(context.getString(it), timeLength)
        }
    }
}

fun Date.getNowInString(): String {
    val pattern = "yyyy-MM-dd"
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
    return simpleDateFormat.format(this)
}


