package com.example.turbocaretestassignment.rhs.feature

import android.os.Bundle
import android.view.View
import com.example.turbocaretestassignment.rhs.domain.VehicleListEnity
import com.example.turbocaretestassignment.rhs.app.base.BaseRecyclerViewHolder
import com.example.turbocaretestassignment.rhs.app.base.ViewHolderEventListener
import kotlinx.android.synthetic.main.item_vehicle_list.view.*

class VehicleListViewHolder(itemView: View , listner: ViewHolderEventListener<VehicleListEnity>) : BaseRecyclerViewHolder<VehicleListEnity>(itemView, listner) {
    var vehName = itemView.vehName
    override fun bind(position: Int, item: VehicleListEnity) {
        vehName.text = item.vehicleName
        itemView.setOnClickListener() {
            eventListener.onItemEvent(1, position, item, Bundle())
        }
    }

}