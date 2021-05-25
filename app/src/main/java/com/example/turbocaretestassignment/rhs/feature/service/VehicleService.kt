package com.example.turbocaretestassignment.rhs.feature.service


import com.example.turbocaretestassignment.rhs.domain.VehicleListEnity
import com.example.turbocaretestassignment.rhs.network.API_VEH_LIST
import com.example.turbocaretestassignment.rhs.network.API_VEH_MODEL
import com.example.turbocaretestassignment.rhs.network.QUERY_CLASS
import com.example.turbocaretestassignment.rhs.network.QUERY_MAKE
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleService {
    /**
     * function to retrieve the Veh List
     */
    @GET(API_VEH_LIST)
    fun getVehList(
        @Query(QUERY_CLASS) vehClass : String
    ): Single<ArrayList<String>>
    /**
     * function to retrieve the Veh Model
     */
    @GET(API_VEH_MODEL)
    fun getVehName(
        @Query(QUERY_CLASS) vehClass : String,
        @Query(QUERY_MAKE) vehMake : String
    ): Single<ArrayList<String>>
}