package pe.devpicon.android.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import pe.devpicon.android.domain.mapper.DataToDomainMapper
import pe.devpicon.android.domain.model.Product
import pe.devpicon.android.mytechbrandapp.data.client.ktor.createKtoProductService

@ExperimentalCoroutinesApi
class GetProductsUseCaseTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var useCase: GetProductsUseCase

    @Before
    fun setUp() {

        Dispatchers.setMain(testDispatcher)

        useCase = GetProductsUseCase(
            remoteDataSource = createKtoProductService(),
            dataToDomainMapper = DataToDomainMapper,
            coroutineContext = testDispatcher
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun execute() = runBlocking {
        val result: List<Product> = useCase.execute()
        delay(5000)
        assert(result.isNotEmpty())
    }
}