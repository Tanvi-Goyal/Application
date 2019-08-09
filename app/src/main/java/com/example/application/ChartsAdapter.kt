package com.example.application

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList

class ChartsAdapter internal constructor(private val context: Context, private val chartList: MutableList<String>) :
    RecyclerView.Adapter<ChartsAdapter.ViewHolder>() {

    lateinit var intent: Intent
    private val copyChartList: List<String>

    inner class ViewHolder internal constructor(private val context: Context, itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var chartName: TextView = itemView.findViewById(R.id.chart_name)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = getAdapterPosition()
            if (position != RecyclerView.NO_POSITION) {
                val chart = chartList[position]

                if (position == 0) {
                    intent = Intent(context, PieChartActivity::class.java)
                } else if(position == 1) {
                    intent = Intent(context, ColumnChartActivity::class.java)
                } else if(position == 2) {
                    intent = Intent(context, LineChartActivity::class.java)
                } else if(position == 3) {
                    intent = Intent(context, BarChartActivity::class.java)
                } else if(position == 4) {
                    intent = Intent(context, VennDiagramActivity::class.java)
                }
                context.startActivity(intent)
            }
        }
    }


    init {
        copyChartList = ArrayList(chartList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        return ViewHolder(context, inflater.inflate(R.layout.card_view_chart, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val chart = chartList[position]

        val textView = viewHolder.chartName
        textView.text = chart
    }

    override fun getItemCount(): Int {
        return chartList.size
    }

}
