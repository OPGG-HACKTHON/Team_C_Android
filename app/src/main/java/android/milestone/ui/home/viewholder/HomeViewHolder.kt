package android.milestone.ui.home.viewholder

import android.milestone.databinding.ItemTinderBinding
import android.milestone.network.model.tinder.TinderModel
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

class HomeViewHolder(
    @LayoutRes layoutRes: Int,
    parent: ViewGroup?
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent?.context).inflate(layoutRes, parent, false)
) {
    val binding: ItemTinderBinding = DataBindingUtil.bind(itemView)!!

    fun bind(item: TinderModel) {
        binding.run {
            setVariable(BR.item, item)
            ivMore.setOnClickListener {
                // TODO: 2021-08-30 신고기능
            }
            tvCount.text = (item.dislike + item.like + item.superlike).toString()

            executePendingBindings()
        }
    }

}