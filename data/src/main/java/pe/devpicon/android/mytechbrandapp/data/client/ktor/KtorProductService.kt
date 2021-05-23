package pe.devpicon.android.mytechbrandapp.data.client.ktor

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import pe.devpicon.android.mytechbrandapp.data.client.ProductService
import pe.devpicon.android.mytechbrandapp.data.common.ProductURL
import pe.devpicon.android.mytechbrandapp.data.request.ProductRequest
import pe.devpicon.android.mytechbrandapp.data.response.ProductResponse

class KtorProductService(
    private val ktorClient: HttpClient
) : ProductService {

    override suspend fun getProducts(): List<ProductResponse> {
        return ktorClient.get<List<ProductResponse>>("${ProductURL.BASE}${ProductURL.PRODUCT}")
    }

    override suspend fun getProduct(id: String): ProductResponse {
        return ktorClient.get<ProductResponse>("${ProductURL.BASE}${ProductURL.PRODUCT}/$id")
    }

    override suspend fun createProduct(productRequest: ProductRequest) {
        ktorClient.post<Unit>("${ProductURL.BASE}${ProductURL.PRODUCT}") {
            contentType(ContentType.Application.Json)
            body = productRequest
        }
    }

    override suspend fun removeProduct(id: String) {
        ktorClient.delete<Unit>("${ProductURL.BASE}${ProductURL.PRODUCT}/$id")
    }
}