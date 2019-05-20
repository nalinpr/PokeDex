package hu.ait.genonepokedex.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import hu.ait.genonepokedex.R
import hu.ait.genonepokedex.ScrollingActivity
import hu.ait.genonepokedex.data.Pokemon
import kotlinx.android.synthetic.main.poke_card.view.*

class PokeAdapter(private val context: Context) : RecyclerView.Adapter<PokeAdapter.ViewHolder>() {

    interface OnPokeListener{
        fun onPokeClick(position: Int)
    }

    var pokeItems = mutableListOf<Pokemon>()

    init {
        for (i in 1..150){
            val poke = Pokemon (
                i.toString(),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + i + ".png"
            )
            pokeItems.add(poke)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val pokeCardView = LayoutInflater.from(context).inflate(
            R.layout.poke_card, viewGroup, false
        )
        return ViewHolder(pokeCardView)
    }

    override fun getItemCount(): Int {
        return pokeItems.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val poke  = pokeItems.get(viewHolder.adapterPosition)
//        viewHolder.tvNumber.text = poke.pokeNum

        Glide.with(context)
            .load(poke.imgUrl)
            .into(viewHolder.imgPoke)

        viewHolder.itemView.setOnClickListener{
            (context as ScrollingActivity).runOnUiThread {
                context.onPokeClick(viewHolder.adapterPosition)
            }
        }    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var imgPoke = itemView.ivAvatar
//        var tvNumber = itemView.tvNumber

    }
}