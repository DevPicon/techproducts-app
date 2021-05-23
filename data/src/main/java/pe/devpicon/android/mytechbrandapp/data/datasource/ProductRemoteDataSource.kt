package pe.devpicon.android.mytechbrandapp.data.datasource

import pe.devpicon.android.mytechbrandapp.data.client.ProductService
import pe.devpicon.android.mytechbrandapp.data.request.ProductRequest
import pe.devpicon.android.mytechbrandapp.data.response.ProductResponse

class ProductRemoteDataSource(
    private val productService: ProductService
) : ProductRemoteSouce {

    override suspend fun getAllProducts(): List<ProductResponse> {
       return productService.getProducts()
    }

    override suspend fun getProduct(id: String): ProductResponse {
        return productService.getProduct(id)
    }

    override suspend fun createProduct(productRequest: ProductRequest) {
        productService.createProduct(productRequest)
    }

    override suspend fun removeProduct(id: String) {
        productService.removeProduct(id)
    }
}