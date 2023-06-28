package com.example.mobiuser.presentation.loginScreen

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mobiuser.Goto
import com.example.mobiuser.domain.model.Credentials
import com.example.mobiuser.domain.tokens.tokenhandler
import com.example.mobiuser.domain.usecase.loginUser.loginUserUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUC: loginUserUC,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _authenticated = MutableLiveData<Boolean>()
   // val authenticated: LiveData<Boolean> get() = _authenticated

    private val _errorMessage = MutableLiveData<String>()
   // val errorMessage: LiveData<String> get() = _errorMessage

    fun onClick(username: String, password: String,navController: NavController) {
        val credentials = Credentials(username, password)
        val tokenhandler = tokenhandler(sharedPreferences)
        viewModelScope.launch {


            val result = loginUserUC.login(credentials)
            when (result) {
                is loginUserUC.Result.Success -> {
                    tokenhandler.storeToken(result.loginResponse.token)
                    _authenticated.value = true
                    navigateToHome(navController)
                }
                is loginUserUC.Result.Error -> {
                    _authenticated.value = false
                    _errorMessage.value = result.errorMessage
                }
            }
        }
    }

    private fun navigateToHome(navController: NavController) {
        navController.navigate(Goto.Login.route) {
            popUpTo(Goto.Login.route) {
                inclusive = true
            }
        }
    }

}