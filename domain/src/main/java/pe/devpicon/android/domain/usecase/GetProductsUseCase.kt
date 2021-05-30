package pe.devpicon.android.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.devpicon.android.domain.mapper.DataToDomainMapper
import pe.devpicon.android.domain.model.Product
import pe.devpicon.android.mytechbrandapp.data.datasource.ProductRemoteSouce
import kotlin.coroutines.CoroutineContext

class GetProductsUseCase(
    private val remoteDataSource: ProductRemoteSouce,
    private val dataToDomainMapper: DataToDomainMapper,
    private val coroutineContext: CoroutineContext = Dispatchers.Default
) {
    suspend fun execute(): List<Product> = withContext(coroutineContext) {
        remoteDataSource.getAllProducts()
            .map { dataToDomainMapper.mapProduct(it) }
    }
}

