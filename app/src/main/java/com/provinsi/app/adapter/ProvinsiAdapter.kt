package com.provinsi.app.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.provinsi.app.databinding.ItemProvinsiBinding
import com.provinsi.app.helper.viewBinding
import com.provinsi.app.model.response.ProvinsiResponseItem

class ProvinsiAdapter(
    private val context: Context,
    var data: List<ProvinsiResponseItem>,
    val cellClickListener: CellClickListener
) :
    RecyclerView.Adapter<ProvinsiAdapter.MyViewHolder>(){

    inner class MyViewHolder(binding: ItemProvinsiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var nameProvinsi = binding.nameProvinsi
        var parentLy = binding.parentLayout

        fun bind(data: ProvinsiResponseItem, pos: Int) {
            nameProvinsi.text = data.propinsi_name

            parentLy.setOnClickListener {
                cellClickListener.selectProvinsi(data)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProvinsiAdapter.MyViewHolder {
        return MyViewHolder(parent.viewBinding(ItemProvinsiBinding::inflate))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = data!![position]
        holder.bind(data, position)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    interface CellClickListener {
        fun selectProvinsi(data: ProvinsiResponseItem)
    }
}
