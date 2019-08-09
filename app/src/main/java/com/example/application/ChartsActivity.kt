package com.example.application

import RoundedBarChartRenderer
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a.ListAdapter
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.model.GradientColor
import com.reddit.indicatorfastscroll.FastScrollItemIndicator
import com.reddit.indicatorfastscroll.FastScrollerView
import kotlinx.android.synthetic.main.activity_charts.*
import kotlinx.android.synthetic.main.chart_bar.*
import kotlinx.android.synthetic.main.chart_bar_stacked.*
import kotlinx.android.synthetic.main.chart_line.*
import kotlinx.android.synthetic.main.chart_line_cubic.*
import java.util.*
import kotlin.collections.ArrayList


class ChartsActivity : AppCompatActivity() {

    data class Team(val image: String, val name: String, val skills: String)

    private lateinit var fastScrollerView: FastScrollerView

    // dummy data list
    private val teamMembers = listOf(
        Team(
            "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50&sa=D&source=hangouts&ust=1565171884220000&usg=AFQjCNFRVS4fmovK-zMHZDJKSB9zY_m7Hg",
            "Abhay Singh",
            "iOS | Android | Python | MySQL"
        ),
        Team(
            "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50",
            "Abhilasha Kumar",
            "Python | Swift | BASIC"
        ),
        Team("", "Arzoo", "PHP | Python | Ruby"),
        Team("", "Bineet Kapoor Roy", "Wireframing | High-Fed Prototyping"),
        Team("", "Chetna Gupta", "iOS | Android | Python | MySQL"),
        Team(
            "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50",
            "Deepak Sharma",
            "Python | Swift | BASIC"
        ),
        Team(
            "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50&sa=D&source=hangouts&ust=1565171884220000&usg=AFQjCNFRVS4fmovK-zMHZDJKSB9zY_m7Hg",
            "Abhay Singh",
            "iOS | Android | Python | MySQL"
        ),
        Team(
            "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50",
            "Abhilasha Kumar",
            "Python | Swift | BASIC"
        ),
        Team("", "Arzoo", "PHP | Python | Ruby"),
        Team("", "Bineet Kapoor Roy", "Wireframing | High-Fed Prototyping"),
        Team("", "Chetna Gupta", "iOS | Android | Python | MySQL"),
        Team(
            "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50",
            "Deepak Sharma",
            "Python | Swift | BASIC"
        ),
        Team(
            "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50&sa=D&source=hangouts&ust=1565171884220000&usg=AFQjCNFRVS4fmovK-zMHZDJKSB9zY_m7Hg",
            "Abhay Singh",
            "iOS | Android | Python | MySQL"
        ),
        Team(
            "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50",
            "Abhilasha Kumar",
            "Python | Swift | BASIC"
        ),
        Team("", "Arzoo", "PHP | Python | Ruby"),
        Team("", "Bineet Kapoor Roy", "Wireframing | High-Fed Prototyping"),
        Team("", "Chetna Gupta", "iOS | Android | Python | MySQL"),
        Team(
            "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50",
            "Deepak Sharma",
            "Python | Swift | BASIC"
        ),
        Team(
            "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50&sa=D&source=hangouts&ust=1565171884220000&usg=AFQjCNFRVS4fmovK-zMHZDJKSB9zY_m7Hg",
            "Abhay Singh",
            "iOS | Android | Python | MySQL"
        ),
        Team(
            "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50",
            "Abhilasha Kumar",
            "Python | Swift | BASIC"
        ),
        Team("", "Arzoo", "PHP | Python | Ruby"),
        Team("", "Bineet Kapoor Roy", "Wireframing | High-Fed Prototyping"),
        Team("", "Chetna Gupta", "iOS | Android | Python | MySQL"),
        Team(
            "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50",
            "Deepak Sharma",
            "Python | Swift | BASIC"
        )

    )

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts)

        text_monthly.setOnClickListener {
            text_monthly.background = ContextCompat.getDrawable(this, R.drawable.card_selected)
            text_weekly.background = ContextCompat.getDrawable(this, R.drawable.card_unselected)
            text_yearly.background = ContextCompat.getDrawable(this, R.drawable.card_unselected)

            text_monthly.setTextColor(R.color.color_selected)
            text_weekly.setTextColor(R.color.color_unselected)
            text_yearly.setTextColor(R.color.color_unselected)
        }

        text_weekly.setOnClickListener {
            text_weekly.background = ContextCompat.getDrawable(this, R.drawable.card_selected)
            text_monthly.background = ContextCompat.getDrawable(this, R.drawable.card_unselected)
            text_yearly.background = ContextCompat.getDrawable(this, R.drawable.card_unselected)

            text_weekly.setTextColor(R.color.color_selected)
            text_monthly.setTextColor(R.color.color_unselected)
            text_yearly.setTextColor(R.color.color_unselected)

        }

        text_yearly.setOnClickListener {
            text_yearly.background = ContextCompat.getDrawable(this, R.drawable.card_selected)
            text_weekly.background = ContextCompat.getDrawable(this, R.drawable.card_unselected)
            text_monthly.background = ContextCompat.getDrawable(this, R.drawable.card_unselected)

            text_yearly.setTextColor(R.color.color_selected)
            text_weekly.setTextColor(R.color.color_unselected)
            text_monthly.setTextColor(R.color.color_unselected)
        }

        // setting up charts
        setLineChartCubic()
        setBarChart()
        setBarChartStacked()
        setLineChart()

        // recycler with fast scroll implementation
        sample_styled_recyclerview.apply {

            layoutManager = LinearLayoutManager(this.context)
            adapter = ListAdapter(teamMembers)
        }


        fastScrollerView = findViewById(R.id.sample_styled_fastscroller)
        fastScrollerView.setupWithRecyclerView(
            sample_styled_recyclerview,
            { position ->
                val item = teamMembers[position] // Get your model object
                FastScrollItemIndicator.Text(
                    getAlphabets()
                ) // Return a text indicator
            }
        )


        fastscroller_thumb.setupWithFastScroller(fastScrollerView)

    }

    private fun getAlphabets(): String {
        val alphabets = "A\n" + "B\n" + "C\n" + "D\n" + "E\n" + "F\n" + "G\n" + "H\n" + "I\n" + "J\n" + "K\n" + "L\n" + "M\n" + "N\n" + "O\n" +
                "P\n" + "Q\n" + "R\n" + "S\n" + "T\n" + "U\n" + "V\n" + "W\n" + "X\n" + "Y\n" + "Z\n"

        return alphabets

    }

    private fun setLineChartCubic() {
        val xAxisValues = ArrayList(

            Arrays.asList(
                "30-6 Jul",
                "7-13 Jul",
                "14-20 Jul",
                "21-27 Jul",
                "28-03 Aug"
            )
        )

        val yVals = ArrayList<Entry>()
        yVals.add(Entry(0f, 25f, "0"))
        yVals.add(Entry(1f, 18f, "1"))
        yVals.add(Entry(2f, 20f, "2"))
        yVals.add(Entry(3f, 16f, "3"))
        yVals.add(Entry(4f, 22f, "4"))


        val set1: LineDataSet
        set1 = LineDataSet(yVals, "")

        set1.mode = LineDataSet.Mode.CUBIC_BEZIER
        set1.color = Color.BLUE
        set1.setCircleColor(Color.BLACK)
        set1.lineWidth = 2f
        set1.circleRadius = 4f
        set1.setDrawCircleHole(true)
        set1.valueTextSize = 0f
        set1.setDrawFilled(true)
        set1.fillDrawable = ContextCompat.getDrawable(this, R.drawable.gradient)


        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)
        val data = LineData(dataSets)

        // set data
        chart_line_cubic.setData(data)
        chart_line_cubic.description.isEnabled = false
        chart_line_cubic.legend.isEnabled = false
        chart_line_cubic.setPinchZoom(false)
        chart_line_cubic.xAxis.setDrawGridLines(false) // disabling horizontal background grid lines

        //lineChart.setDrawGridBackground()
        chart_line_cubic.xAxis.labelCount = 5

        chart_line_cubic.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart_line_cubic.xAxis
            .setValueFormatter(com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues))

        //***
        // Controlling right side of y axis
        val yAxisRight = chart_line_cubic.axisRight
        yAxisRight.isEnabled = false

        //***
        // Controlling left side of y axis
        val yAxisLeft = chart_line_cubic.axisLeft
        yAxisLeft.granularity = 1f

        chart_line_cubic.animateY(1000)


    }

    private fun setBarChart() {

        val bargroup = ArrayList<BarEntry>()
        bargroup.add(BarEntry(0f, 30f, "0"))
        bargroup.add(BarEntry(1f, 12f, "1"))
        bargroup.add(BarEntry(2f, 21f, "2"))
        bargroup.add(BarEntry(3f, 18f, "3"))
        bargroup.add(BarEntry(4f, 25f, "4"))


        val startColor1 = ContextCompat.getColor(this, R.color.lightBlue)
        val startColor2 = ContextCompat.getColor(this, R.color.darkBlue)
        val endColor1 = ContextCompat.getColor(this, R.color.darkBlue)
        val endColor2 = ContextCompat.getColor(this, R.color.lightBlue)

        val gradientColors = java.util.ArrayList<GradientColor>()
        gradientColors.add(GradientColor(startColor1, endColor1))
        gradientColors.add(GradientColor(startColor2, endColor2))

        // creating dataset for Bar Group
        val barDataSet = BarDataSet(bargroup, "")

        barDataSet.setGradientColors(gradientColors)

//        barDataSet.setGradientColor(R.color.lightBlue, R.color.middleBlue)
        val data = BarData(barDataSet)
        chart_bar.setData(data)
        chart_bar.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart_bar.xAxis.labelCount = 5


        chart_bar.description.isEnabled = false
        chart_bar.animateY(1000)
        chart_bar.legend.isEnabled = false
        chart_bar.setPinchZoom(false)
        chart_bar.data.setDrawValues(false)
        chart_bar.xAxis.setDrawGridLines(false)
        chart_bar.data.barWidth = 0.5f
        chart_bar.setFitBars(true)
//        bar_chart_two.background = ContextCompat.getDrawable(this, R.drawable.gradient)
        //***
        // Controlling right side of y axis
        val yAxisRight = chart_bar.axisRight
        yAxisRight.isEnabled = false

        //***
        // Controlling left side of y axis
        val yAxisLeft = chart_bar.axisLeft
        yAxisLeft.granularity = 1f
        chart_bar.invalidate()

        val roundedBarChartRenderer =
            RoundedBarChartRenderer(this, chart_bar, chart_bar.getAnimator(), chart_bar.getViewPortHandler())
        roundedBarChartRenderer.setmRadius(20f)
        chart_bar.setRenderer(roundedBarChartRenderer)


    }

    private fun setBarChartStacked() {

        val bargroup = ArrayList<BarEntry>()
        bargroup.add(BarEntry(0f, 30f, "0"))
        bargroup.add(BarEntry(1f, 12f, "1"))
        bargroup.add(BarEntry(2f, 21f, "2"))
        bargroup.add(BarEntry(3f, 18f, "3"))
        bargroup.add(BarEntry(4f, 25f, "4"))


//            R.color.darkBlue
        val startColor1 = ContextCompat.getColor(this, R.color.lightBlue)
        val startColor2 = ContextCompat.getColor(this, R.color.darkBlue)
        val endColor1 = ContextCompat.getColor(this, R.color.darkBlue)
        val endColor2 = ContextCompat.getColor(this, R.color.lightBlue)

        val gradientColors = java.util.ArrayList<GradientColor>()
        gradientColors.add(GradientColor(startColor1, endColor1))
        gradientColors.add(GradientColor(startColor2, endColor2))

        // creating dataset for Bar Group
        val barDataSet = BarDataSet(bargroup, "")

        barDataSet.setGradientColors(gradientColors)

//        barDataSet.setGradientColor(R.color.lightBlue, R.color.middleBlue)
        val data = BarData(barDataSet)
        chart_bar_stacked.setData(data)
        chart_bar_stacked.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart_bar_stacked.xAxis.labelCount = 5

        chart_bar_stacked.description.isEnabled = false
        chart_bar_stacked.animateY(1000)
        chart_bar_stacked.legend.isEnabled = false
        chart_bar_stacked.setPinchZoom(false)
        chart_bar_stacked.data.setDrawValues(false)
        chart_bar_stacked.xAxis.setDrawGridLines(false)
        chart_bar_stacked.data.barWidth = 0.5f

        chart_bar_stacked.setDrawBarShadow(true)
        //***
        // Controlling right side of y axis
        val yAxisRight = chart_bar_stacked.axisRight
        yAxisRight.isEnabled = false

        //***
        // Controlling left side of y axis
        val yAxisLeft = chart_bar_stacked.axisLeft
        yAxisLeft.isEnabled = false

        chart_bar_stacked.invalidate()

        val roundedBarChartRenderer =
            RoundedBarChartRenderer(
                this,
                chart_bar_stacked,
                chart_bar_stacked.getAnimator(),
                chart_bar_stacked.getViewPortHandler()
            )
        roundedBarChartRenderer.setmRadius(20f)
        chart_bar_stacked.setRenderer(roundedBarChartRenderer)


    }

    private fun setLineChart() {
        val xAxisValues = ArrayList(

            Arrays.asList(
                "30-06 Jul",
                "07-13 Jul",
                "14-20 Jul",
                "21-27 Jul",
                "28-03 Aug"
            )
        )

        val yVals = ArrayList<Entry>()
        yVals.add(Entry(0f, 25f, "0"))
        yVals.add(Entry(1f, 18f, "1"))
        yVals.add(Entry(2f, 20f, "2"))
        yVals.add(Entry(3f, 16f, "3"))
        yVals.add(Entry(4f, 22f, "4"))


        val set1: LineDataSet
        set1 = LineDataSet(yVals, "")

        set1.color = Color.BLUE
        set1.setCircleColor(Color.BLACK)
        set1.lineWidth = 2f
        set1.circleRadius = 4f
        set1.setDrawCircleHole(true)
        set1.valueTextSize = 0f
        set1.setDrawFilled(true)
        set1.fillDrawable = ContextCompat.getDrawable(this, R.drawable.gradient)


        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)
        val data = LineData(dataSets)

        // set data
        chart_line.setData(data)
        chart_line.description.isEnabled = false
        chart_line.legend.isEnabled = false
        chart_line.setPinchZoom(false)
        chart_line.xAxis.setDrawGridLines(false) // disabling horizontal background grid lines

        //lineChart.setDrawGridBackground()
        chart_line.xAxis.labelCount = 5

        chart_line.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart_line.xAxis
            .setValueFormatter(com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues))

        //***
        // Controlling right side of y axis
        val yAxisRight = chart_line.axisRight
        yAxisRight.isEnabled = false

        //***
        // Controlling left side of y axis
        val yAxisLeft = chart_line.axisLeft
        yAxisLeft.granularity = 1f

        chart_line.animateY(1000)


    }

}




