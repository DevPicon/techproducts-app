package pe.devpicon.android.mytechbrandapp.data.client.ktor

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import pe.devpicon.android.mytechbrandapp.data.client.ProductService
import pe.devpicon.android.mytechbrandapp.data.client.retrofit.buildRetrofit
import pe.devpicon.android.mytechbrandapp.data.client.retrofit.getLoggingInterceptor
import pe.devpicon.android.mytechbrandapp.data.client.retrofit.getOkHttpClient
import pe.devpicon.android.mytechbrandapp.data.datasource.ProductRemoteDataSource
import pe.devpicon.android.mytechbrandapp.data.datasource.ProductRemoteSouce
import pe.devpicon.android.mytechbrandapp.data.request.ProductRequest
import pe.devpicon.android.mytechbrandapp.data.response.ProductResponse

suspend fun main() {
    val ktorClient = getKtorClient()
    val retrofitClient = buildRetrofit(getOkHttpClient(getLoggingInterceptor()))

    val ktorProductService: ProductService = KtorProductService(ktorClient)
    val retrofitProductService: ProductService = retrofitClient.create(ProductService::class.java)

    val productRemoteDataSource: ProductRemoteSouce = ProductRemoteDataSource(
        productService = ktorProductService
    )

    val retrofitProductDataSource: ProductRemoteSouce = ProductRemoteDataSource(
        productService = retrofitProductService
    )

    productRemoteDataSource.createProduct(
        ProductRequest(
            id = "0002",
            name = "an artifact",
            brand = "Koscher",
            model = "AIX02"
        )
    )

    retrofitProductDataSource.createProduct(
        ProductRequest(
            id = "0003",
            name = "other artifact",
            brand = "Thomas",
            model = "TH01"
        )
    )

    println(productRemoteDataSource.getAllProducts())
    println(retrofitProductDataSource.getAllProducts())
}

fun getKtorClient() = HttpClient(CIO) {
    expectSuccess = false
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
    install(JsonFeature) {
        serializer = GsonSerializer() {
            setPrettyPrinting()
            disableHtmlEscaping()
        }
    }
}

