package pe.devpicon.android.domain.mapper

import pe.devpicon.android.domain.model.Product
import pe.devpicon.android.mytechbrandapp.data.response.ProductResponse

object DataToDomainMapper {
    fun mapProduct(response: ProductResponse) =
        Product(
            response.id,
            response.name,
            response.brand,
            response.model
        )
}