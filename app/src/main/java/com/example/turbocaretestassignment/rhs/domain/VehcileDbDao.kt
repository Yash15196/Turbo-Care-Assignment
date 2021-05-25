package com.example.turbocaretestassignment.rhs.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface VehcileDbDao {
    @Insert
    fun insertAll(todo: VehicleLocalDbEntity)

    @Query("SELECT * FROM vehicle_list")
    fun getAll(): List<VehicleLocalDbEntity>
}