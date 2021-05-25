package com.cpsl.has.rhs.features.team.repo


import com.example.turbocaretestassignment.rhs.feature.service.VehicleService
import com.example.turbocaretestassignment.rhs.domain.VehicleListEnity
import io.reactivex.Single

class VehicleRepo(val mHttp: VehicleService) {
    /**
     * function to retrieve the Veh list
     */
    fun getVehList(vehicleType: String?): Single<ArrayList<VehicleListEnity>> {
        return mHttp.getVehList(vehicleType?:"2w").flatMap { vehList ->
            parseList(vehList)
        }


    } fun getVehName(vehicleType: String?,make:String): Single<ArrayList<VehicleListEnity>> {
        return mHttp.getVehName(vehicleType?:"2w",make).flatMap { vehList ->
            parseList(vehList)
        }


    }

    fun parseList(vehList: ArrayList<String>): Single<ArrayList<VehicleListEnity>> {
        var parseList: ArrayList<VehicleListEnity> = ArrayList()
        for (entity in vehList) {
            val vehicleListEnity = VehicleListEnity(0)
            vehicleListEnity.vehicleName = entity
            parseList.add(vehicleListEnity)
        }

        return Single.create<ArrayList<VehicleListEnity>> {
            it.onSuccess(parseList)
        }

    }
}