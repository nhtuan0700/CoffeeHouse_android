package com.example.coffeehouse;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeehouse.apdapter.RecyclerViewNews;
import com.example.coffeehouse.apdapter.RecyclerViewProfile;
import com.example.coffeehouse.model.OptionProfile;

import java.util.ArrayList;

public class fragment_profile extends Fragment {
    RecyclerView recyclerView;
    LinearLayout barLogin;
    ArrayList<OptionProfile> arrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        addControls(v);
        getData();
        loadListOptions();
        return v;
    }

    public void addControls(View v) {
        recyclerView = v.findViewById(R.id.recyclerview);
        barLogin = v.findViewById(R.id.bar_login);

        barLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_login.class);
                startActivity(intent);
            }
        });
    }

    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new OptionProfile("The Coffee House Rewards", R.drawable.ic_star_outline));
        arrayList.add(new OptionProfile("Thông tin tài khoản", R.drawable.ic_user));
        arrayList.add(new OptionProfile("Nhạc đang phát", R.drawable.ic_playlist));
        arrayList.add(new OptionProfile("Lịch sử", R.drawable.ic_history));
        arrayList.add(new OptionProfile("Giúp đỡ", R.drawable.ic_help));
        arrayList.add(new OptionProfile("Cài đặt", R.drawable.ic_settings));
    }

    public void loadListOptions() {
        RecyclerViewProfile myAdapter1 = new RecyclerViewProfile(getActivity(), R.layout.item_option_profile, arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(myAdapter1);
        recyclerView.setNestedScrollingEnabled(false);
    }
}