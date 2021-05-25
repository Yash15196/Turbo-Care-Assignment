package com.example.turbocaretestassignment.rhs.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicle_list")
    data class VehicleLocalDbEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int,

        @ColumnInfo(name = "vehicleName") var vehicleName: String,
        @ColumnInfo(name = "vehicleNumber") var vehicleNumber: String,
        @ColumnInfo(name = "vehicleModel") var vehicleModel: String,
        @ColumnInfo(name = "vehicleFuelType") var vehicleFuelType: String,
        @ColumnInfo(name = "vehicleTransType") var vehicleTransType: String
    )

