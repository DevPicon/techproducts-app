package pe.devpicon.android.mytechbrandapp.data.datasource

import pe.devpicon.android.mytechbrandapp.data.request.ProductRequest
import pe.devpicon.android.mytechbrandapp.data.response.ProductResponse

interface ProductRemoteSouce {

    suspend fun getAllProducts():List<ProductResponse>
    suspend fun getProduct(id:String):ProductResponse
    suspend fun createProduct(productRequest: ProductRequest)
    suspend fun removeProduct(id:String)

}