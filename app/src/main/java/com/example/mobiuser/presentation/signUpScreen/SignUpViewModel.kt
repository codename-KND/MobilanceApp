package com.example.mobiuser.presentation.signUpScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mobiuser.Goto
import com.example.mobiuser.domain.model.SignUpRequest
import com.example.mobiuser.domain.usecase.signUpUser.signUpUserUC
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val signUpUserUC: signUpUserUC
): ViewModel() {

    private val _signUpResult = MutableLiveData<Boolean>()
    val signUpResult: LiveData<Boolean> get() = _signUpResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun signUp(email: String, username: String, password: String,navController: NavController) {
        val signUpRequest = SignUpRequest(email, username, password)

        viewModelScope.launch {

            val result = signUpUserUC.signUp(signUpRequest)
            when (result) {
                is signUpUserUC.Result.Success -> {
                    _signUpResult.value = true
                    navigateToNextScreen(navController)
                }
                is signUpUserUC.Result.Error -> {
                    _signUpResult.value = false
                    _errorMessage.value = result.errorMessage
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