package android.milestone.ui.main

import android.milestone.R
import android.milestone.databinding.ActivityMainBinding
import android.milestone.network.request.CreateTinderRequest
import android.milestone.toastShort
import android.milestone.ui.dialog.WriteTinderDialog
import android.milestone.ui.main.viewmodel.MainViewModel
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

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initViewModels()
        scheduleViewModel.updateData()
        rankingViewModel.initData()
    }


    private fun initViews() {
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        binding.navigation.setupWithNavController(navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            // binding.navigation.isVisible = destination.id !in listOf() // 추후 화면 추가됐을 때 사용
        }

        binding.navigation.selectedItemId = R.id.menu_home
        binding.navigation.menu[1].isChecked = true
        binding.navigation.transform(binding.fab, false)

        initNavigation(binding, navHostFragment)
    }

    private fun initViewModels() {
        viewModel.run {
            tinderState.observe(this@MainActivity, { state ->
                when (state) {
                    200 -> {
                        toastShort("메세지 전송성공")
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
                setIcon(if (it.itemId != R.id.menu_home) R.drawable.ic_home else R.drawable.ic_trans)
                title = (if (it.itemId != R.id.menu_home) getString(R.string.home) else "")
                isEnabled = it.itemId != R.id.menu_home
                setIcon(if (it.itemId != R.id.menu_home) R.drawable.ic_history else R.drawable.ic_trans)
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

    private fun showWriteTinderDialog() {
        val dialog = WriteTinderDialog.instance { msg ->
            viewModel.createTinder(CreateTinderRequest(msg))
        }

        dialog.show(supportFragmentManager, "")
    }
}