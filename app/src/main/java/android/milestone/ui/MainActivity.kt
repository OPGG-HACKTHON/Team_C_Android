package android.milestone.ui

import android.milestone.R
import android.milestone.databinding.ActivityMainBinding
import android.milestone.ui.ranking.RankingViewModel
import android.milestone.ui.schedule.ScheduleViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val scheduleViewModel: ScheduleViewModel by viewModels()

    private val rankingViewModel: RankingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        scheduleViewModel.updateData()
        rankingViewModel.initData()
    }

    private fun initViews() {
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        binding.navigation.setupWithNavController(navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            // binding.navigation.isVisible = destination.id !in listOf() // 추후 화면 추가됐을 때 사용
        }

        binding.navigation.selectedItemId = R.id.menu_home
        binding.navigation.menu[1].isChecked = true
        binding.navigation.transform(binding.fab, false)

        initNavigation(binding, navHostFragment)
    }

    private fun initNavigation(
        binding: ActivityMainBinding,
        navHostFragment: NavHostFragment
    ) {
        binding.fab.setOnClickListener {
            navHostFragment.findNavController().navigate(R.id.menu_home)
        }
        binding.navigation.menu.findItem(R.id.menu_home).apply {
        }

        binding.navigation.setOnItemSelectedListener {
            binding.navigation.menu.findItem(R.id.menu_home).apply {
                isEnabled = it.itemId != R.id.menu_home
                setIcon(if (it.itemId != R.id.menu_home) R.drawable.ic_home else R.drawable.ic_trans)
                title = (if (it.itemId != R.id.menu_home) getString(R.string.home) else "")
                binding.navigation.transform(binding.fab, it.itemId != R.id.menu_home)
            }

            when (it.itemId) {
                R.id.menu_history -> {
                    navHostFragment.findNavController().navigate(R.id.menu_history)
                }
                R.id.menu_home -> {
                    navHostFragment.findNavController().navigate(R.id.home)
                }
                else -> {
                    navHostFragment.findNavController().navigate(R.id.menu_schedule)
                }
            }
            true
        }
    }
}