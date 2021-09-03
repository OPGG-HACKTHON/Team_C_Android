package android.milestone

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.regex.Pattern

fun CharSequence.isValidName(): Boolean {
    val patternString = Pattern.compile("^[a-zA-Z0-9가-힁ㄱ-ㅎ\\s]+$")
    return if (this.length < 2) {
        false
    } else {
        !this.first().isWhitespace() && patternString.matcher(this).matches()
    }
}

fun Fragment.toastShort(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Activity.toastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}