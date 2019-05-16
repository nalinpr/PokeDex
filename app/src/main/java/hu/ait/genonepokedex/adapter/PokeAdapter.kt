package hu.ait.genonepokedex.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.ait.genonepokedex.R
import hu.ait.genonepokedex.data.Pokemon
import kotlinx.android.synthetic.main.poke_card.view.*

class PokeAdapter : RecyclerView.Adapter<PokeAdapter.ViewHolder>{

    var pokeItems = mutableListOf<Pokemon>()

    private val context: Context

    constructor(context: Context, listPokes: List<Pokemon>) : super() {
        this.context = context
        pokeItems.addAll(listPokes)
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

        viewHolder.tvName.text = poke.pokeName
        viewHolder.tvNumber.text = poke.pokeNum

        //viewHolder.itemView.setOnClickListener{
          //  (context as ScrollingActivity).runOnUiThread {
            //    context.onCityClick(viewHolder.adapterPosition)
            //}
        //}

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvName = itemView.tvName
        var tvNumber = itemView.tvNumber
    }
}