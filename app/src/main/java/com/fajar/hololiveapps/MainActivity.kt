package com.fajar.hololiveapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    companion object {
        const val INTENT_PARCELABLE = "intent_parcelable"
    }

    private lateinit var rvHolo : RecyclerView
    private val list = ArrayList<Holo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvHolo = findViewById(R.id.rv_hololive)
        rvHolo.setHasFixedSize(true)

        list.addAll(getListHolo())
        showRecyclerList()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.action_about -> {
//                val intent = Intent(this@MainActivity, AboutActivity::class.java)
//                startActivity(intent)
//            }
            R.id.action_list -> {
                rvHolo.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvHolo.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun getListHolo(): ArrayList<Holo> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_desc)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val holoDetail = resources.getStringArray(R.array.data_detail)
        val listHolo = ArrayList<Holo>()
        for (i in dataName.indices) {
            val holo = Holo(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), holoDetail[i])
            listHolo.add(holo)
        }
        return listHolo
    }

    private fun showRecyclerList() {
        rvHolo.layoutManager = LinearLayoutManager(this)
        val listHoloAdapter = ListHoloAdapter(list)
        rvHolo.adapter = listHoloAdapter

        listHoloAdapter.setOnItemClickCallback(object : ListHoloAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Holo) {
                showSelectedHolo(data)
            }
        })
    }

    private fun showSelectedHolo(holo: Holo) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(INTENT_PARCELABLE, holo)
        startActivity(intent)
    }
}
