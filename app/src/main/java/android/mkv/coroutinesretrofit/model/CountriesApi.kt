package android.mkv.coroutinesretrofit.model

import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi {
    @GET("/DevTides/countries/master/countriesV2.json")
    suspend fun getCountries(): Response<ArrayList<Country>>

}