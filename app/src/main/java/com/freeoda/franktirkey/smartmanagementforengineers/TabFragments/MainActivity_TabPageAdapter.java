package com.freeoda.franktirkey.smartmanagementforengineers.TabFragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.freeoda.franktirkey.smartmanagementforengineers.TabFragments.Home.Fragment_Home;
import com.freeoda.franktirkey.smartmanagementforengineers.TabFragments.Plan.Fragment_Plan;
import com.freeoda.franktirkey.smartmanagementforengineers.TabFragments.Recent.Fragment_Recent;

public class MainActivity_TabPageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public MainActivity_TabPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numOfTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new Fragment_Recent();
            case 1:
                return new Fragment_Home();
            case 2:
                return new Fragment_Plan();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
