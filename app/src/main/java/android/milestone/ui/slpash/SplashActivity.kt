package android.milestone.ui.slpash

import android.content.Intent
import android.milestone.Naming
import android.milestone.R
import android.milestone.databinding.ActivitySplashBinding
import android.milestone.ui.login.LoginActivity
import android.milestone.ui.main.MainActivity
import android.milestone.util.PrefUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)

        binding.layoutSplash.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                startFirstActivity()
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {}

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}
        })
    }

    private fun startFirstActivity() {
        if (PrefUtil.getStringValue(Naming.ACCESS_TOKEN, "").isBlank()) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.layoutSplash.startLayoutAnimation()
    }
}