package burger.kotlin.test.perso.bigburger.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import burger.kotlin.test.perso.bigburger.R
import burger.kotlin.test.perso.bigburger.adapters.BurgerAdapter
import burger.kotlin.test.perso.bigburger.utils.ShopSingleton
import kotlinx.android.synthetic.main.activity_shopping_view.*
import shopping.kotlin.test.perso.bigshopping.adapters.ShoppingAdapter

class ShoppingViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_view)

        updatePrice()
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        rv_shopping_list.layoutManager = LinearLayoutManager(this)
        rv_shopping_list.adapter = ShoppingAdapter(ShopSingleton.myList, this)
    }

    fun updatePrice() {
        val finalPrice = "${ShopSingleton.totalPrice / 100} €"
        tv_shopping_total_price.text = finalPrice
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}