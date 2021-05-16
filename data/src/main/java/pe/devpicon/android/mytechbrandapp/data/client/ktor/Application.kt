package pe.devpicon.android.mytechbrandapp.data.client.ktor

import com.google.gson.annotations.SerializedName
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

suspend fun main (){
    val client = HttpClient(CIO){
        expectSuccess = false
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(JsonFeature){
            serializer = GsonSerializer(){
                setPrettyPrinting()
                disableHtmlEscaping()
            }
        }
    }

    client.post<Unit>("http://0.0.0.0:8080/api/product") {
        contentType(ContentType.Application.Json)
        body = ProductRequest("0003", "Microwave oven", "Oster", "RX-3816")
    }

    val response:List<ProductResponse> = client.get("http://0.0.0.0:8080/api/product")
    println(response)
    client.close()
}

data class ProductRequest(
    val id:String,
    val name:String,
    val brand:String,
    val model:String
)

data class ProductResponse(
    val id:String,
    val name:String,
    val brand:String,
    val model:String
)