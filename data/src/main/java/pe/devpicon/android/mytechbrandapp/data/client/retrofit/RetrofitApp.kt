package pe.devpicon.android.mytechbrandapp.data.client.retrofit

import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor
import pe.devpicon.android.mytechbrandapp.data.client.ProductService
import pe.devpicon.android.mytechbrandapp.data.common.ProductURL


suspend fun main() {

    val logging = getLoggingInterceptor()

    val client: OkHttpClient = getOkHttpClient(logging)

    val retrofit = buildRetrofit(client)

    val service: ProductService = retrofit.create(ProductService::class.java)

    //println(service.getProducts())

    println(service.getProduct("0003"))

}

fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

fun buildRetrofit(client: OkHttpClient) = Retrofit.Builder()
    .baseUrl(ProductURL.BASE)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

fun getOkHttpClient(logging: HttpLoggingInterceptor) =
    OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

