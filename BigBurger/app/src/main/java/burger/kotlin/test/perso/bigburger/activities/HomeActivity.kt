package burger.kotlin.test.perso.bigburger.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import burger.kotlin.test.perso.bigburger.R
import burger.kotlin.test.perso.bigburger.adapters.BurgerAdapter
import burger.kotlin.test.perso.bigburger.models.Burger
import burger.kotlin.test.perso.bigburger.services.BurgerService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private var disposable: Disposable? = null
    private val burgerServe by lazy {
        BurgerService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        beginSearch()
        b_home_refresh.setOnClickListener {
            it.visibility = View.GONE
            beginSearch()
        }
        fab.setOnClickListener {
            val intent = Intent(this, ShoppingViewActivity::class.java)
               startActivity(intent)
        }
    }

    private fun onResult(burgers: ArrayList<Burger>){
        rv_burger_list.layoutManager = LinearLayoutManager(this)
        rv_burger_list.adapter = BurgerAdapter(burgers, this)
    }

    private fun beginSearch() {
        disposable = burgerServe.searchBurgerList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> onResult(result)},
                        { error -> onSearchError(error) }
                )
    }

    private fun onSearchError(error:Throwable){
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        b_home_refresh.visibility = View.VISIBLE
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}