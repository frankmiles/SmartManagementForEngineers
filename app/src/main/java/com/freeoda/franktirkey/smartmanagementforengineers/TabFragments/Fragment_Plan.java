package com.freeoda.franktirkey.smartmanagementforengineers.TabFragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.opengl.Visibility;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.freeoda.franktirkey.smartmanagementforengineers.MainActivity;
import com.freeoda.franktirkey.smartmanagementforengineers.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static androidx.core.content.ContextCompat.getSystemService;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Plan extends Fragment {

    private RecyclerView rvPlan;
    static ArrayList<Fragment_Plan_Main_rvModelClass> list = new ArrayList<>();
    static Fragment_Plan_main_rvAdapter mainAdapter;
    private Fragment_Plan_Main_rvModelClass model;
    private Paint p = new Paint();
    private FloatingActionButton fb_fragment_plan_add;

    private LinearLayout ll_addMenu;
    FragmentManager fragment_addMeu_Manager;

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
        fb_fragment_plan_add = view.findViewById(R.id.fb_fragment_plan_add);

        ll_addMenu = view.findViewById(R.id.ll_addMenu);
        list.add(new Fragment_Plan_Main_rvModelClass("Make App"));

        mainAdapter = new Fragment_Plan_main_rvAdapter(context,list);
        rvPlan.setAdapter(mainAdapter);
        rvPlan.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        enableSwipe(view);

        fb_fragment_plan_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ll_addMenu.getVisibility() == View.GONE){
                    rvPlan.setClickable(false);
                    ll_addMenu.setVisibility(View.VISIBLE);
                    fb_fragment_plan_add.setImageDrawable(getResources().getDrawable(R.drawable.back));

                }else {
                    rvPlan.setClickable(true);
                    InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    try{
                        if (inputManager != null) {
                            inputManager.hideSoftInputFromWindow(Objects.requireNonNull(Objects.requireNonNull(getActivity())
                                            .getCurrentFocus()).getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                        }

                    }catch (Exception e){
                        Log.println(Log.VERBOSE,"error Code:155", Arrays.toString(e.getStackTrace()));
                    }
                    ll_addMenu.setVisibility(View.GONE);
                    fb_fragment_plan_add.setImageDrawable(getResources().getDrawable(R.drawable.add_icon));
                }
            }
        });

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