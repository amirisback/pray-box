package org.d3ifcool.jagosholat.presenters.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.jagosholat.R;
import org.d3ifcool.jagosholat.presenters.helpers.DialogFormHelper;

public class StatistikHarianCursorRecyclerAdapter extends CursorRecyclerViewAdapter {

    private DialogFormHelper mDialogForm;

    public StatistikHarianCursorRecyclerAdapter(Context context, Cursor cursor, DialogFormHelper mDialogForm) {
        super(context, cursor);
        this.mDialogForm = mDialogForm;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.content_statistik_harian, parent, false);
        return new StatistikHarianViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        StatistikHarianViewHolder holder = (StatistikHarianViewHolder)viewHolder;
        cursor.moveToPosition(cursor.getPosition());
        holder.setData(cursor, mDialogForm);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
