package com.freeoda.franktirkey.smartmanagementforengineers.TabFragments.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

public class Fragment_Home_Main_rvAdapter extends RecyclerView.Adapter<Fragment_Home_Main_rvAdapter.ViewHolder>{

    private String[] mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    Context context;

    private int lastPosition = -1;

    public Fragment_Home_Main_rvAdapter(Context context, String[] data) {
        this.mData = data;
        this.context = context;
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
        setAnimation(holder.myTextView,position);
    }

    private void setAnimation(View viewToAnimate, int position) {  //Added for animation
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_from_bottom); // for image
            animation.setDuration(800);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
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
