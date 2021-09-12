package android.milestone.ui.home.adapter

import android.milestone.R
import android.milestone.base.BaseDiffUtil
import android.milestone.databinding.ItemTinderBinding
import android.milestone.network.model.home.TinderModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter(private val onReportAction: (Int) -> Unit) :
    ListAdapter<TinderModel, HomeViewHolder>(BaseDiffUtil<TinderModel>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(R.layout.item_tinder, parent)


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position), onReportAction)
    }
}

class HomeViewHolder(
    @LayoutRes layoutRes: Int,
    parent: ViewGroup?
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent?.context).inflate(layoutRes, parent, false)
) {
    val binding: ItemTinderBinding = DataBindingUtil.bind(itemView)!!

    fun bind(item: TinderModel, onReportAction: (Int) -> Unit) {
        binding.run {
            setVariable(BR.item, item)
            ivMore.setOnClickListener {
                onReportAction(item.id)
            }
            tvCount.text = (item.dislike + item.like + item.superlike).toString()

            executePendingBindings()
        }
    }

}