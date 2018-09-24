package org.d3ifcool.jagosholat.Controller.StatistikFragmentContent;

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
import android.widget.TextView;

import org.d3ifcool.jagosholat.Controller.Helper.DialogFormHelper;
import org.d3ifcool.jagosholat.Controller.StatistikFragmentContent.StatistikObject.StatistikWord;
import org.d3ifcool.jagosholat.Controller.Helper.MethodHelper;
import org.d3ifcool.jagosholat.Model.DataContract;
import org.d3ifcool.jagosholat.Model.DataOperation;
import org.d3ifcool.jagosholat.R;
import org.d3ifcool.jagosholat.Controller.StatistikFragmentContent.StatistikAdapter.StatistikSemuaCursorAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatistikSemuaFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    // ---------------------------------------------------------------------------------------------
    private static final int DATA_LOADER = 0;
    private ListView mListView;
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
    // Deklarasi Class Helper yang diperlukan
    private MethodHelper mMethodHelper = new MethodHelper();
    private DataOperation mDataOperation = new DataOperation();
    private DialogFormHelper mDialogForm;
    private StatistikSemuaCursorAdapter mCursorAdapter;

    // ---------------------------------------------------------------------------------------------

    public StatistikSemuaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_statistik_semua, container, false);

        // -----------------------------------------------------------------------------------------
        View empty_listView = rootView.findViewById(R.id.empty_views);
        mListView = rootView.findViewById(R.id.recyclerViewStatistik);
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
        mDialogForm = new DialogFormHelper(mDialogBuilder,inflater, mDialogView, mTextViewWaktu, mMethodHelper,
                getActivity(), mDataOperation);
        mCursorAdapter = new StatistikSemuaCursorAdapter(getActivity(), null);
        mListView.setAdapter(mCursorAdapter);
        getDataFromTable();
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ---------------------------------------------------------------------------------
                String getArrayId = arrayWord.get(position).getId();
                String getArrayWaktu = arrayWord.get(position).getWaktu();
                mDialogForm.DialogForm(getArrayId, getArrayWaktu);
                // ---------------------------------------------------------------------------------
            }
        });
        // -----------------------------------------------------------------------------------------

        return rootView;
    }

    // ---------------------------------------------------------------------------------------------
    public void getDataFromTable() {
        Cursor cursor = mDataOperation.getSemuaData(getContext());
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

    // ---------------------------------------------------------------------------------------------
    // Jadi Ketika Menggunakan CursorLoader Fragment harus mengoverride OnActivityCreated
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(DATA_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }
    // ---------------------------------------------------------------------------------------------

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), // getActivity disini menggantikan this
                DataContract.DataEntry.CONTENT_URI,
                mDataOperation.getProjection(),
                null,
                null,
                null);
    }
}