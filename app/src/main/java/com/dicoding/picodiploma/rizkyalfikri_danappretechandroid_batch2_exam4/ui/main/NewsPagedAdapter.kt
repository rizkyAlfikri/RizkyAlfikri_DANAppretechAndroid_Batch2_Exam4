package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.R
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.entity.NewsEntity
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.databinding.ItemNewsBinding
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.ui.main.NewsPagedAdapter.ModelViewHolder

class NewsPagedAdapter(private val listener: (NewsEntity) -> Unit) :
    PagedListAdapter<NewsEntity, ModelViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<NewsEntity> =
            object : DiffUtil.ItemCallback<NewsEntity>() {

                override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: NewsEntity,
                    newItem: NewsEntity
                ): Boolean {
                    return oldItem.title == newItem.title
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val binding: ItemNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_news, parent, false
        )

        return ModelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        val modelEntity = getItem(position)
        modelEntity?.let { holder.bindData(it, listener) }
    }

    inner class ModelViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(newsEntity: NewsEntity, listener: (NewsEntity) -> Unit) {
            binding.dataNews = newsEntity
            Glide.with(binding.root).load(newsEntity.imageUrl).placeholder(R.drawable.loading_image)
                .error(R.drawable.broken_image).into(binding.imgNews)
            binding.root.setOnClickListener {
                listener(newsEntity)
            }
        }
    }
}