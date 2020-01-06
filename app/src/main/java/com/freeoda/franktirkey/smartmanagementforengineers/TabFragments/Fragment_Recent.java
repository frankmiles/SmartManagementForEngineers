package com.freeoda.franktirkey.smartmanagementforengineers.TabFragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freeoda.franktirkey.smartmanagementforengineers.Chat.ChatMain;
import com.freeoda.franktirkey.smartmanagementforengineers.R;
import com.freeoda.franktirkey.smartmanagementforengineers.Subject.SubjectMain;
import com.freeoda.franktirkey.smartmanagementforengineers.Subject.SyllabusMain;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Recent extends Fragment {

    private RecyclerView rvSubject,rvSyllabus,rvLastGroupChat;
    private TextView tvSubject_recent,tvSyllabus_recent,tvLastGroupChat_recent;

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

        LinearLayoutManager layoutManager_Subject = new LinearLayoutManager(this.getActivity());
        layoutManager_Subject.setOrientation(LinearLayoutManager.HORIZONTAL);

        rvSubject.setLayoutManager(layoutManager_Subject);

        final List<Fragment_Recent_Subject_rvModelClass> list_Subject = new ArrayList<>();
        list_Subject.add(new Fragment_Recent_Subject_rvModelClass("tom"));
        list_Subject.add(new Fragment_Recent_Subject_rvModelClass("cat"));
        list_Subject.add(new Fragment_Recent_Subject_rvModelClass("dog"));
        list_Subject.add(new Fragment_Recent_Subject_rvModelClass("meow"));
        list_Subject.add(new Fragment_Recent_Subject_rvModelClass("bark"));

        Fragment_Recent_Subject_rvAdapter adapter_subject = new Fragment_Recent_Subject_rvAdapter(list_Subject);
        rvSubject.setAdapter(adapter_subject);
        adapter_subject.notifyDataSetChanged();
        adapter_subject.setClickListener(new Fragment_Recent_Subject_rvAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String data = list_Subject.get(position).getText();
                Intent intent = new Intent(getContext(), SubjectMain.class);
                intent.putExtra("data",data);
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
                intent.putExtra("data",data);
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
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });
        /*END RVLastChatGroup LIST AND ADAPTER*/

        return view;
    }

}
