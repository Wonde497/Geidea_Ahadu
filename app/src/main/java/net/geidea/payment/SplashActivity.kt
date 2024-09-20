package net.geidea.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import net.geidea.payment.databinding.ActivitySplashBinding
import net.geidea.payment.kernelconfig.view.KernelConfigActivity
import net.geidea.payment.utils.IS_KERNEL_CONFIG_COMPLETED
import net.geidea.utils.DataStoreUtils
import net.geidea.utils.extension.ioCoroutine
import net.geidea.payment.MainMenuActivity

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.splashImage.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.splash_animation
            )
        )
        ioCoroutine {
            delay(2000)
            val isKernelConfigured = DataStoreUtils.getInstance(this@SplashActivity).getBoolean(
                IS_KERNEL_CONFIG_COMPLETED
            ) ?: false
            if (isKernelConfigured) {
                val intent = Intent(this@SplashActivity, MainMenuActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@SplashActivity, KernelConfigActivity::class.java)
                startActivity(intent)
            }
        }
    }
}