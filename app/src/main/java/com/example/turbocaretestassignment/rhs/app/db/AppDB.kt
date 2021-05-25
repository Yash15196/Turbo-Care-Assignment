package com.example.turbocaretestassignment.rhs.app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.turbocaretestassignment.rhs.domain.VehcileDbDao
import com.example.turbocaretestassignment.rhs.domain.VehicleLocalDbEntity

@Database(entities = arrayOf(VehicleLocalDbEntity::class), version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun vehDao(): VehcileDbDao
    companion object {
        @Volatile private var instance: AppDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDB::class.java, "vehicle_list.db")
            .build()
    }
}

