package com.example.mobiuser.presentation.requestAmbulance

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mobiuser.Goto
import com.example.mobiuser.domain.model.RequestData
import com.example.mobiuser.domain.usecase.requestAmbulanceUC.RequestAmbulanceUC
import kotlinx.coroutines.launch
import javax.inject.Inject

class RequestAmbulanceViewModel @Inject constructor(
    private val RequestAmbulance: RequestAmbulanceUC,
    private val navController: NavController
): ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    fun requestData(requestData: RequestData) {
        viewModelScope.launch {

            RequestAmbulance.requestAmbulance(requestData)
        }
    }

    init {
        RequestAmbulance.requestResult.observeForever { result ->
            when (result) {
                is RequestAmbulanceUC.Result.Success -> {
                    val serverResponse = result.serverResponse
                    navigateToNextScreen(navController)
                }
                is RequestAmbulanceUC.Result.Error -> {
                    val errorMessage = result.message
                    _errorMessage.value = errorMessage

                }
            }
        }
    }

    private fun navigateToNextScreen(navController: NavController) {
        navController.navigate(Goto.Login.route) {
            popUpTo(Goto.Login.route) {
                inclusive = true
            }
        }
    }







}