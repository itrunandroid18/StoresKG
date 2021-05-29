package it.kimoterru.storesit.adapters

interface ProductClickListener {
    fun onItemClick(id : Long)
    fun onFavoriteClick(id : Long)
    fun onAddToCartClick(id : Long)
}