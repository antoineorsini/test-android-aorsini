package burger.kotlin.test.perso.bigburger.services

import burger.kotlin.test.perso.bigburger.BuildConfig
import burger.kotlin.test.perso.bigburger.models.Burger
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BurgerService {

    @GET("/catalog")
    fun searchBurgerList(): Observable<ArrayList<Burger>>

    companion object {
        fun create(): BurgerService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.SERVER_URL)
                    .build()

            return retrofit.create(BurgerService::class.java)
        }
    }
}