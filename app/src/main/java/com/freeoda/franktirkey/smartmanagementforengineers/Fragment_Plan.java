package com.freeoda.franktirkey.smartmanagementforengineers;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Plan extends Fragment {

    private RecyclerView rvPlan;
    private ArrayList<Fragment_Plan_Main_rvModelClass> list = new ArrayList<>();
    private Fragment_Plan_main_rvAdapter mainAdapter;
    private Fragment_Plan_Main_rvModelClass model;
    private Paint p = new Paint();

    View view;
    Context context;

    public Fragment_Plan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__plan, container, false);
        context = this.getActivity();
        rvPlan = view.findViewById(R.id.rvPlan);

        list.add(new Fragment_Plan_Main_rvModelClass("Make App"));
        list.add(new Fragment_Plan_Main_rvModelClass("Comb hair"));
        list.add(new Fragment_Plan_Main_rvModelClass("clean laptop"));
        list.add(new Fragment_Plan_Main_rvModelClass("check insagram"));
        list.add(new Fragment_Plan_Main_rvModelClass("make friends"));
        list.add(new Fragment_Plan_Main_rvModelClass("feel sad"));
        list.add(new Fragment_Plan_Main_rvModelClass("eat"));
        list.add(new Fragment_Plan_Main_rvModelClass("reapeat"));
        list.add(new Fragment_Plan_Main_rvModelClass("take bath"));
        list.add(new Fragment_Plan_Main_rvModelClass("dance naked"));
        list.add(new Fragment_Plan_Main_rvModelClass("again dance "));
        list.add(new Fragment_Plan_Main_rvModelClass("seriously you'r watching this"));

        mainAdapter = new Fragment_Plan_main_rvAdapter(context,list);
        rvPlan.setAdapter(mainAdapter);
        rvPlan.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        enableSwipe(view);

        return view;
    }


    private void enableSwipe(final View view) {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.LEFT) {
                    final Fragment_Plan_Main_rvModelClass deletedModel = list.get(position);
                    final int deletedPosition = position;
                    mainAdapter.removeItem(position);
                    Snackbar snackbar = Snackbar.make(view, " removed from Recyclerview!", Snackbar.LENGTH_LONG);
                       snackbar.setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                              // undo is selected, restore the deleted item
                                mainAdapter.restoreItem(deletedModel, deletedPosition);
                           }
                       });
                }
                else{
                    final Fragment_Plan_Main_rvModelClass deletedModel = list.get(position);
                    final int deletedPosition = position;
                    mainAdapter.removeItem(position);
                    Snackbar snackbar = Snackbar.make(view, " removed from Recyclerview!", Snackbar.LENGTH_LONG);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // undo is selected, restore the deleted item
                    mainAdapter.restoreItem(deletedModel, deletedPosition);
                        }
                    });
                }
            }



            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;
                    if(dX > 0){
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.delete);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.delete);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvPlan);
    }
}