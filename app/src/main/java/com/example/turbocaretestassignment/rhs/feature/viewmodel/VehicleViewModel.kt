package com.example.turbocaretestassignment.rhs.feature.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.cpsl.has.rhs.features.team.repo.VehicleRepo
import com.example.turbocaretestassignment.rhs.BaseViewModel
import com.example.turbocaretestassignment.rhs.app.base.LiveDataResultStatus
import com.example.turbocaretestassignment.rhs.app.base.LiveDataResultWrapper
import com.example.turbocaretestassignment.rhs.domain.VehicleListEnity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class VehicleViewModel(application: Application, val vehicleRepo: VehicleRepo) : BaseViewModel(application) {
    val vehListLiveData: MutableLiveData<LiveDataResultWrapper<ArrayList<VehicleListEnity>>> = MutableLiveData()
    val vehModelLiveData: MutableLiveData<LiveDataResultWrapper<ArrayList<VehicleListEnity>>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getVehList(vehicleType: String?): LiveData<LiveDataResultWrapper<ArrayList<VehicleListEnity>>> {
        val resultWrapper: LiveDataResultWrapper<ArrayList<VehicleListEnity>> = LiveDataResultWrapper()
        vehListLiveData.value = resultWrapper
        vehicleRepo.getVehList(vehicleType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { teamEntity ->
                            resultWrapper.resultData = teamEntity
                            resultWrapper.resultStatus = LiveDataResultStatus.COMPLETE
                            vehListLiveData.value = resultWrapper
                        },
                        { error ->
                            resultWrapper.error = error
                            resultWrapper.resultStatus = LiveDataResultStatus.ERROR
                            vehListLiveData.value = resultWrapper
                            //   teamLiveData.value = DummyAppUtils.testDataForEnquireTeam()
                        })
        return vehListLiveData
    }
 @SuppressLint("CheckResult")
    fun getVehModel(vehicleType: String?,make:String): LiveData<LiveDataResultWrapper<ArrayList<VehicleListEnity>>> {
        val resultWrapper: LiveDataResultWrapper<ArrayList<VehicleListEnity>> = LiveDataResultWrapper()
     vehModelLiveData.value = resultWrapper
        vehicleRepo.getVehName(vehicleType,make)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { teamEntity ->
                            resultWrapper.resultData = teamEntity
                            resultWrapper.resultStatus = LiveDataResultStatus.COMPLETE
                            vehModelLiveData.value = resultWrapper
                        },
                        { error ->
                            resultWrapper.error = error
                            resultWrapper.resultStatus = LiveDataResultStatus.ERROR
                            vehModelLiveData.value = resultWrapper
                            //   teamLiveData.value = DummyAppUtils.testDataForEnquireTeam()
                        })
        return vehModelLiveData
    }

    override fun claredViewModel() {

    }


}