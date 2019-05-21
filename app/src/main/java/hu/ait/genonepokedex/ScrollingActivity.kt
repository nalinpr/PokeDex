package hu.ait.genonepokedex

//YOOOOOO GET THOSE POKESSSSS

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import hu.ait.genonepokedex.adapter.PokeAdapter
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity(), PokeAdapter.OnPokeListener {

    companion object {
        val POKE_NAME = "POKE_NAME"
        val IMG_URL = "IMG_URL"
        val POKE_NUM = "POKE_NUM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            val intentDetails = Intent()
            intentDetails.setClass(this@ScrollingActivity,
                PopDetailsActivity::class.java)

            intentDetails.putExtra(POKE_NAME, "bulbasaur")
            intentDetails.putExtra(IMG_URL, "/")
            intentDetails.putExtra(POKE_NUM, "001")
            startActivity(intentDetails)
        }
    }

    lateinit var pokeAdapter : PokeAdapter


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

        intentDetails.putExtra(POKE_NAME, poke.pokeName)
        intentDetails.putExtra(IMG_URL, poke.imgUrl)
        startActivity(intentDetails)
    }


}
