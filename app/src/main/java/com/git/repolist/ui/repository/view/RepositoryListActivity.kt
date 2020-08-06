package com.git.repolist.ui.repository.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.git.repolist.R
import com.git.repolist.data.model.Repository
import com.git.repolist.databinding.ActivityRepositoryListBinding
import com.git.repolist.ui.repository.adapter.RepositoryListAdapter
import com.git.repolist.ui.repository.viewmodel.RepositoryViewModel
import kotlinx.android.synthetic.main.activity_repository_list.*


class RepositoryListActivity : AppCompatActivity() {

    lateinit var binding: ActivityRepositoryListBinding
    private lateinit var repositoryViewModel: RepositoryViewModel
    private lateinit var adapter: RepositoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
        setupUI()
    }

    private fun setupDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repository_list)
        repositoryViewModel = ViewModelProviders.of(this).get(RepositoryViewModel::class.java)

        repositoryViewModel.repositoryList.observe(this, Observer { repositoryList ->
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            layoutRetry.visibility = View.GONE

            showRepositoryList(repositoryList)
        })

        repositoryViewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
                layoutRetry.visibility = View.GONE
            }
        })

        repositoryViewModel.showRetry.observe(this, Observer { shouldShowRetry ->
            if (shouldShowRetry) {
                displayRetryLayout()
            }
        })

        repositoryViewModel.errorMessage.observe(this, Observer {
                errorMessage -> if (errorMessage != null) displayRetryLayout() else hideRetryLayout()
        })

        binding.viewModel = repositoryViewModel
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RepositoryListAdapter(arrayListOf()) { repo : Repository -> repositoryClicked(repo) }
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun showRepositoryList(repositories: List<Repository>) {
        adapter.apply {
            addRepositories(repositories)
            notifyDataSetChanged()
        }
    }

    private fun repositoryClicked(repository : Repository) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(repository.htmlUrl))
        startActivity(browserIntent)
    }

    /**
     * Layout visibility
     *
     */

    private fun displayRetryLayout() {
        recyclerView.visibility = View.GONE
        layoutRetry.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    private fun hideRetryLayout() {
        layoutRetry.visibility = View.GONE
    }

}
