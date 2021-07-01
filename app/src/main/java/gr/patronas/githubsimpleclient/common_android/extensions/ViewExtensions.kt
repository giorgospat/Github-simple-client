package gr.patronas.githubsimpleclient.common_android.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.displaySnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}