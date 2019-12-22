package com.freeoda.franktirkey.smartmanagementforengineers;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment_Home_Main_rvAdapter extends RecyclerView.Adapter<Fragment_Home_Main_rvAdapter.ViewHolder>{

    private String[] mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public Fragment_Home_Main_rvAdapter(Context context, String[] data) {
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_home_main_rv_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.myTextView.setText(mData[position]);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData[id];
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tv_rvHome);

//            myTextView.setSelected(true);
//            myTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
//            myTextView.setSingleLine(true);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
