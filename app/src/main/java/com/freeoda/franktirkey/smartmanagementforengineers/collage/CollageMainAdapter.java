package com.freeoda.franktirkey.smartmanagementforengineers.collage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.ArrayList;
import java.util.List;

public class CollageMainAdapter extends RecyclerView.Adapter<CollageMainAdapter.cViewHolder>{

    List<String> list;
    Context context;

    public CollageMainAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collage_rv_main_model,parent,false);

        return new cViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class cViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        LinearLayout ll_main_rv_collage;
        RecyclerView rv_main_domain;

        public cViewHolder(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tv_collage_domain);
            ll_main_rv_collage = itemView.findViewById(R.id.ll_main_rv_collage);
            rv_main_domain = itemView.findViewById(R.id.rv_main_domain_contact);
        }

        private void setData(String data){
            tv.setText(data);

            LinearLayoutManager layoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
            rv_main_domain.setLayoutManager(layoutManager);
            List<String> datalist = new ArrayList<>();
            CollageMainContactsAdapter adapter;
            switch(data){
                case "Administration":
                    ll_main_rv_collage.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                    rv_main_domain.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                    datalist.clear();
                    datalist.add("Principal");
                    datalist.add("Vice-Principal");
                    datalist.add("Dean");
                    datalist.add("Head Of Department");
                    datalist.add("Proctor");
                    adapter = new CollageMainContactsAdapter(datalist,context,"Administration");
                    rv_main_domain.setAdapter(adapter);
                    break;

                case "Faculty":
                    ll_main_rv_collage.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                    rv_main_domain.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                    datalist.clear();
                    datalist.add("Chandan Patra");
                    datalist.add("Manoj Kumar");
                    datalist.add("Sankarshan Sahu");
                    datalist.add("S.C. Manapatra");
                    datalist.add("Nitisha Kumari");
                    datalist.add("Drake Valientine");
                    datalist.add("Pankaj Kumar Panda");
                    adapter = new CollageMainContactsAdapter(datalist,context,"Faculty");
                    rv_main_domain.setAdapter(adapter);
                    break;

                case "Accommodation":
                    ll_main_rv_collage.setBackgroundColor(context.getResources().getColor(R.color.colorCardPrimary));
                    rv_main_domain.setBackgroundColor(context.getResources().getColor(R.color.colorCardPrimary));
                    datalist.clear();
                    datalist.add("Hostel Incharge");
                    datalist.add("Hostel Warden");
                    datalist.add("Hostel Caretaker");
                    datalist.add("Hostel Security");

                    adapter = new CollageMainContactsAdapter(datalist,context,"Accommodation");
                    rv_main_domain.setAdapter(adapter);
                    break;

                    default:
                        Log.d("msgError","Data Leaked on CollageMainAdapter");
                        break;
            }
        }
    }
}
