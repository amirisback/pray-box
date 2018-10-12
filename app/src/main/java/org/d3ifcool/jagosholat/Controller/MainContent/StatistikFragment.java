package org.d3ifcool.jagosholat.Controller.MainContent;


import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.d3ifcool.jagosholat.Controller.StatistikFragmentContent.StatistikCursorAdapter;
import org.d3ifcool.jagosholat.Controller.StatistikFragmentContent.StatistikWord;
import org.d3ifcool.jagosholat.Controller.Helper.DialogFormHelper;
import org.d3ifcool.jagosholat.Controller.Helper.MethodHelper;
import org.d3ifcool.jagosholat.Model.DataContract;
import org.d3ifcool.jagosholat.Model.DataOperation;
import org.d3ifcool.jagosholat.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatistikFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    // ---------------------------------------------------------------------------------------------
    // Deklarasi Kebutuhan
    private static final int DATA_LOADER = 0;
    private ArrayList<StatistikWord> arrayWord = new ArrayList<>();
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // Deklarasi XML Alert Dialog
    private AlertDialog.Builder mDialogBuilder;
    private LayoutInflater mInflater;
    private View mDialogView;
    private TextView mTextViewWaktu;
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    private LineChart mChart;
    // ---------------------------------------------------------------------------------------------
    private MethodHelper methodHelper = new MethodHelper();
    private DataOperation crud = new DataOperation();
    private Cursor cursorTanggal, cursorCount;
    private String [] days;
    // ---------------------------------------------------------------------------------------------


    // ---------------------------------------------------------------------------------------------
    // Deklarasi Class Helper yang diperlukan
    private MethodHelper mMethodHelper = new MethodHelper();
    private DataOperation mDataOperation = new DataOperation();
    private DialogFormHelper mDialogForm;
    private StatistikCursorAdapter mCursorAdapter;
    // ---------------------------------------------------------------------------------------------

    public StatistikFragment() {
        // Required empty public constructor
    }


    public boolean isEmptyTanggal(){
        try {
            cursorTanggal = crud.getSemuaTanggal(getContext());
            int cek = cursorTanggal.getCount();
            return cek == 0;
        } finally {
            cursorTanggal.close();
        }

    }


