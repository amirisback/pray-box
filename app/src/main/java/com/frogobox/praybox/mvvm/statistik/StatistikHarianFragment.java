package com.frogobox.praybox.mvvm.statistik;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.frogobox.praybox.R;
import com.frogobox.praybox.core.BaseFragment;
import com.frogobox.praybox.source.local.DataContract;
import com.frogobox.praybox.source.local.DataOperation;
import com.frogobox.praybox.util.helper.MethodHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class StatistikHarianFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    // ---------------------------------------------------------------------------------------------
    // Deklarasi Kebutuhan
    private static final int DATA_LOADER = 0;
    // ---------------------------------------------------------------------------------------------
    // Deklarasi Class helper yang diperlukan
    private MethodHelper mMethodHelper = new MethodHelper();
    private DataOperation mDataOperation = new DataOperation();
    private StatistikViewAdapter mCursorAdapter;
    private ActionMode mActionMode;
    // ---------------------------------------------------------------------------------------------
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.menu_action, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
            actionMode.setTitle(String.valueOf(mCursorAdapter.selectionCount()));
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_delete:
                    deleteData();
                    return true;
            }
            return false;

        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            mActionMode = null;
            mCursorAdapter.resetSelection();
        }
    };

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
        // -----------------------------------------------------------------------------------------
        final StatistikDialog mDialogForm = new StatistikDialog(mDialogBuilder, mDialogView, mMethodHelper, getContext(), mDataOperation);
        // -----------------------------------------------------------------------------------------
        String percen = getProgress() + "%";
        mTextViewPercentage.setText(percen);
        mProgressBar.setProgress(getProgress());
        if (getProgress() != 0) {
            empty_listView.setVisibility(View.GONE);
        } else {
            empty_listView.setVisibility(View.VISIBLE);
        }
        // -----------------------------------------------------------------------------------------
        Cursor cursor = mDataOperation.getDataToday(getContext(), mMethodHelper.getDateToday());
        mCursorAdapter = new StatistikViewAdapter(getContext(), cursor);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mCursorAdapter);
        // -----------------------------------------------------------------------------------------
        return rootView;
    }
    // ---------------------------------------------------------------------------------------------

    public int getProgress() {
        Cursor cursor = mDataOperation.getDataToday(getContext(), mMethodHelper.getDateToday());
        int countTable = cursor.getCount();
        return countTable * 20;
    }

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

    private void deleteData() {
        // -----------------------------------------------------------------------------------------
        String messages;
        final ArrayList<Integer> selectedId = mCursorAdapter.getmSelectedId();
        final ArrayList<String> selectedDataId = mCursorAdapter.getmSelectedDataId();
        if (selectedId.size() == 1) {
            messages = getString(R.string.delete_data);
        } else {
            messages = getString(R.string.deletes_data);
            Collections.sort(selectedId, new Comparator<Integer>() {
                @Override
                public int compare(Integer integer, Integer t1) {
                    return t1.compareTo(integer);
                }
            });
        }
        // ---------------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getContext());
        builder.setTitle(R.string.delete_data).setMessage(messages).setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                for (int currentPedId = 0; currentPedId < selectedId.size(); currentPedId++) {
                    String databaseID = selectedDataId.get(currentPedId);
                    boolean delete = mDataOperation.deleteDataId(getContext(), databaseID);
                }
                mCursorAdapter.notifyDataSetChanged();
                mActionMode.finish();
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                mActionMode.finish();
            }
        });
        builder.create().show();
        // -----------------------------------------------------------------------------------------


    }
}