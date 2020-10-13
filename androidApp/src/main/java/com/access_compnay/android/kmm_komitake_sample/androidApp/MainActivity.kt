package com.access_compnay.android.kmm_komitake_sample.androidApp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.access_compnay.android.kmm_komitake_sample.androidApp.databinding.ActivityMainBinding
import com.access_compnay.android.kmm_komitake_sample.shared.*
import kotlinx.coroutines.*

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RepositoryDataAdapter
    private val repositoryList: MutableList<RepositoryData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = greet()

        adapter = RepositoryDataAdapter(this, repositoryList)
        binding.listView.adapter = adapter

        getRepos()
    }

    private fun getRepos() = launch {
        val newList = withContext(Dispatchers.IO) {
            val githubApi = GitHubApi(GITHUB_API_TOKEN)

            val store = ApiDataStore(githubApi)
            store.getRepos()
        }
        repositoryList.run {
            clear()
            addAll(newList)
        }
        adapter.notifyDataSetChanged()
    }
}

class RepositoryDataAdapter(context: Context, repos: List<RepositoryData>) : ArrayAdapter<RepositoryData>(context, 0, repos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView
            ?: LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, null)
        val item = getItem(position)!!

        view.findViewById<TextView>(android.R.id.text1).text = item.name
        view.findViewById<TextView>(android.R.id.text2).text = item.defaultBranch

        return view
    }
}