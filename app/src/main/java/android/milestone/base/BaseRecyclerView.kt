package android.milestone.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter

abstract class BaseRecyclerView<B : ViewDataBinding, T : Any>(
    @LayoutRes private val layoutResId: Int,
    private val onClick: (T) -> Unit = {}
) : ListAdapter<T, BaseViewHolder<B, T>>(BaseDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = object : BaseViewHolder<B, T>(layoutResId, parent) {}

    override fun onBindViewHolder(holder: BaseViewHolder<B, T>, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}