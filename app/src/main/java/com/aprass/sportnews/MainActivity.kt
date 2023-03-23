package com.aprass.sportnews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aprass.sportnews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var rvNews: RecyclerView

    private val list = ArrayList<News>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create view binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        rvNews = binding.rvNews
        rvNews.setHasFixedSize(true)

        list.addAll(getListNews())
        showRecyclerList()
    }

    private fun getListNews(): ArrayList<News> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataDate = resources.getStringArray(R.array.data_date)
        val dataContent = resources.getStringArray(R.array.data_content)
        val dataImage = resources.getStringArray(R.array.data_image)

        val listHero = ArrayList<News>()

        for (i in dataTitle.indices) {
            val hero = News(dataTitle[i], dataDate[i], dataContent[i], dataImage[i])
            listHero.add(hero)
        }

        return listHero
    }

    private fun showRecyclerList() {
        rvNews.layoutManager = LinearLayoutManager(this)
        val listNewsAdapter = ListNewsAdapter(list)
        rvNews.adapter = listNewsAdapter

        listNewsAdapter.setOnItemClickCallback(object : ListNewsAdapter.OnItemClickCallback {
            override fun onItemClicked(news: News) {
                showSelectedNews(news)
            }
        })
    }

    private fun showSelectedNews(news: News) {
//        Toast.makeText(this, "Kamu memilih " + news.title, Toast.LENGTH_SHORT).show()
        val intentToDetail = Intent(this@MainActivity, DetailNews::class.java)
        intentToDetail.putExtra(DetailNews.EXTRA_NEWS_OBJECT, news)
        startActivity(intentToDetail)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val intentAbout = Intent(this@MainActivity, About::class.java)
                startActivity(intentAbout)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}