package com.app.newzapp

sealed class UiState<out T> {
    class Success<T>(val data: T?) : UiState<T>()
    class Failure<T>(val message: String) : UiState<T>()
    object Loading : UiState<Nothing>()
}

//sealed class Car() {
//    class Mercedes : Car()
//
//    class Audi : Car()
//
//}
//
//class Mechanic<in T> {}
//
//val audiMechanic: Mechanic<Car.Audi> = Mechanic<Car>()