package burger.kotlin.test.perso.bigburger.utils

import android.content.Context
import android.util.Log
import burger.kotlin.test.perso.bigburger.models.Burger

object ShopSingleton {

    val myList = arrayListOf<Burger>()
        get() = field

    var totalPrice : Double = 0.0

    fun addBurger(newOrder: Burger) {
        val iterate = myList.listIterator()
        while (iterate.hasNext()) {
            val burger = iterate.next()
            if (compareBurger(burger, newOrder)) {
                burger.qty += 1
                iterate.set(burger)
                totalPrice += burger.price
                return
            }
        }
        newOrder.qty = 1
        totalPrice += newOrder.price
        myList.add(newOrder)
    }

    fun delBurger(newOrder: Burger) {
        val iterate = myList.listIterator()
        while (iterate.hasNext()) {
            val burger = iterate.next()
            if (compareBurger(burger, newOrder)) {
                if (burger.qty == 1)
                    iterate.remove()
                else {
                    burger.qty -= 1
                    iterate.set(burger)
                }
                totalPrice -= newOrder.price
                return
            }
        }
    }


    private fun compareBurger(first: Burger, second: Burger): Boolean {
        if (
                first.ref.equals(second.ref)
                && first.title.equals(second.title)
                && first.description.equals(second.description)
                && first.thumbnail.equals(second.thumbnail)
                && first.price == second.price
        )
            return true
        return false
    }

//    val orders = mutableMapOf<Burger, Int>()
//
//    fun addBurger(newOrder: Burger) {
//        if (orders.size > 0) {
//            if (orders.contains(newOrder)) {
//                orders[newOrder] = orders.get(newOrder)!!.plus(1)
//            } else {
//                orders[newOrder] = 1
//            }
//        } else
//            orders[newOrder] = 1
//    }
//
//    fun delBurger(del: Burger){
//        if (orders.get(del) == 1)
//            orders.remove(del)
//        else
//            orders[del] = orders.get(del)!!.minus(1)
//    }
}