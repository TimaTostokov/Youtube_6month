package com.example.my.youtube_6month.ui.playlist

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my.youtube_6month.core.network.result.Resource
import com.example.my.youtube_6month.databinding.ActivityPlayListBinding
import com.example.my.youtube_6month.remote.model.Item
import com.example.my.youtube_6month.ui.MainViewModel
import com.example.my.youtube_6month.ui.detail.DetailActivity
import com.example.my.youtube_6month.ui.playlist.adapter.PlayListAdapter
import com.example.my.youtube_6month.uii.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayListActivity : BaseActivity<ActivityPlayListBinding, MainViewModel>() {

    private lateinit var adapter: PlayListAdapter

    override val viewModel: MainViewModel by viewModel()

    override fun isInternetAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    override fun initViews() {
        super.initViews()
        adapter = PlayListAdapter(this::onClick)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it

        }
        viewModel.getPlaylists().observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { it1 -> adapter.addList(it1.items) }
                    viewModel.loading.postValue(false)
                }

                Resource.Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }

                Resource.Status.ERROR -> {
                    viewModel.loading.postValue(false)
                }
            }
        }
    }

    override fun isConnection() {
        super.isConnection()
        if (!isInternetAvailable()) {
            binding.checkInternet.failInternet.isVisible = true
        }
    }

    override fun inflateViewBinding(): ActivityPlayListBinding {
        return ActivityPlayListBinding.inflate(layoutInflater)
    }

    private fun onClick(item: Item) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", item.id)
        intent.putExtra("title", item.snippet.title)
        intent.putExtra("desc", item.snippet.description)
        intent.putExtra("count", item.contentDetails.itemCount)
        startActivity(intent)
    }

}