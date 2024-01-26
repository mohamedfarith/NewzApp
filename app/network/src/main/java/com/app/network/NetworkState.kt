package com.app.network

sealed class NetworkState {
    data class Success(val data: Any?) : NetworkState()
    data class Failure(val message: String) : NetworkState()
    object Loading: NetworkState()
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



