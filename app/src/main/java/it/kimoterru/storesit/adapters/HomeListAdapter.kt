package it.kimoterru.storesit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import it.kimoterru.storesit.R
import it.kimoterru.storesit.network.models.HomeListItem

class HomeListAdapter(private val data : List<HomeListItem>, private val listener : ProductClickListener) : RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.product_name)
        val price: TextView = itemView.findViewById(R.id.product_price)
        val image: ImageView = itemView.findViewById(R.id.product_image)
        val layout: ConstraintLayout = itemView.findViewById(R.id.product_layout)
        val favorite: ImageView = itemView.findViewById(R.id.product_favorite)
        val addToCart: Button = itemView.findViewById(R.id.product_cart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product_home, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.name.text = item.nameShort
        holder.price.text = item.price.toString()
        Glide.with(holder.image).load(item.imageUrl).into(holder.image)
        holder.layout.setOnClickListener {
            listener.onItemClick(item.id.toLong())
        }
        holder.favorite.setOnClickListener {
            listener.onFavoriteClick(item.id.toLong())
        }
        holder.addToCart.setOnClickListener {
            listener.onAddToCartClick(item.id.toLong())
        }
    }

    override fun getItemCount() = data.size
}