package com.freeoda.franktirkey.smartmanagementforengineers.Syllabus;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.util.StringUtil;

import com.backendless.utils.StringUtils;
import com.freeoda.franktirkey.smartmanagementforengineers.R;
import com.freeoda.franktirkey.smartmanagementforengineers.Syllabus.youtube.YoutubeMainAPI_config;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

public class SyllabusMainAdaper extends RecyclerView.Adapter<SyllabusMainAdaper.cViewHolder>{

    List<SyllabusMainModel> list;
    interface_RecyclerViewClickListner_Syllabus irvcl;

    public SyllabusMainAdaper(List<SyllabusMainModel> list, interface_RecyclerViewClickListner_Syllabus irvcl) {
        this.list = list;
        this.irvcl = irvcl;
    }

    @NonNull
    @Override
    public cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.syllabus_main_rv_item_layout,parent,false);
        return new cViewHolder(view,irvcl);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewHolder holder, int position) {

        holder.setData(list.get(position).getTopic(),list.get(position).getDetail(),list.get(position).getYoutube());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class cViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_rv_item_syllabus_title,tv_rv_syllabus_body;
        YouTubePlayerView vv_syllabus_yoututbePlayer;
        interface_RecyclerViewClickListner_Syllabus ocl;

        YouTubePlayer.OnInitializedListener onInitializedListener;
        String youtubeUrl;

        public cViewHolder(@NonNull View itemView,interface_RecyclerViewClickListner_Syllabus ocl) {
            super(itemView);
            tv_rv_item_syllabus_title = itemView.findViewById(R.id.tv_rv_item_syllabus_title);
            tv_rv_syllabus_body = itemView.findViewById(R.id.tv_rv_syllabus_body);
            vv_syllabus_yoututbePlayer = itemView.findViewById(R.id.vv_syllabus_yoututbePlayer);
            itemView.setOnClickListener(this);
            this.ocl = ocl;

            vv_syllabus_yoututbePlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vv_syllabus_yoututbePlayer.initialize(YoutubeMainAPI_config.getYoutubeApi(),onInitializedListener);
                }
            });

            onInitializedListener = new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                    String validUrl = youtubeUrl.replace("\n", "").replace("\r", "");
                    validUrl = validUrl.trim();
                    Log.d("msg",validUrl);
                    youTubePlayer.loadVideo(validUrl);

                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    Log.d("msgErr","failed to load YT Video");
                }
            };

        }

        public void setData(String title, String body, final String youtubeUrl){
            tv_rv_item_syllabus_title.setText(title);
            tv_rv_syllabus_body.setText(body);
            this.youtubeUrl = youtubeUrl;
        }

        @Override
        public void onClick(View view) {
            ocl.onClick(view,getAdapterPosition());
        }
    }
}
