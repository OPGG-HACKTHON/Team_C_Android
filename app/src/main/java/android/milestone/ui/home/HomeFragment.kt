package android.milestone.ui.home

import android.content.Intent
import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentHomeBinding
import android.milestone.databinding.ItemGameScoreBinding
import android.milestone.network.model.home.CurrentGameModel
import android.milestone.network.request.CreateReportRequest
import android.milestone.network.request.UpdateLikeRequest
import android.milestone.toastShort
import android.milestone.ui.dialog.ReportTinderDialog
import android.milestone.ui.home.adapter.HomeAdapter
import android.milestone.ui.home.viewmodel.HomeViewModel
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import dagger.hilt.android.AndroidEntryPoint
import java.util.logging.Filter

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

    override fun onStart() {
        super.onStart()
        viewModel.getTinder()
    }

    override fun initViews() {
        initViewModels()
        binding.run {
            cvTinder.apply {
                layoutManager = cardStackLayoutManager
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
        }
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
            tinderResponse.observe(viewLifecycleOwner, { tinderResponse ->
                homeAdapter.submitList(tinderResponse.data)
            })

            rootResponse.observe(viewLifecycleOwner, { rootResponse ->
                if (rootResponse.success) {
                    toastShort(rootResponse.data)
                } else {
                    toastShort(rootResponse.msg)
                }
            })

            reportMessage.observe(viewLifecycleOwner, { reportMessage ->
                currentTinderId.value?.let { currentTinderId ->
                    createReport(CreateReportRequest(currentTinderId, reportMessage))
                }
            })

            currentGameResponse.observe(viewLifecycleOwner, { currentGameResponse ->
                val currentGameModel = currentGameResponse.data

                if (currentGameModel == null) {
                    // TODO: 2021-09-04 경기 없음 처리
                } else {
                    binding.itemGameScore.run {
                        currentModel = currentGameModel
                        setCurrentScoreTextColor(currentGameModel)
                    }
                    // TODO: 2021-09-04 경기 시작전 시간 카운트 처리
                    // TODO: 2021-09-04 어떻게 계속 데이터를 갱신할건지 고민
                }
            })
        }
    }

    private fun ItemGameScoreBinding.setCurrentScoreTextColor(currentGameModel: CurrentGameModel) {
        when {
            currentGameModel.aTeamScore > currentGameModel.bTeamScore -> {
                tvFirstTeamScore.setTextColor(requireContext().getColor(R.color.blue500))
                tvSecondTeamScore.setTextColor(requireContext().getColor(R.color.gray300))
            }
            currentGameModel.aTeamScore == currentGameModel.bTeamScore -> {
                tvFirstTeamScore.setTextColor(requireContext().getColor(R.color.gray300))
                tvSecondTeamScore.setTextColor(requireContext().getColor(R.color.gray300))
            }
            else -> {
                tvFirstTeamScore.setTextColor(requireContext().getColor(R.color.gray300))
                tvSecondTeamScore.setTextColor(requireContext().getColor(R.color.blue500))
            }
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {}

    override fun onCardSwiped(direction: Direction?) {
        if (cardStackLayoutManager.topPosition == homeAdapter.itemCount - 2) {
            viewModel.getTinder()
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
        val dialog = ReportTinderDialog.instance()
        dialog.show(parentFragmentManager, "")
    }
}