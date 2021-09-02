package android.milestone.ui.home.adapter

import android.milestone.R
import android.milestone.base.BaseDiffUtil
import android.milestone.network.model.tinder.TinderModel
import android.milestone.ui.home.viewholder.HomeViewHolder
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class HomeAdapter(private val onReportAction: (Int) -> Unit) :
    ListAdapter<TinderModel, HomeViewHolder>(BaseDiffUtil<TinderModel>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(R.layout.item_tinder, parent)


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position), onReportAction)
    }
}