package org.d3ifcool.jagosholat.presenters.fragments;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.d3ifcool.jagosholat.presenters.adapters.StatistikHarianCursorRecyclerAdapter;
import org.d3ifcool.jagosholat.presenters.helpers.DialogFormHelper;
import org.d3ifcool.jagosholat.presenters.helpers.MethodHelper;
import org.d3ifcool.jagosholat.models.DataContract;
import org.d3ifcool.jagosholat.models.DataOperation;
import org.d3ifcool.jagosholat.R;


public class StatistikHarianFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    // ---------------------------------------------------------------------------------------------
    // Deklarasi Kebutuhan
    private static final int DATA_LOADER = 0;
    // ---------------------------------------------------------------------------------------------
    // Deklarasi Class helper yang diperlukan
    private MethodHelper mMethodHelper = new MethodHelper();
    private DataOperation mDataOperation = new DataOperation();
    private StatistikHarianCursorRecyclerAdapter mCursorAdapter;
    // ---------------------------------------------------------------------------------------------

    public StatistikHarianFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_statistik_harian, container, false);

        // -----------------------------------------------------------------------------------------
        View empty_listView = rootView.findViewById(R.id.statistik_view_emptyview);
        RecyclerView mRecyclerView = rootView.findViewById(R.id.statistik_list_data);
        ProgressBar mProgressBar = rootView.findViewById(R.id.statistik_progress_bar);
        TextView mTextViewPercentage = rootView.findViewById(R.id.statistik_textview_bar);
        // -----------------------------------------------------------------------------------------
        // Deklarasi Element XML Update View
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getContext());
        View mDialogView = inflater.inflate(R.layout.content_statistik_update, null);
        TextView mTextViewWaktu = mDialogView.findViewById(R.id.textview_statistik_update);
        // -----------------------------------------------------------------------------------------
        DialogFormHelper mDialogForm = new DialogFormHelper(mDialogBuilder,inflater, mDialogView, mTextViewWaktu, mMethodHelper, getContext(), mDataOperation);
        // -----------------------------------------------------------------------------------------
        String percen = getProgress() + "%";
        mTextViewPercentage.setText(percen);
        mProgressBar.setProgress(getProgress());
        if (getProgress()!=0) {
            empty_listView.setVisibility(View.GONE);
        } else {
            empty_listView.setVisibility(View.VISIBLE);
        }
        // -----------------------------------------------------------------------------------------
        Cursor cursor = mDataOperation.getDataTanggal(getContext(), mMethodHelper.getDateToday());
        mCursorAdapter = new StatistikHarianCursorRecyclerAdapter(getContext(), cursor, mDialogForm);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), mLayoutManager.getOrientation());
        // -----------------------------------------------------------------------------------------
        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mCursorAdapter);

        // -----------------------------------------------------------------------------------------
        return rootView;
    }

    public int getProgress(){
        Cursor cursor = mDataOperation.getDataTanggal(getContext(), mMethodHelper.getDateToday());
        int countTable = cursor.getCount();
        return countTable * 20;
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
        return new CursorLoader(getContext(), // getContext disini menggantikan this
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

}