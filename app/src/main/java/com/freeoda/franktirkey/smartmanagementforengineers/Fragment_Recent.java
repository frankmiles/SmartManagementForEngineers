package com.freeoda.franktirkey.smartmanagementforengineers;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.io.BackendlessUserFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Recent extends Fragment {

    RecyclerView rvSubject,rvSyllabus,rvLastGroupChat;
    TextView tvSubject_recent,tvSyllabus_recent,tvLastGroupChat_recent;

    public Fragment_Recent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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

        LinearLayoutManager layoutManager_Subject = new LinearLayoutManager(this.getActivity());
        layoutManager_Subject.setOrientation(LinearLayoutManager.HORIZONTAL);

        rvSubject.setLayoutManager(layoutManager_Subject);

        List<Fragment_Recent_Subject_rvModelClass> list_Subject = new ArrayList<>();
        list_Subject.add(new Fragment_Recent_Subject_rvModelClass("tom"));
        list_Subject.add(new Fragment_Recent_Subject_rvModelClass("tom"));
        list_Subject.add(new Fragment_Recent_Subject_rvModelClass("tom"));
        list_Subject.add(new Fragment_Recent_Subject_rvModelClass("tom"));
        list_Subject.add(new Fragment_Recent_Subject_rvModelClass("tom"));

        Fragment_Recent_Subject_rvAdapter adapter_subject = new Fragment_Recent_Subject_rvAdapter(list_Subject);
        rvSubject.setAdapter(adapter_subject);
        adapter_subject.notifyDataSetChanged();
        /*END RV LIST AND ADAPTER*/

        /*RVSyllabus LIST AND ADAPTER*/

        LinearLayoutManager layoutManager_Syllabus = new LinearLayoutManager(this.getActivity());
        layoutManager_Syllabus.setOrientation(LinearLayoutManager.HORIZONTAL);

        rvSyllabus.setLayoutManager(layoutManager_Syllabus);

        List<Fragment_Recent_Syllabus_rvModelClass> list_Syllabus = new ArrayList<>();
        list_Syllabus.add(new Fragment_Recent_Syllabus_rvModelClass("cat"));
        list_Syllabus.add(new Fragment_Recent_Syllabus_rvModelClass("cat"));
        list_Syllabus.add(new Fragment_Recent_Syllabus_rvModelClass("cat"));
        list_Syllabus.add(new Fragment_Recent_Syllabus_rvModelClass("cat"));
        list_Syllabus.add(new Fragment_Recent_Syllabus_rvModelClass("cat"));
        list_Syllabus.add(new Fragment_Recent_Syllabus_rvModelClass("cat"));

        Fragment_Recent_Syllabus_rvAdapter adapter_syllabus = new Fragment_Recent_Syllabus_rvAdapter(list_Syllabus);
        rvSyllabus.setAdapter(adapter_syllabus);
        adapter_syllabus.notifyDataSetChanged();
        /*END RVSyllabus LIST AND ADAPTER*/

        /*RVLastChatGroup LIST AND ADAPTER*/

        LinearLayoutManager layoutManager_LastChatFroup = new LinearLayoutManager(this.getActivity());
        layoutManager_LastChatFroup.setOrientation(LinearLayoutManager.HORIZONTAL);

        rvLastGroupChat.setLayoutManager(layoutManager_LastChatFroup);

        List<Fragment_Recent_LastChatGroup_rvModelClass> list_LastChatGroup = new ArrayList<>();
        list_LastChatGroup.add(new Fragment_Recent_LastChatGroup_rvModelClass("lol"));
        list_LastChatGroup.add(new Fragment_Recent_LastChatGroup_rvModelClass("lol"));
        list_LastChatGroup.add(new Fragment_Recent_LastChatGroup_rvModelClass("lol"));
        list_LastChatGroup.add(new Fragment_Recent_LastChatGroup_rvModelClass("lol"));
        list_LastChatGroup.add(new Fragment_Recent_LastChatGroup_rvModelClass("lol"));
        list_LastChatGroup.add(new Fragment_Recent_LastChatGroup_rvModelClass("lol"));

        Fragment_Recent_LastChatGroup_rvAdapter adapter_LastChatGroup = new Fragment_Recent_LastChatGroup_rvAdapter(list_LastChatGroup);
        rvLastGroupChat.setAdapter(adapter_LastChatGroup);
        adapter_LastChatGroup.notifyDataSetChanged();
        /*END RVLastChatGroup LIST AND ADAPTER*/


        return view;
    }

}
