package com.frogobox.praybox.mvvm.statistik

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.praybox.core.BaseFragment
import com.frogobox.praybox.databinding.FragmentStatistikGrafikBinding
import com.frogobox.praybox.source.DataContract
import com.frogobox.praybox.source.DataOperation
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*

class StatistikGrafikFragment : BaseFragment<FragmentStatistikGrafikBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStatistikGrafikBinding {
        return FragmentStatistikGrafikBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
    }

    override fun onViewCreatedExt(view: View, savedInstanceState: Bundle?) {
        super.onViewCreatedExt(view, savedInstanceState)
        binding.apply {

            // -----------------------------------------------------------------------------------------
            val mBarChart: BarChart = chartId // Diagram Batang
            val leftAxis = mBarChart.getAxis(YAxis.AxisDependency.LEFT) // Inisiasi Sumbu Y kiri
            val rightAxis = mBarChart.getAxis(YAxis.AxisDependency.RIGHT) // Inisiasi Sumbu Y kanan
            val xAxis = mBarChart.xAxis // Inisiasi Sumbu X
            // -----------------------------------------------------------------------------------------
            val barEntries = ArrayList<BarEntry>()
            val barLabels = ArrayList<String>()
            // -----------------------------------------------------------------------------------------
            barLabels.add("")
            val mDataOperation = DataOperation()
            val cursor = mDataOperation.getDataSameDate(context)
            var i = 1
            while (cursor.moveToNext()) {
                // -------------------------------------------------------------------------------------
                val tanggalColoumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_TANGGAL)
                val tanggal = cursor.getString(tanggalColoumnIndex)
                barLabels.add(tanggal)
                // -------------------------------------------------------------------------------------
                val cursorSum = mDataOperation.getDataToday(context, tanggal)
                val countX = i
                val countY = cursorSum.count * 20
                barEntries.add(BarEntry(countX.toFloat(), countY.toFloat()))
                i++
                // -------------------------------------------------------------------------------------
            }
            // -----------------------------------------------------------------------------------------
            val barDataSet = BarDataSet(barEntries, "Poin Shalat")
            barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
            val barData = BarData(barDataSet)
            // -----------------------------------------------------------------------------------------
            CreateBarChart(mBarChart, xAxis, leftAxis, rightAxis, barData, barLabels)
        }
    }

    private fun CreateBarChart(
        mBarchart: BarChart,
        mXAxis: XAxis,
        mLeftAxis: YAxis,
        mRightAxis: YAxis,
        mBarData: BarData,
        mBarLabels: ArrayList<String>
    ) {
        mBarchart.data = mBarData
        // -----------------------------------------------------------------------------------------
        mBarchart.setScaleEnabled(false)
        mBarchart.description.isEnabled = false
        mBarchart.animateY(2000)
        mBarchart.setVisibleXRange(1f, 3f)
        mBarchart.setVisibleYRange(20f, 100f, YAxis.AxisDependency.LEFT)
        // -----------------------------------------------------------------------------------------
        // Sumbu X
        mXAxis.valueFormatter = IndexAxisValueFormatter(mBarLabels)
        mXAxis.isEnabled = true
        mXAxis.setDrawGridLines(false)
        mXAxis.textSize = 7f
        mXAxis.position = XAxis.XAxisPosition.BOTTOM
        // -----------------------------------------------------------------------------------------
        // Garis Bagian Kanan
        mRightAxis.setDrawAxisLine(false)
        mRightAxis.setDrawLabels(false)
        mRightAxis.setDrawGridLines(false)
        // -----------------------------------------------------------------------------------------
        // Garis Bagian Kiri
        mLeftAxis.setDrawGridLines(false)
        mLeftAxis.labelCount = 5
        mLeftAxis.axisMinimum = 0f
        mLeftAxis.axisMaximum = 100f
        // -----------------------------------------------------------------------------------------
    }

}