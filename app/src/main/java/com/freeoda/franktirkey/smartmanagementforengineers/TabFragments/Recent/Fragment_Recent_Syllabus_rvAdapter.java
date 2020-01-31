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

public class Fragment_Recent_Syllabus_rvAdapter extends RecyclerView.Adapter<Fragment_Recent_Syllabus_rvAdapter.cViewHolder>{

    Context context;
    List<Fragment_Recent_Syllabus_rvModelClass> list;
    private ItemClickListner mClickListener;

    int lastPosition = -1;

    public Fragment_Recent_Syllabus_rvAdapter(Context context, List<Fragment_Recent_Syllabus_rvModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recent_syllabus_rv_layout,parent,false);

        return new cViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewHolder holder, int position) {

        String string = list.get(position).getString();

        holder.setData(string);
        setAnimation(holder.textView,position);
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

    void setClickListner(ItemClickListner itemClickListener){
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListner{
        void onItemClick(View view,int position);
    }

    class cViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;

        public cViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvSyllabus);

            itemView.setOnClickListener(this);
        }

        private void setData(String string){
            textView.setText(string);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
