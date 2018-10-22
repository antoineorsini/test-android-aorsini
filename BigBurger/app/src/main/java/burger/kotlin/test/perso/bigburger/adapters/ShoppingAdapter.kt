package shopping.kotlin.test.perso.bigshopping.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import burger.kotlin.test.perso.bigburger.R
import burger.kotlin.test.perso.bigburger.activities.ShoppingViewActivity
import burger.kotlin.test.perso.bigburger.models.Burger
import burger.kotlin.test.perso.bigburger.utils.ShopSingleton

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_view_shopping.view.*
import kotlinx.android.synthetic.main.card_view_shopping.view.*

class ShoppingAdapter(val items: ArrayList<Burger>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view_shopping, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.tvShoppingTitle?.text = items.get(position).title
        val price = "${(items.get(position).price / 100L).toString()} €"
        holder?.tvShoppingPrice?.text = price
        Picasso.with(context)
                .load(items.get(position).thumbnail)
                .into(holder?.ivShoppingThumbnail)
        holder?.ivShoppingAdd?.setOnClickListener {
            ShopSingleton.addBurger(items.get(position))
            (context as ShoppingViewActivity).updatePrice()
            holder?.tvShoppingQty?.text = items.get(position).qty.toString()
        }
        holder?.ivShoppingDel?.setOnClickListener {
            if (items.get(position).qty == 1) {
                ShopSingleton.delBurger(items.get(position))
                this.notifyItemRemoved(position)
                this.notifyDataSetChanged()
            } else {
                ShopSingleton.delBurger(items.get(position))
                holder?.tvShoppingQty?.text = items.get(position).qty.toString()
            }
            (context as ShoppingViewActivity).updatePrice()
        }
        holder?.tvShoppingQty?.text = items.get(position).qty.toString()
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvShoppingTitle = view.tv_shopping_title
    val tvShoppingPrice = view.tv_shopping_price
    val ivShoppingThumbnail = view.iv_shopping_thumbnail
    val ivShoppingAdd = view.iv_shop_add_to_cart
    val ivShoppingDel = view.iv_shop_del_to_cart
    val tvShoppingQty = view.tv_shopping_qty
}
