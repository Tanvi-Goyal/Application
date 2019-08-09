package com.example.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.NameValueDataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.chart.common.listener.Event
import com.anychart.chart.common.listener.ListenersInterface
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
import kotlinx.android.synthetic.main.any_chart_common.*
import java.util.ArrayList

class VennDiagramActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.any_chart_common)

        val venn = AnyChart.venn()

        val data = ArrayList<DataEntry>()
        data.add(NameValueDataEntry("A", "Data Science", 100))
        data.add(NameValueDataEntry("B", "Computer Science", 25))
        data.add(NameValueDataEntry("C", "Math and Statistics", 25))
        data.add(NameValueDataEntry("D", "Subject Matter Expertise", 25))
        data.add(NameValueDataEntry("A&B", "Computer Science", 50))
        data.add(NameValueDataEntry("A&C", "Math and Statistics", 50))
        data.add(NameValueDataEntry("A&D", "Subject Matter Expertise", 50))
        data.add(NameValueDataEntry("B&C", "Machine\\nLearning", 5))
        data.add(NameValueDataEntry("C&D", "Traditional\\nResearch", 5))
        data.add(NameValueDataEntry("D&B", "Traditional\\nSoftware", 5))
        data.add(NameValueDataEntry("B&C&D", "Unicorn", 5))

        venn.data(data)

        venn.stroke("2 #FFFFFF")

        venn.labels().format("{%Name}")

        venn.intersections().hovered().fill("black", 0.25)

        venn.intersections().labels().fontWeight("bold")
        venn.intersections().labels().format("{%Name}")

        venn.tooltip().titleFormat("{%Name}")

        any_chart_view.setChart(venn)
    }
}
