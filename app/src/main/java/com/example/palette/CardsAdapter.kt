package com.example.palette

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView

class CardsAdapter(private val items: ArrayList<Tarjeta>) :
    RecyclerView.Adapter<CardsAdapter.TarjViewHolder>() {

    private var onClickListener: View.OnClickListener? = null

    fun setOnClick(onClickListener: View.OnClickListener) {
        this.onClickListener = onClickListener
    }

    class TarjViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imageViewCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarjViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cards, parent, false)
        return TarjViewHolder(view)
    }

    override fun onBindViewHolder(holder: TarjViewHolder, position: Int) {
        val item = items[position]
        val context: Context = holder.itemView.context

        holder.imagen.setImageResource(item.imagen)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ImagePalette::class.java)
            intent.putExtra("image_resource", item.imagen)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as Activity,
                holder.imagen,
                "sharedImage"
            )

            context.startActivity(intent, options.toBundle())
        }
    }

    override fun getItemCount(): Int = items.size
}
