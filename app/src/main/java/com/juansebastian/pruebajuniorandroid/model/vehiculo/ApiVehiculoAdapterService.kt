package com.juansebastian.pruebajuniorandroid.model.vehiculo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiVehiculoAdapterService {

    companion object {
        private var API_SERVICE: ApiVehiculoService? = null
        @JvmStatic
        fun getApiService(): ApiVehiculoService? {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            val baseUrl = "https://run.mocky.io/v3/"
            if (API_SERVICE == null) {
                val retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build()
                API_SERVICE = retrofit.create<ApiVehiculoService>(ApiVehiculoService::class.java)
            }
            return API_SERVICE
        }

    }
}