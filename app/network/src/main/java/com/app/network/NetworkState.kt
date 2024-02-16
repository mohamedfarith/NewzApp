package com.app.network

sealed class NetworkState<out T> {
    data class Success<T>(val data: Any?) : NetworkState<T>()
    data class Failure(val message: String) : NetworkState<Nothing>()
    object Loading : NetworkState<Nothing>()
}

/**
 *

open class Car

class Audi : Car()
class Mercedes : Car()

class CarMechanic<out T : Car>
class WorkShop {
fun addMechanic(car: CarMechanic<Car>) {}
}

val m = WorkShop().addMechanic(CarMechanic<Audi>())

 */



