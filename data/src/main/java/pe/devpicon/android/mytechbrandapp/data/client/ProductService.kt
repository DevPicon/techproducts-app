package pe.devpicon.android.mytechbrandapp.data.client

import pe.devpicon.android.mytechbrandapp.data.common.ProductURL
import pe.devpicon.android.mytechbrandapp.data.request.ProductRequest
import pe.devpicon.android.mytechbrandapp.data.response.ProductResponse
import retrofit2.http.*

interface ProductService {

    @GET(ProductURL.PRODUCT)
    suspend fun getProducts(): List<ProductResponse>

    @GET(ProductURL.PRODUCT_BY_ID)
    suspend fun getProduct(
        @Path("id") id: String
    ): ProductResponse

    @POST(ProductURL.PRODUCT)
    suspend fun createProduct(
        @Body productRequest: ProductRequest
    )

    @DELETE(ProductURL.PRODUCT_BY_ID)
    suspend fun removeProduct(
        @Path("id") id: String
    )

}