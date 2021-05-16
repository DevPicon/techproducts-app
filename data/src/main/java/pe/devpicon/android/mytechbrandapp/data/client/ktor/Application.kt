package pe.devpicon.android.mytechbrandapp.data.client.ktor

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import pe.devpicon.android.mytechbrandapp.data.request.ProductRequest
import pe.devpicon.android.mytechbrandapp.data.response.ProductResponse

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

