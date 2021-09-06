package android.milestone.ui

import android.milestone.R
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.jetbrains.annotations.NotNull

@BindingAdapter("setImage", "isCircleCrop", requireAll = false)
@NotNull
fun ImageView.setImage(imageUrl: String?, isCircleCrop: Boolean = false) {
    imageUrl?.let {
        var glide = Glide.with(this).load(it)

        if (isCircleCrop) {
            glide = glide.circleCrop()
        }

        glide.into(this)
    }
}

@BindingAdapter("setPositionImage")
@NotNull
fun ImageView.setPositionImage(position: String?) {
    setImageResource(
        when (position) {
            "sup" -> R.drawable.ic_sup
            "top" -> R.drawable.ic_top
            "mid" -> R.drawable.ic_mid
            "adc" -> R.drawable.ic_bottom
            else -> R.drawable.ic_jug
        }
    )
}