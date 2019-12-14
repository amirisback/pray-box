package com.frogobox.praybox.ui.fragment;


import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frogobox.praybox.source.local.DataContract;
import com.frogobox.praybox.source.local.DataOperation;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import com.frogobox.praybox.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatistikGrafikFragment extends Fragment{

    private DataOperation mDataOperation = new DataOperation();

    public StatistikGrafikFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_statistik_grafik, container, false);
        // -----------------------------------------------------------------------------------------
        BarChart mBarChart = rootView.findViewById(R.id.chart_id); // Diagram Batang
        YAxis leftAxis = mBarChart.getAxis(YAxis.AxisDependency.LEFT); // Inisiasi Sumbu Y kiri
        YAxis rightAxis = mBarChart.getAxis(YAxis.AxisDependency.RIGHT); // Inisiasi Sumbu Y kanan
        XAxis xAxis = mBarChart.getXAxis(); // Inisiasi Sumbu X
        // -----------------------------------------------------------------------------------------
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<String> barLabels = new ArrayList<>();
        // -----------------------------------------------------------------------------------------
        barLabels.add("");
        Cursor cursor = mDataOperation.getDataSameDate(getContext());
        int i=1;
        while (cursor.moveToNext()) {
            // -------------------------------------------------------------------------------------
            int tanggalColoumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_TANGGAL);
            String tanggal = cursor.getString(tanggalColoumnIndex);
            barLabels.add(tanggal);
            // -------------------------------------------------------------------------------------
            Cursor cursorSum = mDataOperation.getDataToday(getContext(), tanggal);
            int countX = i;
            int countY = cursorSum.getCount() * 20;
            barEntries.add(new BarEntry(countX, countY));
            i++;
            // -------------------------------------------------------------------------------------
        }
        // -----------------------------------------------------------------------------------------
        BarDataSet barDataSet = new BarDataSet(barEntries, "Poin Shalat");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData = new BarData(barDataSet);
        // -----------------------------------------------------------------------------------------
        CreateBarChart(mBarChart, xAxis, leftAxis, rightAxis, barData, barLabels);
        return rootView;
    }

    public void CreateBarChart(BarChart mBarchart, XAxis mXAxis, YAxis mLeftAxis, YAxis mRightAxis, BarData mBarData, ArrayList<String> mBarLabels){
        mBarchart.setData(mBarData);
        // -----------------------------------------------------------------------------------------
        mBarchart.setScaleEnabled(false);
        mBarchart.getDescription().setEnabled(false);
        mBarchart.animateY(2000);
        mBarchart.setVisibleXRange(1,3);
        mBarchart.setVisibleYRange(20,100,YAxis.AxisDependency.LEFT);
        // -----------------------------------------------------------------------------------------
        // Sumbu X
        mXAxis.setValueFormatter(new IndexAxisValueFormatter(mBarLabels));
        mXAxis.setEnabled(true);
        mXAxis.setDrawGridLines(false);
        mXAxis.setTextSize(7f);
        mXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // -----------------------------------------------------------------------------------------
        // Garis Bagian Kanan
        mRightAxis.setDrawAxisLine(false);
        mRightAxis.setDrawLabels(false);
        mRightAxis.setDrawGridLines(false);
        // -----------------------------------------------------------------------------------------
        // Garis Bagian Kiri
        mLeftAxis.setDrawGridLines(false);
        mLeftAxis.setLabelCount(5);
        mLeftAxis.setAxisMinimum(0);
        mLeftAxis.setAxisMaximum(100);
        // -----------------------------------------------------------------------------------------
    }

}