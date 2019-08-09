package com.example.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.chart.common.listener.Event
import com.anychart.chart.common.listener.ListenersInterface
import com.anychart.data.Set
import com.anychart.enums.*
import kotlinx.android.synthetic.main.any_chart_common.*
import java.util.ArrayList

class BarChartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.any_chart_common)

        val barChart = AnyChart.bar()

        barChart.animation(true)

        barChart.padding(10.0, 20.0, 5.0, 20.0)

        barChart.yScale().stackMode(ScaleStackMode.VALUE)

        barChart.yAxis(0).labels().format(
            "function() {\n" +
                    "    return Math.abs(this.value).toLocaleString();\n" +
                    "  }"
        )

        barChart.yAxis(0.0).title("Revenue in Dollars")

        barChart.xAxis(0.0).overlapMode(LabelsOverlapMode.ALLOW_OVERLAP)

        val xAxis1 = barChart.xAxis(1.0)
        xAxis1.enabled(true)
        xAxis1.orientation(Orientation.RIGHT)
        xAxis1.overlapMode(LabelsOverlapMode.ALLOW_OVERLAP)

        barChart.title("Cosmetic Sales by Gender")

        barChart.interactivity().hoverMode(HoverMode.BY_X)

        barChart.tooltip()
            .title(false)
            .separator(false)
            .displayMode(TooltipDisplayMode.SEPARATED)
            .positionMode(TooltipPositionMode.POINT)
            .useHtml(true)
            .fontSize(12.0)
            .offsetX(5.0)
            .offsetY(0.0)
            .format(
                ("function() {\n" +
                        "      return '<span style=\"color: #D9D9D9\">$</span>' + Math.abs(this.value).toLocaleString();\n" +
                        "    }")
            )

        val seriesData = ArrayList<DataEntry>()
        seriesData.add(CustomDataEntry("Nail polish", 5376, -229))
        seriesData.add(CustomDataEntry("Eyebrow pencil", 10987, -932))
        seriesData.add(CustomDataEntry("Rouge", 7624, -5221))
        seriesData.add(CustomDataEntry("Lipstick", 8814, -256))
        seriesData.add(CustomDataEntry("Eyeshadows", 8998, -308))
        seriesData.add(CustomDataEntry("Eyeliner", 9321, -432))
        seriesData.add(CustomDataEntry("Foundation", 8342, -701))
        seriesData.add(CustomDataEntry("Lip gloss", 6998, -908))
        seriesData.add(CustomDataEntry("Mascara", 9261, -712))
        seriesData.add(CustomDataEntry("Shampoo", 5376, -9229))
        seriesData.add(CustomDataEntry("Hair conditioner", 10987, -13932))
        seriesData.add(CustomDataEntry("Body lotion", 7624, -10221))
        seriesData.add(CustomDataEntry("Shower gel", 8814, -12256))
        seriesData.add(CustomDataEntry("Soap", 8998, -5308))
        seriesData.add(CustomDataEntry("Eye fresher", 9321, -432))
        seriesData.add(CustomDataEntry("Deodorant", 8342, -11701))
        seriesData.add(CustomDataEntry("Hand cream", 7598, -5808))
        seriesData.add(CustomDataEntry("Foot cream", 6098, -3987))
        seriesData.add(CustomDataEntry("Night cream", 6998, -847))
        seriesData.add(CustomDataEntry("Day cream", 5304, -4008))
        seriesData.add(CustomDataEntry("Vanila cream", 9261, -712))

        val set = Set.instantiate()
        set.data(seriesData)
        val series1Data = set.mapAs("{ x: 'x', value: 'value' }")
        val series2Data = set.mapAs("{ x: 'x', value: 'value2' }")

        val series1 = barChart.bar(series1Data)
        series1.name("Females")
            .color("HotPink")
        series1.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)

        val series2 = barChart.bar(series2Data)
        series2.name("Males")
        series2.tooltip()
            .position("left")
            .anchor(Anchor.RIGHT_CENTER)

        barChart.legend().enabled(true)
        barChart.legend().inverted(true)
        barChart.legend().fontSize(13.0)
        barChart.legend().padding(0.0, 0.0, 20.0, 0.0)

        any_chart_view.setChart(barChart)
    }

    private inner class CustomDataEntry internal constructor(x: String, value: Number, value2: Number) :
        ValueDataEntry(x, value) {
        init {
            setValue("value2", value2)
        }
    }
}
