package com.android.bottomsheetSample.subStackFragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.bottomsheetSample.databinding.ItemAdapterBinding


data class SimpleDataClass(val title : String = "", val desp : String = "")

class SimpleAdapter(val arrayList: ArrayList<SimpleDataClass>) : RecyclerView.Adapter<SimpleItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleItemViewHolder {
        return SimpleItemViewHolder(
                ItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SimpleItemViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}

class SimpleItemViewHolder( val  viewbinder: ItemAdapterBinding,) : RecyclerView.ViewHolder(viewbinder.root) {

    fun bind(simpleDataClass: SimpleDataClass){
        viewbinder.tvItemTitle.text = simpleDataClass.title
        viewbinder.tvItemDesp.text = simpleDataClass.desp
    }

}