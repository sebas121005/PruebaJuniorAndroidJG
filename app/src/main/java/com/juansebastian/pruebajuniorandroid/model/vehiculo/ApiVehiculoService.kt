package com.juansebastian.pruebajuniorandroid.model.vehiculo

import retrofit2.Call
import retrofit2.http.GET


interface ApiVehiculoService {
    @GET("d8c1c023-5ab7-413c-bbec-a3963ff3eed7")
    fun getVehiculos(): Call<Vehiculo>
}