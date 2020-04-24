package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.MainActivity
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.R
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.StatusResponse
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.remote.response.ProfileRequest
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.databinding.ActivityLoginBinding
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.utils.EMAIL
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.utils.PASSWORD
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.utils.showToast
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.viewmodel.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var loginViewModel: LoginViewModel? = null

    companion object {
        private fun getViewModel(activity: AppCompatActivity): LoginViewModel? {
            val factory = ViewModelFactory.getInstance(activity.application)
            return factory?.let { ViewModelProvider(activity, it).get(LoginViewModel::class.java) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)
        initViewModel()
        requestLoginToServer()
        clickLogin()
        onClickComingSoon()

    }

    private fun initViewModel() {
        loginViewModel = getViewModel(this@LoginActivity)
    }

    private fun onClickComingSoon() {
        binding.tvSignUp.setOnClickListener {
            this.showToast(getString(R.string.sign_up))
        }
    }

    private fun clickLogin() {
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            when {
                email.isEmpty() -> binding.tvEmail.error = getString(R.string.error_email)
                password.isEmpty() -> binding.tvPassword.error = getString(R.string.error_pass)
                else -> validationLogin(email, password)
            }
        }
    }

    private fun validationLogin(email: String, password: String) {
        if ((email == EMAIL) && (password == PASSWORD)) {
            loginViewModel?.setLoginRequest(ProfileRequest(email, password))
        } else {
            this.showToast(getString(R.string.login_failed))
        }
    }

    private fun requestLoginToServer() {
        loginViewModel?.getLoginRequest?.observe(this@LoginActivity, Observer { response ->
            when(response.status){
                StatusResponse.SUCCESS -> {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    this.showToast(getString(R.string.login_success))
                    finish()
                }

                StatusResponse.EMPTY -> {
                    this.showToast(getString(R.string.login_failed))
                }

                StatusResponse.ERROR -> {
                    this.showToast(getString(R.string.login_failed))
                }
            }
        })
    }
}
