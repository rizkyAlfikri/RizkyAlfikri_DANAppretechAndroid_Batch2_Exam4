package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.R
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.databinding.MainFragmentBinding
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.detail.DetailNewsActivity
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.detail.DetailNewsActivity.Companion.EXTRA_NEWS
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.utils.invisible
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.utils.showToast
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.utils.visible
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.viewmodel.ViewModelFactory
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.vo.Status.*

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var newsAdapter: NewsPagedAdapter
    private var mainViewModel: MainViewModel? = null

    companion object {
        fun newInstance() = MainFragment()

        private fun getViewModel(activity: FragmentActivity): MainViewModel? {
            val factory = ViewModelFactory.getInstance(activity.application)
            return factory?.let { ViewModelProvider(activity, it).get(MainViewModel::class.java) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initializeViewModel()
        initializeAdapter()
        initializeRecyclerView()
        swipeListener()
        loadData()

    }

    private fun initializeViewModel() {
        mainViewModel = getViewModel(requireActivity())
    }

    private fun initializeAdapter() {
        newsAdapter = NewsPagedAdapter {
            val intent = Intent(context, DetailNewsActivity::class.java)
            intent.putExtra(EXTRA_NEWS, it)
            startActivity(intent)
        }
    }

    private fun initializeRecyclerView() {
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }

    private fun swipeListener() {
        binding.swLayout.setOnRefreshListener {
            binding.swLayout.isRefreshing = true
            mainViewModel?.setRefresh(binding.swLayout.isRefreshing)
        }
    }

    private fun loadData() {
        mainViewModel?.let {
            it.getModelLiveData.observe(viewLifecycleOwner, Observer { resource ->

                when (resource.status) {
                    SUCCESS -> {
                        binding.progressBar.invisible()
                        newsAdapter.submitList(resource.body)
                        binding.swLayout.isRefreshing = false
                    }

                    LOADING -> {
                        binding.progressBar.visible()
                    }

                    ERROR -> {
                        binding.progressBar.invisible()
                        binding.swLayout.isRefreshing = false
                        context?.showToast("${resource.message}")
                    }
                }
            })
        }
    }
}
