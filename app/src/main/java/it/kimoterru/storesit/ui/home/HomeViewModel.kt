package it.kimoterru.storesit.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.kimoterru.storesit.network.NetworkHelper
import it.kimoterru.storesit.network.models.HomeReponse
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val homeResponseLiveData = MutableLiveData<HomeReponse>()

    fun getHomeScreen() {
        viewModelScope.launch {
            val result = NetworkHelper.getService().getHomePage()
            homeResponseLiveData.postValue(result)
        }
    }
}