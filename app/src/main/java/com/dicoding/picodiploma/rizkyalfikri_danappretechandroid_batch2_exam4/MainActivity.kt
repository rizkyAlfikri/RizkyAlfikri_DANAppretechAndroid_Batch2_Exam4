package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.databinding.MainActivityBinding
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.main.MainFragment
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.main.MainViewModel
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.utils.setWhiteStatusBarText
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private var mainViewModel: MainViewModel? = null
    private lateinit var binding: MainActivityBinding

    companion object {
        private fun getViewModel(activity: AppCompatActivity): MainViewModel? {
            val factory = ViewModelFactory.getInstance(activity.application)
            return factory?.let { ViewModelProvider(activity, it).get(MainViewModel::class.java) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.main_activity)
        window.setWhiteStatusBarText(this@MainActivity)
        mainViewModel = getViewModel(this@MainActivity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
