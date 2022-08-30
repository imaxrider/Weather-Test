package com.imax.testapplication.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imax.testapplication.R
import com.imax.testapplication.business.AppImpl
import com.imax.testapplication.constant.UnitsType
import com.imax.testapplication.model.ForeCastModel
import com.imax.testapplication.util.DateTimeUtils

class ForeCastAdapter(private val items: ArrayList<ForeCastModel?>?, val context: Context):
RecyclerView.Adapter<ForeCastAdapter.ItemRowHolder>(){
    class ItemRowHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txvDay: TextView? = itemView.findViewById(R.id.txvDay)
        var txvHumidity: TextView? = itemView.findViewById(R.id.txvHumidity)
        var txvUnits: TextView? = itemView.findViewById(R.id.txvUnits)
        var txvTem: TextView? = itemView.findViewById(R.id.txvTem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRowHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.list_forecast_item, parent, false)
        return ItemRowHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemRowHolder, position: Int) {
        val model = items!![position]
        holder.txvHumidity!!.text = "${model?.main?.humidity}%"
        holder.txvTem!!.text = "${model?.main?.temp}"
        holder.txvUnits!!.text = AppImpl.checkUnits(model?.units ?: UnitsType.metric.name)
        holder.txvDay!!.text = DateTimeUtils.changeFormat(model!!.dtTxt!!)

    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }
}