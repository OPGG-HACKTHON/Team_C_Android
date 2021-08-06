package android.milestone

import android.milestone.databinding.ActivityMainBinding
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        binding.navigation.setupWithNavController(navHostFragment.navController)
        binding.navigation.menu.findItem(R.id.menu_empty).isEnabled = false

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            // binding.navigation.isVisible = destination.id !in listOf() // 추후 화면 추가됐을 때 사용
        }

        binding.fab.setOnClickListener {
            navHostFragment.findNavController().navigate(R.id.home)
        }
    }
}