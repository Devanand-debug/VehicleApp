package com.devanand.vehicleapp.di

import com.devanand.vehicleapp.api.VehicleAPI
import com.devanand.vehicleapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    }


    @Provides
    @Singleton
    fun providesVehicleAPI(retrofitBuilder: Retrofit.Builder): VehicleAPI {
        return retrofitBuilder.build().create(VehicleAPI::class.java)
    }

}