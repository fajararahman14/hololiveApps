package com.fajar.hololiveapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val holo = intent.getParcelableExtra<Holo>(MainActivity.INTENT_PARCELABLE)
        val holoPhoto = findViewById<ImageView>(R.id.iv_detail_photo)
        val holoName = findViewById<TextView>(R.id.tv_detail_name)
        val holoDetail = findViewById<TextView>(R.id.tv_detail_description)

        holoPhoto.setImageResource(holo?.photo!!)
        holoName.text = holo.name
        holoDetail.text = holo.detail
        val share_text_1_btn = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.share_text_1_btn)
        share_text_1_btn.setOnClickListener {
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Hey Check out this Great app:")
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }
    }
}