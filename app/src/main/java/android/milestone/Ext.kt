package android.milestone

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.regex.Pattern

fun String.isValidName(): Pair<Boolean, String> {
    val patternString = Pattern.compile("^[a-zA-Z0-9가-힁ㄱ-ㅎ\\s]+$")
    return if (this.length < 2) {
        false to App.context().getString(R.string.fragment_nickname_waring_length)
    } else {
        if (!this.first().isWhitespace() && patternString.matcher(this).matches()) {
            true to ""
        } else {
            false to App.context().getString(R.string.fragment_nickname_waring_wrong)
        }
    }
}

fun Fragment.toastShort(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Activity.toastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}