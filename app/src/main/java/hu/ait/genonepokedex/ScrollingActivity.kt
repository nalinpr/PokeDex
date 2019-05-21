package hu.ait.genonepokedex

//YOOOOOO GET THOSE POKESSSSS

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import hu.ait.genonepokedex.adapter.PokeAdapter
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.poke_card.*

class ScrollingActivity : AppCompatActivity(), PokeAdapter.OnPokeListener {

    companion object {
        val POKE_NUM = "POKE_NUM"
        val IMG_URL = "IMG_URL"
    }

    lateinit var pokeAdapter : PokeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
//        fab.setOnClickListener { view ->
//            val intentDetails = Intent()
//            intentDetails.setClass(this@ScrollingActivity,
//                PopDetailsActivity::class.java)
//
//            intentDetails.putExtra(POKE_NAME, "bulbasaur")
//            intentDetails.putExtra(IMG_URL, "/")
//            startActivity(intentDetails)
//        }

        pokeAdapter = PokeAdapter(this)

        val layoutManager = GridLayoutManager(this,3)
        recyclerPoke.layoutManager = layoutManager
        recyclerPoke.adapter = pokeAdapter

    }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPokeClick(position: Int) {
        val poke = pokeAdapter.pokeItems.get(position)

        val intentDetails = Intent()
        intentDetails.setClass(this@ScrollingActivity,
            PopDetailsActivity::class.java)

        intentDetails.putExtra(IMG_URL, poke.imgUrl)
        intentDetails.putExtra(POKE_NUM, poke.pokeNum)
        startActivity(intentDetails)
    }


}
