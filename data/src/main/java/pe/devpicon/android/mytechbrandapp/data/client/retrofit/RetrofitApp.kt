package pe.devpicon.android.mytechbrandapp.data.client.retrofit

import pe.devpicon.android.mytechbrandapp.data.response.ProductResponse
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit
import retrofit2.http.GET
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor





suspend fun main(){

    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)

    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://0.0.0.0:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val service: ProductService = retrofit.create(ProductService::class.java)

    println(service.getProducts())

}

interface ProductService{

    @GET("api/product")
    suspend fun getProducts():List<ProductResponse>

}
