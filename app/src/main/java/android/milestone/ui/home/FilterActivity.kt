package android.milestone.ui.home

import android.milestone.R
import android.milestone.databinding.ActivityFilterBinding
import android.milestone.network.model.auth.TeamInfoModel
import android.milestone.ui.home.adapter.FilterAdapter
import android.milestone.ui.home.viewmodel.FilterViewModel
import android.milestone.util.PrefUtil
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterActivity : AppCompatActivity() {

    private val viewModel: FilterViewModel by viewModels()
    private val filterAdapter: FilterAdapter by lazy {
        FilterAdapter { setFilter(it) }
    }
    private val unSelectTeamList = PrefUtil.getStringValue(PrefUtil.UNSELECT_TEAM_LIST, "")
        .split(",")
        .toMutableList()
    private lateinit var binding: ActivityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter)
        binding.lifecycleOwner = this
        initViews()
    }

    override fun onDestroy() {
        convertUnSelectTeamListToString()
        super.onDestroy()
        // TODO: 2021-09-05 파괴되기 전에 불려야하는데 파괴된 후 틴더 메세지가 호출된뒤 저장됨
    }

    private fun initViews() {
        initViewModels()
        binding.run {
            rvTeam.adapter = filterAdapter
            ivBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun convertUnSelectTeamListToString() {
        var filter = ""
        unSelectTeamList.forEachIndexed { index, i ->
            if (i != "") {
                filter += if (index != unSelectTeamList.size - 1) {
                    "$i,"
                } else {
                    i
                }
            }
        }
        PrefUtil.setStringValue(PrefUtil.UNSELECT_TEAM_LIST, filter)
    }

    private fun initViewModels() {
        viewModel.run {
            teamInfoResponse.observe(this@FilterActivity, {
                filterAdapter.submitList(it.data)
            })
        }
    }

    private fun setFilter(teamInfoModel: TeamInfoModel) {
        unSelectTeamList.run {
            val id = teamInfoModel.id.toString()
            if (contains(id)) {
                remove(id)
            } else {
                add(id)
            }
        }
    }
}