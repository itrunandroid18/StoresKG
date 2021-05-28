package it.kimoterru.storesit.network

import it.kimoterru.storesit.network.models.HomeResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/getNewInComeHitDisc")
    suspend fun getHomePage(): HomeResponse
}