package org.d3ifcool.jagosholat.views.adapters.recyclerview;

import android.content.Context;
import android.database.Cursor;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.jagosholat.R;
import org.d3ifcool.jagosholat.views.dialogs.StatistikCustomDialog;
import org.d3ifcool.jagosholat.views.interfaces.ClickHandlerActionMode;

import java.util.ArrayList;

public class StatistikHarianCursorRecyclerViewAdapter extends CursorRecyclerViewAdapter {

    private StatistikCustomDialog mDialogForm;
    private ClickHandlerActionMode mClickHandler;
    private ArrayList<Integer> mSelectedId;
    private ArrayList<String> mSelectedDataId;
    private ArrayList<String> mSelectedDataWaktu;
    private StatistikHarianViewHolder holder;


    public StatistikHarianCursorRecyclerViewAdapter(Context context, Cursor cursor, ClickHandlerActionMode handler) {
        super(context, cursor);
        this.mClickHandler = handler;
        this.mSelectedId = new ArrayList<>();
        this.mSelectedDataId = new ArrayList<>();
        this.mSelectedDataWaktu = new ArrayList<>();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.content_statistik_harian, parent, false);
        return new StatistikHarianViewHolder(v, mClickHandler);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        holder = (StatistikHarianViewHolder) viewHolder;
        cursor.moveToPosition(cursor.getPosition());
        holder.setData(cursor);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    // ---------------------------------------------------------------------------------------------
    public void toggleSelection(int dataId){
        if (mSelectedId.contains(dataId)){
            mSelectedId.remove(Integer.valueOf(dataId));
        } else {
            mSelectedId.add(dataId);
            mSelectedDataId.add(holder.getId());
            mSelectedDataWaktu.add(holder.getWaktu());
        }
        notifyDataSetChanged();
    }
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    public int selectionCount(){
        return mSelectedId.size();
    }
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------'
    public void resetSelection(){
        mSelectedId = new ArrayList<>();
        mSelectedDataId = new ArrayList<>();
        notifyDataSetChanged();
    }
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    public ArrayList<Integer> getmSelectedId() {
        return mSelectedId;
    }

    public ArrayList<String> getmSelectedDataId() {
        return mSelectedDataId;
    }

    public ArrayList<String> getmSelectedDataWaktu() {
        return mSelectedDataWaktu;
    }

    // ---------------------------------------------------------------------------------------------



}
