package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.R
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.databinding.ActivitySplashBinding
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@SplashActivity, R.layout.activity_splash)

        val anim = AnimationUtils.loadAnimation(this, R.anim.fade)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }

            override fun onAnimationStart(p0: Animation?) {

            }
        })

        binding.tvSplash.startAnimation(anim)
    }
}
