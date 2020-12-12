package com.example.coffeehouse;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeehouse.apdapter.PageAdapter;
import com.google.android.material.tabs.TabLayout;

public class fragment_order extends Fragment {
    private TabLayout tabLayout350;
    private ViewPager viewPager350;
    public PageAdapter pageAdapter350;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order,container,false);
        addControls(v);
        createTabFragment();
        return v;
    }

    public void addControls(View v) {
        tabLayout350 = (TabLayout) v.findViewById(R.id.tabLayout);
        viewPager350 = (ViewPager) v.findViewById(R.id.viewpager);
    }

    public void createTabFragment() {
        pageAdapter350 = new PageAdapter(getChildFragmentManager());

        pageAdapter350.addFragment(new fragment_favourite(),"Nổi bật");
        pageAdapter350.addFragment(new fragment_drinks(),"Thức uống");
        pageAdapter350.addFragment(new fragment_food(),"Đồ ăn");

        viewPager350.setAdapter(pageAdapter350);
        tabLayout350.setupWithViewPager(viewPager350);

        tabLayout350.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}