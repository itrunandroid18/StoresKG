package it.kimoterru.storesit.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.kimoterru.storesit.network.NetworkHelper
import it.kimoterru.storesit.network.Resource
import it.kimoterru.storesit.network.models.HomeResponse
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    val homeResponseLiveData = MutableLiveData<Resource<HomeResponse>>()

    fun getHomeScreen() {
        homeResponseLiveData.postValue(Resource.loading())
        viewModelScope.launch {
            try {
                val result = NetworkHelper.getService().getHomePage()
                homeResponseLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                homeResponseLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }
}