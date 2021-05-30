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

suspend fun main() {
    val productRemoteDataSource: ProductRemoteSouce = createKtoProductService()

    productRemoteDataSource.createProduct(
        ProductRequest(
            id = "0002",
            name = "an artifact",
            brand = "Koscher",
            model = "AIX02"
        )
    )

    val retrofitProductDataSource = createRetrofitProductService()

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

fun createKtoProductService(): ProductRemoteSouce {
    val ktorClient = getKtorClient()
    val ktorProductService: ProductService = KtorProductService(ktorClient)
    return ProductRemoteDataSource(
        productService = ktorProductService
    )
}

fun createRetrofitProductService(): ProductRemoteSouce {
    val retrofitClient = buildRetrofit(getOkHttpClient(getLoggingInterceptor()))
    val retrofitProductService: ProductService = retrofitClient.create(ProductService::class.java)

    return ProductRemoteDataSource(
        productService = retrofitProductService
    )
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

