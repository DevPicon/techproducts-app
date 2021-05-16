package pe.devpicon.android.mytechbrandapp.data.client.ktor

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

suspend fun main (){
    val client = HttpClient(CIO){
        expectSuccess = false
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }
    }
    val response:HttpResponse = client.get("http://0.0.0.0:8080/api/product")
    println(response.status)
    client.close()
}