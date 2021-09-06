package android.milestone.ui.home

import android.milestone.R
import android.milestone.databinding.ActivityFilterBinding
import android.milestone.ui.home.adapter.FilterAdapter
import android.milestone.ui.home.viewmodel.FilterViewModel
import android.milestone.util.PrefUtil
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterActivity : AppCompatActivity() {

    private val viewModel: FilterViewModel by viewModels()
    private val filterAdapter: FilterAdapter by lazy {
        FilterAdapter { viewModel.setFilter(it) }
    }

    private lateinit var binding: ActivityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter)
        binding.lifecycleOwner = this
        initViews()
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

    private fun initViewModels() {
        viewModel.run {
            teamInfoResponse.observe(this@FilterActivity, {
                filterAdapter.submitList(it.data)
            })
            unSelectTeamString.observe(this@FilterActivity, {
                PrefUtil.setStringValue(PrefUtil.UNSELECT_TEAM_LIST, it)
            })
        }
    }
}