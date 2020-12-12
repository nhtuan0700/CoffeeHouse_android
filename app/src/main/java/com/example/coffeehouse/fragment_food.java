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

import com.example.coffeehouse.apdapter.RecyclerViewMenu;
import com.example.coffeehouse.model.Drink;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;


public class fragment_food extends Fragment {
    private RecyclerView recyclerView350;
    ArrayList<Drink> arrayList350;
    DatabaseReference database350;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_food,container,false);
        database350 = FirebaseDatabase.getInstance().getReference();
        addControls(v);
        loadData();
        return v;
    }

    public void addControls(View v) {
        recyclerView350 = v.findViewById(R.id.recyclerview);
    }

    public void loadData() {
        arrayList350 = new ArrayList<>();
        Query query = database350.child("Menu").orderByChild("category").equalTo("food");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Drink drink = snapshot.getValue(Drink.class);
                arrayList350.add(drink);
                loadMenu();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void loadMenu(){
        RecyclerViewMenu myAdapter = new RecyclerViewMenu(getActivity(),R.layout.item_drink,arrayList350);
        recyclerView350.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView350.setAdapter(myAdapter);
        recyclerView350.setNestedScrollingEnabled(false);
    }

}