package com.freeoda.franktirkey.smartmanagementforengineers.tabFragments.Home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.freeoda.franktirkey.smartmanagementforengineers.appConfig.AppAbout;
import com.freeoda.franktirkey.smartmanagementforengineers.appConfig.AppSetting;
import com.freeoda.franktirkey.smartmanagementforengineers.appConfig.HelpAndReport;
import com.freeoda.franktirkey.smartmanagementforengineers.attendance.AttendanceMain;
import com.freeoda.franktirkey.smartmanagementforengineers.chat.chatWorkers.ChatMainWorker;
import com.freeoda.franktirkey.smartmanagementforengineers.collage.CollageMain;
import com.freeoda.franktirkey.smartmanagementforengineers.R;
import com.freeoda.franktirkey.smartmanagementforengineers.notice.Notice;
import com.freeoda.franktirkey.smartmanagementforengineers.subject.SubjectMain;
import com.freeoda.franktirkey.smartmanagementforengineers.toDos.ToDoMain;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Home extends Fragment {

    private Fragment_Home_Main_rvAdapter home_main_adapter;
    private RecyclerView rvHome;
    private FrameLayout home_framelayout_main;
    private TextView tv_rvHome;

    public Fragment_Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Context context = this.getActivity();

        View view = inflater.inflate(R.layout.fragment_fragment__home, container, false);
        View viewRv = inflater.inflate(R.layout.fragment_home_main_rv_layout, container, false);

        String[] data = {
                "Subject",
                "Group Discussion",
                "Manage Attendance",
                "Plannings",
                "Collage",
                "Important Dates",
                "Set Goals",
                "Setting",
                "Need Help Or Report?",
                "About",
                "Notice"
        };

        rvHome = view.findViewById(R.id.rvHome);
        home_framelayout_main = view.findViewById(R.id.home_framelayout_main);
        tv_rvHome = viewRv.findViewById(R.id.tv_rvHome);

        int numberOfColumns = 1;

        rvHome.setLayoutManager(new GridLayoutManager(context,numberOfColumns));
        home_main_adapter = new Fragment_Home_Main_rvAdapter(context,data);
        home_main_adapter.setClickListener(new Fragment_Home_Main_rvAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Toast.makeText(context,home_main_adapter.getItem(position),Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0: startActivity(new Intent(context, SubjectMain.class));
                        break;
                    case 1: startActivity(new Intent(context, ChatMainWorker.class));
                        break;
                    case 2: startActivity(new Intent(context, AttendanceMain.class));
                        break;
                    case 3: startActivity(new Intent(context, ToDoMain.class).putExtra("data","planning"));
                        break;
                    case 4: startActivity(new Intent(context, CollageMain.class).putExtra("data","reachCollage"));
                        break;
                    case 5: startActivity(new Intent(context,ToDoMain.class).putExtra("data","impDate"));
                        break;
                    case 6: startActivity(new Intent(context,ToDoMain.class).putExtra("data","Set Goal"));
                        break;
                    case 7: startActivity(new Intent(context, AppSetting.class));
                        break;
                    case 8: startActivity(new Intent(context, HelpAndReport.class));
                        break;
                    case 9: startActivity(new Intent(context, AppAbout.class));
                        break;
                    case 10: startActivity(new Intent(context, Notice.class));
                    break;
                }
            }
        });

        rvHome.setAdapter(home_main_adapter);

//        rvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
////            @Override
////            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
////                super.onScrollStateChanged(recyclerView, newState);
////                Log.println(Log.VERBOSE,"rvHome","working New State = "+newState); //TODO Testing Phase
////            }
////
////            @Override
////            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
////                super.onScrolled(recyclerView, dx, dy);
////                Log.println(Log.VERBOSE,"rvHome","working dx = "+dx+" dy = "+dy); //TODO Testing Phase
////            }
////        });

        // Inflate the layout for this fragment
        return view;
    }
}
