package android.milestone

import java.util.regex.Pattern

fun CharSequence.validation(): Boolean {
    val ps = Pattern.compile("^[a-zA-Z0-9가-힁ㄱ-ㅎ\\s]+$")
    return if (this.length < 2) {
        false
    } else {
        !this.first().isWhitespace() && ps.matcher(this).matches()
    }
}