//    public void CreateGrafik(){
//        // -----------------------------------------------------------------------------------------
//        mChart.setBorderColor(Color.GREEN);
//        mChart.setDragEnabled(true);
//        mChart.setScaleEnabled(false);
//        mChart.canScrollHorizontally(10);
//        mChart.setDescription(null);
//        mChart.setMinimumHeight(20);
//        // -----------------------------------------------------------------------------------------
//        ArrayList<Entry> yValues = new ArrayList<>();
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        // -----------------------------------------------------------------------------------------
//        if (!isEmptyTanggal()) {
//            try {
//                cursorTanggal = crud.getSemuaTanggal(getContext());
//                days = new String[cursorTanggal.getCount()];
//                float xData = 0;
//                int i = 0;
//                // ---------------------------------------------------------------------------------
//                while (cursorTanggal.moveToNext()){
//                    // -----------------------------------------------------------------------------
//                    int tanggalColumnIndex = cursorTanggal.getColumnIndex(DataContract.DataEntry.COLUMN_TANGGAL); // Mencari index dalam database
//                    String tanggal = cursorTanggal.getString(tanggalColumnIndex); // Mendapat data dari database berdasarkan index
//                    // -----------------------------------------------------------------------------
//                    try {
//                        cursorCount = crud.getDataTanggal(getContext(), tanggal);
//                        int jumlahData = cursorCount.getCount();
//                        float yData  = (float) jumlahData * 2;
//                        // -------------------------------------------------------------------------
//                        yValues.add(new Entry(xData, yData)); // Posisi di grafik
//                    } finally {
//                        cursorCount.close();
//                    }
//                    // -----------------------------------------------------------------------------
//                    String noYears = tanggal.substring(0,tanggal.length()-5);
//                    days[i] = noYears;
//                    // -----------------------------------------------------------------------------
//                    xData++;
//                    i++;
//                }
//            } finally {
//                cursorTanggal.close();
//            }
//        } else {
//            yValues.add(new Entry(0, 0f));
//            yValues.add(new Entry(1, 0f));
//            yValues.add(new Entry(2, 0f));
//            yValues.add(new Entry(3, 0f));
//            yValues.add(new Entry(4, 0f));
//            yValues.add(new Entry(5, 0f));
//            yValues.add(new Entry(6, 0f));
//            days = new String[7];
//            for (int i = 0; i<days.length ; i++){
//                days[i] = "0";
//            }
//        }
//
//        LineDataSet mLineDataSet = new LineDataSet(yValues, "Grafik PerHari Tahun " + methodHelper.getSystemYear());
//        // -----------------------------------------------------------------------------------------
//        mLineDataSet.setFillAlpha(10);
//        mLineDataSet.setColor(Color.BLACK);
//        mLineDataSet.setLineWidth(3f);
//        mLineDataSet.setValueTextSize(10f);
//        mLineDataSet.setValueTextColor(Color.BLACK);
//        mLineDataSet.setLabel("ASASAS");
//        // -----------------------------------------------------------------------------------------
//        dataSets.add(mLineDataSet);
//        LineData data = new LineData(dataSets);
//        // -----------------------------------------------------------------------------------------
//        mChart.setData(data);
//        XAxis xAxis = mChart.getXAxis();
//        xAxis.setValueFormatter( new StatistikFragment.MyXAxisValueFormatter(days));
//
//    }

    // ---------------------------------------------------------------------------------------------
    public int getProgress(){
        Cursor cursor = mDataOperation.getDataTanggal(getContext(), mMethodHelper.getDateToday());
        int countTable = cursor.getCount();
        int progress = countTable * 20;
        return progress;
    }
    // ---------------------------------------------------------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_statistik, container, false);

        // -----------------------------------------------------------------------------------------
        View empty_listView = rootView.findViewById(R.id.empty_views);
        ListView mListView = rootView.findViewById(R.id.ListData);
        ProgressBar mProgressBar = rootView.findViewById(R.id.stat_progressBar);
        TextView mTextViewPercentage = rootView.findViewById(R.id.txt_percentage);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // Deklarasi Element XML Update View
        mDialogBuilder = new AlertDialog.Builder(getActivity());
        mInflater = getLayoutInflater();
        mDialogView = inflater.inflate(R.layout.content_statistik_update, null);
        mTextViewWaktu = mDialogView.findViewById(R.id.txt_waktu_update);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        mListView.setEmptyView(empty_listView);
        mDialogForm = new DialogFormHelper(mDialogBuilder,inflater, mDialogView, mTextViewWaktu, mMethodHelper, getActivity(), mDataOperation);
        mCursorAdapter = new StatistikCursorAdapter(getContext(), null);
        mListView.setAdapter(mCursorAdapter);

        getDataFromTable();
        mProgressBar.setProgress(getProgress());
        String percen = getProgress() + "%";
        mTextViewPercentage.setText(percen);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ---------------------------------------------------------------------------------
                String getArrayId = arrayWord.get(position).getId();
                String getArrayWaktu = arrayWord.get(position).getWaktu();
                String getArrayShalat = arrayWord.get(position).getShalat();
                mDialogForm.DialogForm(getArrayId, getArrayWaktu);
                // ---------------------------------------------------------------------------------
            }
        });

        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // mChart = (LineChart) rootView.findViewById(R.id.stat_chart);
        // -----------------------------------------------------------------------------------------
        // CreateGrafik();
        // -----------------------------------------------------------------------------------------

        return rootView;
    }

    // ---------------------------------------------------------------------------------------------

    public void getDataFromTable() {
        Cursor cursor = mDataOperation.getDataTanggal(getContext(), mMethodHelper.getDateToday());
        while (cursor.moveToNext()){
            // -------------------------------------------------------------------------------------
            int idColoumnIndex = cursor.getColumnIndex(DataContract.DataEntry._ID);
            int shalatColoumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_SHALAT);
            int waktuColoumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_WAKTU);
            // -------------------------------------------------------------------------------------
            String id = cursor.getString(idColoumnIndex);
            String shalat = cursor.getString(shalatColoumnIndex);
            String waktu = cursor.getString(waktuColoumnIndex);
            // -------------------------------------------------------------------------------------
            arrayWord.add(new StatistikWord(id, waktu, shalat));
            // -------------------------------------------------------------------------------------
        }
    }
    // ---------------------------------------------------------------------------------------------




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(DATA_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String selection = DataContract.DataEntry.COLUMN_TANGGAL + " = '" + mMethodHelper.getDateToday() + "'";
        return new CursorLoader(getActivity(), // getActivity disini menggantikan this
                DataContract.DataEntry.CONTENT_URI,
                mDataOperation.getProjection(),
                selection,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

    // ---------------------------------------------------------------------------------------------
    public static class MyXAxisValueFormatter implements IAxisValueFormatter {
        private String[] mValues;
        public MyXAxisValueFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int) value];
        }
    }

}
