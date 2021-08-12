package android.milestone.ui

import android.milestone.R
import android.milestone.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        binding.navigation.setupWithNavController(navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            // binding.navigation.isVisible = destination.id !in listOf() // 추후 화면 추가됐을 때 사용
        }

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
        binding.navigation.transform(binding.fab, true)

        binding.navigation.setOnItemSelectedListener {
            binding.navigation.menu.findItem(R.id.menu_home).apply {
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
}