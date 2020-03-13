package com.freeoda.franktirkey.smartmanagementforengineers.tabFragments.Recent;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;
import com.freeoda.franktirkey.smartmanagementforengineers.chat.chatWorkers.ChatMainWorker;
import com.freeoda.franktirkey.smartmanagementforengineers.R;
import com.freeoda.franktirkey.smartmanagementforengineers.subject.SubjectMain;
import com.freeoda.franktirkey.smartmanagementforengineers.subject.subjectRoomForRecentTab.Subject;
import com.freeoda.franktirkey.smartmanagementforengineers.syllabus.SyllabusMain;
import com.freeoda.franktirkey.smartmanagementforengineers.syllabus.syllabusRoomForRecentTab.Syllabus;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Recent extends Fragment {

    private RecyclerView rvSubject, rvSyllabus, rvLastGroupChat;
    private TextView tvSubject_recent, tvSyllabus_recent, tvLastGroupChat_recent;

    public static List<Subject> DBSubjectList = new ArrayList<>();
    public static List<Syllabus> DBSyllabusList = new ArrayList<>();
    //getSubjectFromDB DBSubject = new getSubjectFromDB();

    String[] branch, sem;

    Fragment_Recent_Subject_rvAdapter adapter_subject;
    Fragment_Recent_Syllabus_rvAdapter adapter_syllabus;

    final List<Fragment_Recent_Subject_rvModelClass> list_Subject = new ArrayList<>();
    final List<Fragment_Recent_Syllabus_rvModelClass> list_Syllabus = new ArrayList<>();

    public Fragment_Recent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment__recent, container, false);

        rvSubject = view.findViewById(R.id.rvSubject);
        rvSyllabus = view.findViewById(R.id.rvSyllabus);
        rvLastGroupChat = view.findViewById(R.id.rvchatGroup);

        tvSubject_recent = view.findViewById(R.id.tvSubject_recent);
        tvSyllabus_recent = view.findViewById(R.id.tvSyllabus_recent);
        tvLastGroupChat_recent = view.findViewById(R.id.tvChatGroup_recent);

        /*rvSubject LIST AND ADAPTER*/

        list_Subject.clear();
        LinearLayoutManager layoutManager_Subject = new LinearLayoutManager(this.getActivity());
        layoutManager_Subject.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvSubject.setLayoutManager(layoutManager_Subject);
        recentSubjectFetch();
        adapter_subject = new Fragment_Recent_Subject_rvAdapter(getContext(),list_Subject);
        rvSubject.setAdapter(adapter_subject);

        adapter_subject.setClickListener(new Fragment_Recent_Subject_rvAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String data = list_Subject.get(position).getText();
                Intent intent = new Intent(getContext(), SubjectMain.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });
        /*END RV LIST AND ADAPTER*/

        /*RVSyllabus LIST AND ADAPTER*/

        list_Syllabus.clear();
        LinearLayoutManager layoutManager_Syllabus = new LinearLayoutManager(this.getActivity());
        layoutManager_Syllabus.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvSyllabus.setLayoutManager(layoutManager_Syllabus);
        recentSyllabusFetch();
        adapter_syllabus = new Fragment_Recent_Syllabus_rvAdapter(getContext(),list_Syllabus);
        rvSyllabus.setAdapter(adapter_syllabus);

        adapter_syllabus.setClickListner(new Fragment_Recent_Syllabus_rvAdapter.ItemClickListner() {
            @Override
            public void onItemClick(View view, int position) {
                String url = list_Syllabus.get(position).getUrl();
                String name = list_Syllabus.get(position).getString();
                Intent intent = new Intent(getContext(), SyllabusMain.class);
                intent.putExtra("url", url);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
        /*END RVSyllabus LIST AND ADAPTER*/

        /*RVLastChatGroup LIST AND ADAPTER*/

        LinearLayoutManager layoutManager_LastChatFroup = new LinearLayoutManager(this.getActivity());
        layoutManager_LastChatFroup.setOrientation(LinearLayoutManager.HORIZONTAL);

        rvLastGroupChat.setLayoutManager(layoutManager_LastChatFroup);

        final List<Fragment_Recent_LastChatGroup_rvModelClass> list_LastChatGroup = new ArrayList<>();
        list_LastChatGroup.add(new Fragment_Recent_LastChatGroup_rvModelClass("muni"));
        list_LastChatGroup.add(new Fragment_Recent_LastChatGroup_rvModelClass("shila"));
        list_LastChatGroup.add(new Fragment_Recent_LastChatGroup_rvModelClass("nitish"));
        list_LastChatGroup.add(new Fragment_Recent_LastChatGroup_rvModelClass("ujjwal"));
        list_LastChatGroup.add(new Fragment_Recent_LastChatGroup_rvModelClass("suraj"));
        list_LastChatGroup.add(new Fragment_Recent_LastChatGroup_rvModelClass("pandit"));

        Fragment_Recent_LastChatGroup_rvAdapter adapter_LastChatGroup =
                new Fragment_Recent_LastChatGroup_rvAdapter(getContext(),list_LastChatGroup);
        rvLastGroupChat.setAdapter(adapter_LastChatGroup);
        adapter_LastChatGroup.notifyDataSetChanged();
        adapter_LastChatGroup.setClickListener(new Fragment_Recent_LastChatGroup_rvAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String data = DBSyllabusList.get(position).getUrl();
                Intent intent = new Intent(getContext(), ChatMainWorker.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });
        /*END RVLastChatGroup LIST AND ADAPTER*/


        return view;
    }

    private void recentSubjectFetch(){

//        LinearLayoutManager layoutManager_Subject = new LinearLayoutManager(this.getActivity());
//        layoutManager_Subject.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        rvSubject.setLayoutManager(layoutManager_Subject);

        DBSubjectList = BackendlessApplication.getSubject_db().subjectDao().getAll();
        Log.d("msg","Fetched Subject From DB");
        branch = getResources().getStringArray(R.array.Branch);
        sem = getResources().getStringArray(R.array.Sem);
        list_Subject.clear();
        for(Subject s:DBSubjectList){

            String parsedBranch = branch[s.getSelectedBranch()];
            String parsedSem = sem[s.getSelectedSem()];
            list_Subject.add(new Fragment_Recent_Subject_rvModelClass(parsedSem+"\n"+parsedBranch));
            Log.d("msgDB","Inserted data on recent - Subjects");

        }

//        adapter_subject = new Fragment_Recent_Subject_rvAdapter(list_Subject);
//        rvSubject.setAdapter(adapter_subject);
//        adapter_subject.notifyDataSetChanged();
    }

    private void recentSyllabusFetch(){

//        LinearLayoutManager layoutManager_Syllabus = new LinearLayoutManager(this.getActivity());
//        layoutManager_Syllabus.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        rvSyllabus.setLayoutManager(layoutManager_Syllabus);

        DBSyllabusList = BackendlessApplication.getSyllabus_db().syllabusDao().getAll();
        Log.d("msg","Fetched Syllabus From DB");

        list_Syllabus.clear();
        for(Syllabus s:DBSyllabusList){

            String parsedName = s.getName();
            String parsedUrl = s.getUrl();
            list_Syllabus.add(new Fragment_Recent_Syllabus_rvModelClass(parsedName,parsedUrl));
            Log.d("msgDB","Inserted data on recent - Syllabus");

        }

//        adapter_syllabus = new Fragment_Recent_Syllabus_rvAdapter(list_Syllabus);
//        rvSyllabus.setAdapter(adapter_syllabus);
//        adapter_syllabus.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();

        final Handler handler = new Handler(); //To prevent Conflicts b/w dataBase I/O
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list_Subject.clear();
                list_Syllabus.clear();

                recentSubjectFetch();
                recentSyllabusFetch();

                adapter_subject.notifyDataSetChanged();
                adapter_syllabus.notifyDataSetChanged();
            }
        },5000);
    }
}