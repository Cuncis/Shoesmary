package com.cuncis.shoesmary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cuncis.shoesmary.model.cost.CostResponse
import com.cuncis.shoesmary.repository.ShippingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ShippingViewModel : ViewModel() {

    private val repository = ShippingRepository()

    private var costData = MutableLiveData<CostResponse>()
    var message = MutableLiveData<String>()
    var loading = MutableLiveData<Boolean>()

    fun getCost(origin: String, destination: String, weight: String, courier: String): MutableLiveData<CostResponse> {
        loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                try {
                    val response = repository.getCost(origin, destination, weight, courier)

                    if (response.isSuccessful) {
                        costData.postValue(response.body())
                    } else {
                        message.postValue(response.message())
                    }
                    loading.postValue(false)
                } catch (e: Exception) {
                    message.postValue(e.localizedMessage)
                    loading.postValue(false)
                }
            }
        }

        return costData
    }

}