package com.example.coffeehouse;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.coffeehouse.apdapter.RecyclerListDrink;
import com.example.coffeehouse.model.Drink;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class fragment_common extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<Drink> arrayList;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_common,container,false);
        addControls(v);
        loadData();
        loadListDrink();
        return v;
    }

    public void addControls(View v) {
        recyclerView = v.findViewById(R.id.recyclerview);
    }

    public void loadData() {
        arrayList = new ArrayList<>();
        arrayList.add(new Drink(0,"Sôcôla Lúa Mạch nóng",20000,R.drawable.drink1));
        arrayList.add(new Drink(0,"Sôcôla Lúa Mạch nóng",20000,R.drawable.drink1));
        arrayList.add(new Drink(0,"Sôcôla Lúa Mạch nóng",20000,R.drawable.drink1));
        arrayList.add(new Drink(0,"Sôcôla Lúa Mạch nóng",20000,R.drawable.drink1));
        arrayList.add(new Drink(0,"Sôcôla Lúa Mạch nóng",20000,R.drawable.drink1));
        arrayList.add(new Drink(0,"Sôcôla Lúa Mạch nóng",20000,R.drawable.drink1));
    }

    public void loadListDrink(){
        RecyclerListDrink myAdapter = new RecyclerListDrink(getActivity(),R.layout.item_drink,arrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(myAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }
}