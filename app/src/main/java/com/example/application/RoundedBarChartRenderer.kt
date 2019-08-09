
import android.content.Context
import android.graphics.Canvas
import com.github.mikephil.charting.buffer.BarBuffer
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ViewPortHandler
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider
import com.github.mikephil.charting.renderer.BarChartRenderer

import android.graphics.RectF
import androidx.core.content.ContextCompat
import com.example.application.R
import com.example.application.R.color.*

class RoundedBarChartRenderer constructor(var context: Context, chart: BarDataProvider, animator: ChartAnimator, viewPortHandler: ViewPortHandler) :
    BarChartRenderer(chart, animator, viewPortHandler) {

    private var mRadius = 100f

    fun setmRadius(mRadius: Float) {
        this.mRadius = mRadius
    }

    override fun drawDataSet( c: Canvas, dataSet: IBarDataSet, index: Int) {

        val trans = mChart.getTransformer(dataSet.axisDependency)

        mShadowPaint.color = ContextCompat.getColor(context, lightBlue)

        val phaseX = mAnimator.phaseX
        val phaseY = mAnimator.phaseY


        // initialize the buffer
        val buffer = mBarBuffers[index]
        buffer.setPhases(phaseX, phaseY)
        buffer.setDataSet(index)
        buffer.setBarWidth(mChart.barData.barWidth)
        buffer.setInverted(mChart.isInverted(dataSet.axisDependency))

        buffer.feed(dataSet)

        trans.pointValuesToPixel(buffer.buffer)

        // if multiple colors
        if (dataSet.colors.size > 1) {

            var j = 0
            while (j < buffer.size()) {

                if (!mViewPortHandler.isInBoundsLeft(buffer.buffer[j + 2])) {
                    j += 4
                    continue
                }

                if (!mViewPortHandler.isInBoundsRight(buffer.buffer[j]))
                    break

                if (mChart.isDrawBarShadowEnabled) {
                    if (mRadius > 0)
                        c.drawRoundRect(
                            RectF(
                                buffer.buffer[j], mViewPortHandler.contentTop(),
                                buffer.buffer[j + 2],
                                mViewPortHandler.contentBottom()
                            ), mRadius, mRadius, mShadowPaint
                        )
                    else
                        c.drawRect(
                            buffer.buffer[j], mViewPortHandler.contentTop(),
                            buffer.buffer[j + 2],
                            mViewPortHandler.contentBottom(), mShadowPaint
                        )
                }

                // Set the color for the currently drawn value. If the index
                // is
                // out of bounds, reuse colors.
                mRenderPaint.color = dataSet.getColor(j / 4)
                if (mRadius > 0)
                    c.drawRoundRect(
                        RectF(
                            buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2],
                            buffer.buffer[j + 3]
                        ), mRadius, mRadius, mRenderPaint
                    )
                else
                    c.drawRect(
                        buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2],
                        buffer.buffer[j + 3], mRenderPaint
                    )
                j += 4
            }
        } else {

            mRenderPaint.color = ContextCompat.getColor(context, middleBlue)

            var j = 0
            while (j < buffer.size()) {

                if (!mViewPortHandler.isInBoundsLeft(buffer.buffer[j + 2])) {
                    j += 4
                    continue
                }

                if (!mViewPortHandler.isInBoundsRight(buffer.buffer[j]))
                    break

                if (mChart.isDrawBarShadowEnabled) {
                    if (mRadius > 0)
                        c.drawRoundRect(
                            RectF(
                                buffer.buffer[j], mViewPortHandler.contentTop(),
                                buffer.buffer[j + 2],
                                mViewPortHandler.contentBottom()
                            ), mRadius, mRadius, mShadowPaint
                        )
                    else
                        c.drawRect(
                            buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2],
                            buffer.buffer[j + 3], mRenderPaint
                        )
                }

                if (mRadius > 0)
                    c.drawRoundRect(
                        RectF(
                            buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2],
                            buffer.buffer[j + 3]
                        ), mRadius, mRadius, mRenderPaint
                    )
                else
                    c.drawRect(
                        buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2],
                        buffer.buffer[j + 3], mRenderPaint
                    )
                j += 4
            }
        }
    }
}