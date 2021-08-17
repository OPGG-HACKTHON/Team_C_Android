package android.milestone

import java.util.regex.Pattern

fun CharSequence.isValidName(): Boolean {
    val patternString = Pattern.compile("^[a-zA-Z0-9가-힁ㄱ-ㅎ\\s]+$")
    return if (this.length < 2) {
        false
    } else {
        !this.first().isWhitespace() && patternString.matcher(this).matches()
    }
}
