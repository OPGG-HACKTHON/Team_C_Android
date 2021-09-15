package android.milestone.ui.main

import android.milestone.R
import android.milestone.databinding.ActivityMainBinding
import android.milestone.toastShort
import android.milestone.ui.dialog.WriteTinderDialog
import android.milestone.ui.history.HistoryViewModel
import android.milestone.ui.main.viewmodel.MainViewModel
import android.milestone.ui.ranking.RankingViewModel
import android.milestone.ui.schedule.ScheduleViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), StartDestination {

    private val scheduleViewModel: ScheduleViewModel by viewModels()

    private val historyViewModel: HistoryViewModel by viewModels()

    private val rankingViewModel: RankingViewModel by viewModels()

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initViewModels()
        scheduleViewModel.updateData()
        rankingViewModel.updateData()
        historyViewModel.updateData()
    }

    private fun initViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        binding.navigation.setupWithNavController(navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.navigation.isVisible = destination.id !in listOf(
                R.id.fragment_match_detail,
                R.id.fragment_mypage,
                R.id.fragment_edit_nickname,
                R.id.fragment_setting,
            )

            if (!binding.navigation.isVisible) {
                outFromDestination()
            }
        }

        goToStartDestination()
        initNavigation(binding, navHostFragment)
        binding.navigation.transform(binding.fab, true)
    }

    private fun initViewModels() {
        viewModel.run {
            tinderState.observe(this@MainActivity, { state ->
                when (state) {
                    200 -> {
                        toastShort("메세지 전송성공")
                        historyViewModel.updateData()
                    }
                }
            })
        }
    }

    private fun initNavigation(
        binding: ActivityMainBinding,
        navHostFragment: NavHostFragment
    ) {
        binding.fab.setOnClickListener {
            showWriteTinderDialog()
        }

        binding.navigation.setOnItemSelectedListener {
            binding.navigation.menu.findItem(R.id.menu_home).apply {
                isEnabled = it.itemId != R.id.menu_home
                title = (if (it.itemId != R.id.menu_home) getString(R.string.home) else "")
                setIcon(if (it.itemId != R.id.menu_home) R.drawable.ic_home else R.drawable.ic_trans)
                binding.navigation.transform(binding.fab, it.itemId == R.id.menu_home)
            }

            when (it.itemId) {
                R.id.fragment_history -> {
                    navHostFragment.findNavController().navigate(R.id.fragment_history)
                }
                R.id.menu_home -> {
                    navHostFragment.findNavController().navigate(R.id.fragment_home)
                }
                else -> {
                    navHostFragment.findNavController().navigate(R.id.fragment_schedule)
                }
            }
            true
        }
    }

    private fun showWriteTinderDialog() {
        val dialog = WriteTinderDialog.instance(
            dialogHeightRatio = 0.8f,
            dialogWidthRatio = 0.9f,
            onSendAction = { msg ->
                viewModel.createTinder(msg)
            })

        dialog.show(supportFragmentManager, "")
    }

    override fun goToStartDestination() {
        binding.navigation.selectedItemId = R.id.menu_home
        binding.navigation.menu[1].isChecked = true
        binding.navigation.transform(binding.fab, true)
    }

    override fun outFromDestination() {
        binding.navigation.transform(binding.fab, false)
    }
}