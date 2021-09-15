package android.milestone.base

import android.milestone.BR
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<B : ViewDataBinding, T>(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup?
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent?.context).inflate(layoutResId, parent, false)
) {
     val binding: B = DataBindingUtil.bind(itemView)!!

    open fun bind(item: T, onClick: (T) -> Unit = {}) {
        try {
            binding.run {
                setVariable(BR.item, item)
                itemView.setOnClickListener {
                    onClick(item)
                }
                executePendingBindings()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    open fun setSingleSelectPosition(selectPosition: Int) {}
}