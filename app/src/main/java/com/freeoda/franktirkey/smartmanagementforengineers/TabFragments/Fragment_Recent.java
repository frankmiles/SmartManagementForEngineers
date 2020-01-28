package com.freeoda.franktirkey.smartmanagementforengineers.TabFragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;
import com.freeoda.franktirkey.smartmanagementforengineers.Chat.ChatMain;
import com.freeoda.franktirkey.smartmanagementforengineers.R;
import com.freeoda.franktirkey.smartmanagementforengineers.Subject.SubjectMain;
import com.freeoda.franktirkey.smartmanagementforengineers.Subject.subjectRoomForRecentTab.Subject;
import com.freeoda.franktirkey.smartmanagementforengineers.Syllabus.SyllabusMain;
import com.freeoda.franktirkey.smartmanagementforengineers.Subject.subjectRoomForRecentTab.getSubjectFromDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Recent extends Fragment {

    private RecyclerView rvSubject, rvSyllabus, rvLastGroupChat;
    private TextView tvSubject_recent, tvSyllabus_recent, tvLastGroupChat_recent;

    static List<Subject> DBSubjectList = new ArrayList<>();
    //getSubjectFromDB DBSubject = new getSubjectFromDB();

    String[] branch, sem;

    static Fragment_Recent_Subject_rvAdapter adapter_subject;

    public Fragment_Recent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment__recent, container, false);

        DBSubjectList = BackendlessApplication.getSubject_db().subjectDao().getAll();

        //DBSubject.execute();
        branch = getResources().getStringArray(R.array.Branch);
        sem = getResources().getStringArray(R.array.Sem);

        rvSubject = view.findViewById(R.id.rvSubject);
        rvSyllabus = view.findViewById(R.id.rvSyllabus);
        rvLastGroupChat = view.findViewById(R.id.rvchatGroup);

        tvSubject_recent = view.findViewById(R.id.tvSubject_recent);
        tvSyllabus_recent = view.findViewById(R.id.tvSyllabus_recent);
        tvLastGroupChat_recent = view.findViewById(R.id.tvChatGroup_recent);

        /*rvSubject LIST AND ADAPTER*/

        LinearLayoutManager layoutManager_Subject = new LinearLayoutManager(this.getActivity());
        layoutManager_Subject.setOrientation(LinearLayoutManager.HORIZONTAL);

        rvSubject.setLayoutManager(layoutManager_Subject);

        //DBSubjectList = getSubjectFromDB.getList();

        final List<Fragment_Recent_Subject_rvModelClass> list_Subject = new ArrayList<>();

        for(Subject s:DBSubjectList){

            String parsedBranch = branch[s.getSelectedBranch()];
            String parsedSem = sem[s.getSelectedSem()];
            list_Subject.add(new Fragment_Recent_Subject_rvModelClass(parsedSem+"\n"+parsedBranch));
            Log.d("msgDB","Enserted data on recent - Subjects");

        }

        adapter_subject = new Fragment_Recent_Subject_rvAdapter(list_Subject);
        rvSubject.setAdapter(adapter_subject);
        adapter_subject.notifyDataSetChanged();
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

        LinearLayoutManager layoutManager_Syllabus = new LinearLayoutManager(this.getActivity());
        layoutManager_Syllabus.setOrientation(LinearLayoutManager.HORIZONTAL);

        rvSyllabus.setLayoutManager(layoutManager_Syllabus);

        final List<Fragment_Recent_Syllabus_rvModelClass> list_Syllabus = new ArrayList<>();
        list_Syllabus.add(new Fragment_Recent_Syllabus_rvModelClass("cat"));
        list_Syllabus.add(new Fragment_Recent_Syllabus_rvModelClass("dog"));
        list_Syllabus.add(new Fragment_Recent_Syllabus_rvModelClass("milk"));
        list_Syllabus.add(new Fragment_Recent_Syllabus_rvModelClass("mouse"));
        list_Syllabus.add(new Fragment_Recent_Syllabus_rvModelClass("duck"));
        list_Syllabus.add(new Fragment_Recent_Syllabus_rvModelClass("sparrow"));

        Fragment_Recent_Syllabus_rvAdapter adapter_syllabus = new Fragment_Recent_Syllabus_rvAdapter(list_Syllabus);
        rvSyllabus.setAdapter(adapter_syllabus);
        adapter_syllabus.notifyDataSetChanged();
        adapter_syllabus.setClickListner(new Fragment_Recent_Syllabus_rvAdapter.ItemClickListner() {
            @Override
            public void onItemClick(View view, int position) {
                String data = list_Syllabus.get(position).getString();
                Intent intent = new Intent(getContext(), SyllabusMain.class);
                intent.putExtra("data", data);
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

        Fragment_Recent_LastChatGroup_rvAdapter adapter_LastChatGroup = new Fragment_Recent_LastChatGroup_rvAdapter(list_LastChatGroup);
        rvLastGroupChat.setAdapter(adapter_LastChatGroup);
        adapter_LastChatGroup.notifyDataSetChanged();
        adapter_LastChatGroup.setClickListener(new Fragment_Recent_LastChatGroup_rvAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String data = list_LastChatGroup.get(position).getString();
                Intent intent = new Intent(getContext(), ChatMain.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });
        /*END RVLastChatGroup LIST AND ADAPTER*/

        return view;
    }

    public static void dataSetChanged() {
        DBSubjectList = BackendlessApplication.getSubject_db().subjectDao().getAll();
        adapter_subject.notifyDataSetChanged();
    }
}