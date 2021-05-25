package it.kimoterru.storesit.network

import it.kimoterru.storesit.network.models.HomeReponse
import retrofit2.http.GET

interface ApiService {
    @GET("/getNewInComeHitDisc")
    suspend fun getHomePage(): HomeReponse
}