package burger.kotlin.test.perso.bigburger.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import burger.kotlin.test.perso.bigburger.R
import burger.kotlin.test.perso.bigburger.models.Burger
import burger.kotlin.test.perso.bigburger.utils.ShopSingleton
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_view_burger.view.*

class BurgerAdapter(val items: ArrayList<Burger>, val context: Context) : RecyclerView.Adapter<ViewHolderHome>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderHome {
        return ViewHolderHome(LayoutInflater.from(context).inflate(R.layout.card_view_burger, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderHome?, position: Int) {
        holder?.tvBurgerTitle?.text = items.get(position).title
        holder?.tvBurgerDesc?.text = items.get(position).description
        val price = "${(items.get(position).price / 100L).toString()} €"
        holder?.tvBurgerPrice?.text = price
        Picasso.with(context)
                .load(items.get(position).thumbnail)
                .into(holder?.ivBurgerThumbnail)
        holder?.ivBurgerCart?.setOnClickListener { ShopSingleton.addBurger(items.get(position))
            Toast.makeText(context, "Ajout d'un ${items.get(position).title}", Toast.LENGTH_SHORT).show()
        }
    }
}

class ViewHolderHome(view: View) : RecyclerView.ViewHolder(view) {
    val tvBurgerTitle = view.tv_burger_title
    val tvBurgerDesc = view.tv_burger_description
    val tvBurgerPrice = view.tv_burger_price
    val ivBurgerThumbnail = view.iv_burger_thumbnail
    val ivBurgerCart = view.iv_add_to_cart
}
