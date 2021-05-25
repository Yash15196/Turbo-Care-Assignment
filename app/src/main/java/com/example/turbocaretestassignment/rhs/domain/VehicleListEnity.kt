package com.example.turbocaretestassignment.rhs.domain

import com.cpsl.has.rhs.domain.BaseEntity

data class VehicleListEnity(var vehcileId: Int) : BaseEntity() {
    var vehicleName: String? = null
}