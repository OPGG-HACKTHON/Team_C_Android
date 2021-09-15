package android.milestone.ui.home

import android.content.Intent
import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentHomeBinding
import android.milestone.network.request.UpdateLikeRequest
import android.milestone.toastShort
import android.milestone.ui.dialog.POGBottomSheetDialog
import android.milestone.ui.dialog.ReportTinderDialog
import android.milestone.ui.dialog.TutorialDialog
import android.milestone.ui.home.adapter.HomeAdapter
import android.milestone.ui.home.adapter.POGListTabAdapter
import android.milestone.ui.home.viewmodel.HomeViewModel
import android.milestone.util.PrefUtil
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), CardStackListener {

    private val viewModel: HomeViewModel by activityViewModels()
    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter { tinderId ->
            viewModel.setCurrentTinderId(tinderId)
            showReportDialog()
        }
    }

    private val cardStackLayoutManager by lazy {
        CardStackLayoutManager(requireContext(), this)
            .apply {
                setCanScrollVertical(true)
                setCanScrollHorizontal(true)
                setDirections(Direction.FREEDOM)
            }
    }

    override fun initViews() {
        if (PrefUtil.getBooleanValue("first", true)) {
            showTutorialDialog()
        }
        initViewModels()
        binding.run {
            cvTinder.apply {
                if (layoutManager == null) {
                    layoutManager = cardStackLayoutManager
                }
                adapter = homeAdapter
            }
            lvPass.setOnClickListener {
                setSwipeAnimationSetting(Direction.Bottom)
                lvPass.playAnimation()
            }
            lvDislike.setOnClickListener {
                setSwipeAnimationSetting(Direction.Left)
                lvDislike.playAnimation()
            }

            lvLike.setOnClickListener {
                setSwipeAnimationSetting(Direction.Right)
                lvLike.playAnimation()
            }
            lvBest.setOnClickListener {
                setSwipeAnimationSetting(Direction.Top)
                lvBest.playAnimation()
            }
            ivFilter.setOnClickListener {
                val intent = Intent(requireContext(), FilterActivity::class.java)
                startActivity(intent)
            }
            ivPogStatus.setOnClickListener {
                val status = viewModel.currentGameResponse.value?.data?.status
                if (status == 1 || status == 0) {
                    val dialog = POGBottomSheetDialog.instance()
                    dialog.show(parentFragmentManager, "")
                } else {
                    toastShort("진행중인 경기가 없습니다.")
                }
            }
            ivMy.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionFragmentMypage())
            }
            pager.adapter = POGListTabAdapter(this@HomeFragment)
        }
    }

    private fun showTutorialDialog() {
        val dialog = TutorialDialog.instance(
            dialogHeightRatio = 0.95f,
            dialogWidthRatio = 1f
        )
        dialog.show(parentFragmentManager, "")
    }

    private fun setSwipeAnimationSetting(direction: Direction) {
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(direction)
            .setDuration(1000)
            .setInterpolator(AccelerateInterpolator())
            .build()
        cardStackLayoutManager.setSwipeAnimationSetting(setting)
        binding.run {
            cvTinder.swipe()
        }
    }

    private fun initViewModels() {
        viewModel.run {
            getTinder()
            tinderResponse.observe(viewLifecycleOwner, { tinderResponse ->
                homeAdapter.submitList(tinderResponse.data)
            })
            pogListResponse.observe(viewLifecycleOwner, { pogListResponse ->
                TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
                    val pogListDataResponse = pogListResponse.data
                    tab.text =
                        if (position == 0) {
                            pogListDataResponse.aTeam.name
                        } else {
                            pogListDataResponse.bTeam.name
                        }
                }.attach()
            })
            postPogVoteResponse.observe(viewLifecycleOwner, {
                binding.clTinder.isVisible = true
                binding.clPogVote.isVisible = false
                initTimer()
            })

            progress.observe(viewLifecycleOwner, {
                binding.progress.progress = it
            })

            timerCount.observe(viewLifecycleOwner, {
                binding.tvTime.text = getString(R.string.timer, it)
            })

            scheduleData.observe(viewLifecycleOwner, { scheduleData ->
                binding.run {
                    if (scheduleData == null) {
                        itemGameScore.clNoGame.isVisible = true
                        itemGameScore.clGame.isVisible = false
                    } else {
                        itemGameScore.clNoGame.isVisible = false
                        itemGameScore.clGame.isVisible = true
                        item = scheduleData
                        itemGameScore.tvFirstTeamScore.setTextColor(
                            ContextCompat.getColor(
                                root.context,
                                scheduleData.teamAScoreColor
                            )
                        )
                        itemGameScore.tvSecondTeamScore.setTextColor(
                            ContextCompat.getColor(
                                root.context,
                                scheduleData.teamBScoreColor
                            )
                        )
                    }
                }
            })
            currentGameResponse.observe(viewLifecycleOwner, {
                binding.itemGameScore.run {
                    if (it.data?.status == -1) {
                        llScore.isVisible = false
                        tvStartTime.isVisible = true
                    } else {
                        llScore.isVisible = true
                        tvStartTime.isVisible = false
                    }
                }
            })
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {}

    override fun onCardSwiped(direction: Direction?) {
        if (cardStackLayoutManager.topPosition == homeAdapter.itemCount) {
            viewModel.getTinder()
            binding.clTinder.isVisible = false
            binding.clPogVote.isVisible = true
            lifecycleScope.launch {
                viewModel.getPogList()
            }
        }
        updateLike(direction)
    }

    private fun updateLike(direction: Direction?) {
        val tinderId = homeAdapter.currentList[cardStackLayoutManager.topPosition - 1].id
        val updateLikeRequest = when (direction) {
            Direction.Left -> UpdateLikeRequest(tinderId, 0, 1, 0, 0)
            Direction.Right -> UpdateLikeRequest(tinderId, 1, 0, 0, 0)
            Direction.Top -> UpdateLikeRequest(tinderId, 0, 0, 1, 0)
            else -> UpdateLikeRequest(tinderId, 0, 0, 0, 1)
        }
        viewModel.updateLike(updateLikeRequest)
    }

    override fun onCardRewound() {}

    override fun onCardCanceled() {}

    override fun onCardAppeared(view: View?, position: Int) {}

    override fun onCardDisappeared(view: View?, position: Int) {}

    private fun showReportDialog() {
        val dialog = ReportTinderDialog.instance(
            dialogHeightRatio = 0.8f,
            dialogWidthRatio = 0.9f
        )
        dialog.show(parentFragmentManager, "")
    }
}