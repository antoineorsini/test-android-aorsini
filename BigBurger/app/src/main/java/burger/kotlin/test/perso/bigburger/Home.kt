package burger.kotlin.test.perso.bigburger

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import burger.kotlin.test.perso.bigburger.adapters.BurgerAdapter
import burger.kotlin.test.perso.bigburger.models.Burger
import burger.kotlin.test.perso.bigburger.services.BurgerService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    private var disposable: Disposable? = null

    private val burgerServe by lazy {
        BurgerService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        beginSearch()
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
                        { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}