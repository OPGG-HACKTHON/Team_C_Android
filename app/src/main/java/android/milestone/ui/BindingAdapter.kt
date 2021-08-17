package android.milestone.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.jetbrains.annotations.NotNull

@BindingAdapter("bind:setImage")
@NotNull
fun ImageView.setImage(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(this)
            .load(it)
            .into(this)
    }
}