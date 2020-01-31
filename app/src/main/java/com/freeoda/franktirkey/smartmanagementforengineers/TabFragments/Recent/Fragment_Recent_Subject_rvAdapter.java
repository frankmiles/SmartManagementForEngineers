package com.freeoda.franktirkey.smartmanagementforengineers.TabFragments.Recent;

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

import java.util.List;

public class Fragment_Recent_Subject_rvAdapter extends RecyclerView.Adapter<Fragment_Recent_Subject_rvAdapter.cViewHolder> {

    private Context context;
    private List<Fragment_Recent_Subject_rvModelClass> list;
    private ItemClickListener mClickListener;

    int lastPosition = -1;

    public Fragment_Recent_Subject_rvAdapter(Context context, List<Fragment_Recent_Subject_rvModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recent_subject_rv_layout,parent,false);


        return new cViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewHolder holder, int position) {

        String string = list.get(position).getText();

        holder.setData(string);
        setAnimation(holder.vhTextView,position);

    }

    private void setAnimation(View viewToAnimate, int position) {  //Added for animation
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.silde_from_left); // for image
            animation.setDuration(800);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    void setClickListener(ItemClickListener itemClickListener){
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }

    class cViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView vhTextView;

        public cViewHolder(@NonNull View itemView) {
            super(itemView);
            vhTextView = itemView.findViewById(R.id.tvSubject);
            itemView.setOnClickListener(this);
        }

        private void setData(String string){
            vhTextView.setText(string);
        }

        @Override
        public void onClick(View view) {
            if(mClickListener != null) mClickListener.onItemClick(view,getAdapterPosition());
        }
    }
}
