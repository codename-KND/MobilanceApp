package com.example.mobiuser.presentation.signUpScreen

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mobiuser.Goto
import com.example.mobiuser.domain.model.SignUpRequest
import com.example.mobiuser.domain.usecase.signUpUser.signUpUserUC
import com.example.mobiuser.presentation.components.ViewModelFun
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUserUC: signUpUserUC
): ViewModel() {

    private val _signUpResult = MutableLiveData<Boolean>()
    val signUpResult: LiveData<Boolean> get() = _signUpResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    val use =ViewModelFun()

    fun signUp(email: String, username: String, password: String,navController: NavController,context:Context) {
        val signUpRequest = SignUpRequest(username, password,email)

        viewModelScope.launch {

            val result = signUpUserUC.signUp(signUpRequest)
            when (result) {
                is signUpUserUC.Result.Success -> {
                    _signUpResult.value = true
                    use.navigateNext(navController,Goto.Login.route)
                }
                is signUpUserUC.Result.Error -> {
                    _signUpResult.value = false
                    use.showError(context)
                }
            }
        }
    }

}