package android.milestone.ui

import android.milestone.R
import android.milestone.util.ReadableDateTime
import android.view.View
import android.widget.ImageView
import android.widget.TextView
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

@BindingAdapter("setPogRate")
@NotNull
fun View.setPogRate(rate: Float) {
    val params = layoutParams
    params.width = 10 + (rate.toInt() * 5)
    layoutParams = params
}

@BindingAdapter("setPogRateBackground")
@NotNull
fun View.setPogRateBackground(rateRank: Int) {
    val backgroundArray by lazy { resources.obtainTypedArray(R.array.pog_bar_background) }

    setBackgroundResource(
        backgroundArray.getResourceId(
            rateRank - 1,
            R.drawable.shape_rect_radius3_blue200
        )
    )
}

@BindingAdapter("setCreatedAt")
@NotNull
fun TextView.setCreatedAt(createdAt: String) {
    text = ReadableDateTime.from(createdAt).toMinuteDifference()
}