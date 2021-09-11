package android.milestone.ui.home.adapter

import android.animation.Animator
import android.milestone.R
import android.milestone.base.BaseRecyclerView
import android.milestone.base.BaseViewHolder
import android.milestone.databinding.ItemPogPlayerVoteBinding
import android.milestone.network.response.home.pog_list.PogListPlayer
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.airbnb.lottie.LottieAnimationView

class POGPlayerVoteAdapter(private val onClick: (PogListPlayer) -> Unit) :
    BaseRecyclerView<ItemPogPlayerVoteBinding, PogListPlayer>(
        R.layout.item_pog_player_vote,
        onClick
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemPogPlayerVoteBinding, PogListPlayer> {
        return POGPlayerVoteViewHolder(parent)
    }

}

class POGPlayerVoteViewHolder(parent: ViewGroup) :
    BaseViewHolder<ItemPogPlayerVoteBinding, PogListPlayer>(
        R.layout.item_pog_player_vote, parent
    ) {
    private val context = parent.context
    private var isCancel = false

    override fun bind(item: PogListPlayer, onClick: (PogListPlayer) -> Unit) {
        super.bind(item, onClick)
        itemView.setOnClickListener {
            onClick(item)
            initAnimation()
        }
    }

    private fun initAnimation() {
        isCancel = false
        val animationView = LottieAnimationView(context)
        animationView.apply {
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(48f.pixelToDp(), 100f.pixelToDp())
            setAnimation(R.raw.pog_heart)
            binding.clRoot.addView(this)
            setConstraint()
            playAnimation()
            setAnimatorListener(animationView)
        }
    }

    private fun LottieAnimationView.setAnimatorListener(animationView: LottieAnimationView) {
        addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                if (isCancel) {
                    animation?.cancel()
                    clearAnimation()
                }
            }

            override fun onAnimationEnd(animation: Animator?) {
                isCancel = false
                binding.clRoot.removeView(animationView)
            }

            override fun onAnimationCancel(animation: Animator?) {
                isCancel = true
            }

            override fun onAnimationRepeat(animation: Animator?) {}
        })
    }

    private fun LottieAnimationView.setConstraint() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.clRoot)

        constraintSet.connect(
            id,
            ConstraintSet.BOTTOM,
            binding.ivPlayer.id,
            ConstraintSet.TOP,
        )
        constraintSet.connect(
            id,
            ConstraintSet.START,
            binding.clRoot.id,
            ConstraintSet.START,
        )
        constraintSet.connect(
            id,
            ConstraintSet.END,
            binding.clRoot.id,
            ConstraintSet.END,
        )

        constraintSet.applyTo(binding.clRoot)
    }

    private fun Float.pixelToDp() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this,
        context.resources.displayMetrics
    ).toInt()
}