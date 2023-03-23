package com.aprass.sportnews

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.aprass.sportnews.databinding.ActivityDetailNewsBinding
import com.aprass.sportnews.databinding.ActivityMainBinding
import com.bumptech.glide.Glide

class DetailNews : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailNewsBinding

    companion object {
        const val EXTRA_NEWS_OBJECT = "extra_news_object"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailNewsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val imgvImage: ImageView = binding.imgItemImage
        val tvTitle: TextView = binding.tvItemTitle
        val tvDate: TextView = binding.tvItemDate
        val tvContent: TextView = binding.tvItemContent

        val btnShare: Button = binding.actionShare


        val news = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_NEWS_OBJECT, News::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_NEWS_OBJECT)
        }

        if (news != null) {

            fun getImage(imageName: String) : Int {
                val drawableResourceId: Int = this.resources.getIdentifier(imageName, "drawable", this.packageName);
                return drawableResourceId;
            }

            Glide.with(this)
                .load(getImage(news.image))
                .into(imgvImage);

            tvTitle.text = news.title
            tvDate.text = news.date
            tvContent.text = news.content

            btnShare.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.action_share -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.putExtra(Intent.EXTRA_TEXT,
                    "${binding.tvItemTitle.text}\n\n${binding.tvItemContent.text.subSequence(0, 100)}...\n\n"
                            + "Want to read more?? check out my app at: https://just.random.link?id=" + BuildConfig.APPLICATION_ID);
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
            }
        }
    }
}