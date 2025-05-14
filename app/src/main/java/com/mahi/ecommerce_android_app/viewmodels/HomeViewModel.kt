package com.mahi.ecommerce_android_app.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahi.ecommerce_android_app.api.MobileApi
import com.mahi.ecommerce_android_app.model.HomePageItems
import com.mahi.ecommerce_android_app.model.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mobileApi: MobileApi
): ViewModel() {

    private val _homePageState = MutableStateFlow<NetworkResult<HomePageItems>>(NetworkResult.Loading())
    val homePageState: StateFlow<NetworkResult<HomePageItems>> = _homePageState


    fun fetchHomePageItems() {
        viewModelScope.launch {
            _homePageState.value = NetworkResult.Loading()
            try {
                val response = mobileApi.getHomePageItems()
                _homePageState.value = NetworkResult.Success(response.body()!!)
            } catch (e: HttpException) {
                _homePageState.value = NetworkResult.Error("Server error: ${e.message()}")
            } catch (e: Exception) {
                _homePageState.value = NetworkResult.Error("Unexpected error: ${e.localizedMessage}")
            }
        }
    }

}


