package com.example.mobiuser.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mobilanceuser.data.remote.dto.DjangoApi
import com.example.mobiuser.data.repository.DjangoRepositoryImpl
import com.example.mobiuser.domain.repository.DjangoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDjangoApi(): DjangoApi{
        return Retrofit.Builder()
             .baseUrl("http://192.168.100.76:8000/api/")
            //.baseUrl("http://192.168.43.74:8000/api/")
            //.baseUrl("https://eomjc3xwz030p8y.m.pipedream.net")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(DjangoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDjangoRepository(api: DjangoApi):DjangoRepository{
        return DjangoRepositoryImpl(api)
    }

    @Provides
    fun provideNavController(activity: ComponentActivity): NavController {
        return NavHostController(activity)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("tokens", Context.MODE_PRIVATE)
    }



}