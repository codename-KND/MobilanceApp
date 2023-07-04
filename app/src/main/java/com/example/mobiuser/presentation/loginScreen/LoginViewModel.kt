package com.example.mobiuser.presentation.loginScreen

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mobiuser.Goto
import com.example.mobiuser.domain.model.Credentials
import com.example.mobiuser.domain.tokens.TokenHandler
import com.example.mobiuser.domain.usecase.loginUser.loginUserUC
import com.example.mobiuser.presentation.components.ViewModelFun
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUC: loginUserUC,
) : ViewModel() {
    private val _authenticated = MutableLiveData<Boolean>()
   // val authenticated: LiveData<Boolean> get() = _authenticated

    private val _errorMessage = MutableLiveData<String>()
   // val errorMessage: LiveData<String> get() = _errorMessage
        var use = ViewModelFun()
    fun login(username: String, password: String,navController: NavController,context: Context) {
        val credentials = Credentials(username, password)

        viewModelScope.launch {

            Log.i("LoginViewModel", "login called")
            val result = loginUserUC.login(credentials)
            when (result) {
                is loginUserUC.Result.Success -> {
                    _authenticated.value = true
                    use.navigateNext(navController,Goto.DriverHome.route)
                }
                is loginUserUC.Result.Error -> {
                    _authenticated.value = false
                    use.showError(context)
                }
            }
        }
    }

}