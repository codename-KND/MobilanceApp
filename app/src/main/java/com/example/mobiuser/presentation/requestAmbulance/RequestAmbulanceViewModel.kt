//package com.example.mobiuser.presentation.requestAmbulance
//
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import androidx.navigation.NavController
//import com.example.mobiuser.Goto
//import com.example.mobiuser.domain.model.RequestData
//import com.example.mobiuser.domain.tokens.TokenHandler
//import com.example.mobiuser.domain.usecase.requestAmbulanceUC.RequestAmbulanceUC
//import com.example.mobiuser.presentation.components.ViewModelFun
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class RequestAmbulanceViewModel @Inject constructor(
//    private val requestAmbulanceUC: RequestAmbulanceUC
//) : ViewModel() {
//
//    private val _errorMessage = MutableLiveData<String>()
//    val errorMessage: LiveData<String> = _errorMessage
//
//    fun requestData(
//        navController: NavController,
//        contact: String,
//        patient: String,
//        pickLatitude: Double,
//        pickLongitude: Double,
//        hospitalLatitude: Double,
//        hospitalLongitude: Double
//    ) {
//        viewModelScope.launch {
//            Log.i("userLog","launch ambulancecall")
//            requestAmbulanceUC.requestAmbulance(
//                contact = contact,
//                patient = patient,
//                pickLatitude = pickLatitude,
//                pickLongitude = pickLongitude,
//                hospitalLatitude = hospitalLatitude,
//                hospitalLongitude = hospitalLongitude
//            )
//            requestAmbulanceUC.requestResult.observeForever(){ result ->
//                when (result) {
//                    is RequestAmbulanceUC.Result.Success -> {
//                        navController.navigate(Goto.Home.route)
//                    }
//                    is RequestAmbulanceUC.Result.Error -> {
//                        _errorMessage.postValue(result.message)
//                    }
//                }
//            }
//        }
//    }
//}