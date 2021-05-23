package pe.devpicon.android.mytechbrandapp.data.common

object ProductURL {
    const val HOST = "0.0.0.0"
    const val PORT = 8080
    const val BASE = "http://$HOST:$PORT/"
    const val PRODUCT = "api/product"
    const val PRODUCT_BY_ID = PRODUCT + "/{id}"
}