package com.example.turbocaretestassignment.rhs.feature

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.turbocaretestassignment.R
import com.example.turbocaretestassignment.rhs.domain.VehicleLocalDbEntity
import com.example.turbocaretestassignment.rhs.util.*
import kotlinx.android.synthetic.main.item_veh_list.view.*


class VehListAdapterAdapter(
    val context: Context? ,
    val isUnload: Boolean ,
    val vehList: ArrayList<VehicleLocalDbEntity>

) : RecyclerView.Adapter<TaskUldViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): TaskUldViewHolder {
        return TaskUldViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_veh_list ,
                parent ,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return vehList.size
    }

    override fun onBindViewHolder(holder: TaskUldViewHolder , position: Int) {
        holder.tvVehNumber.text = vehList.get(position).vehicleNumber
        holder.tvVehModel.text = vehList.get(position).vehicleModel
        holder.itemView.setOnClickListener() {
            var bundle = Bundle()
            var item = vehList.get(position)
            bundle.putString(RECORD_VEH_FUEL , item.vehicleFuelType)
            bundle.putString(RECORD_VEH_TRANS , item.vehicleTransType)
            bundle.putString(RECORD_VEH_NAME , item.vehicleName)
            bundle.putString(RECORD_VEH_MODEL , item.vehicleModel)
            bundle.putString(RECORD_VEH_NUMBER , item.vehicleNumber)
            var fragment = VehicleSumaryFragment.newInstance(bundle,false)
            navigateToFragment(R.id.container , true , context as AppCompatActivity , fragment)
        }
    }
}

class TaskUldViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var tvVehNumber = view.tvVehNumber
    var tvVehModel = view.tvVehModel
}