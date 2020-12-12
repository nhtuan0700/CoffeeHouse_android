package com.example.coffeehouse;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeehouse.apdapter.RecyclerViewProfile;
import com.example.coffeehouse.model.OptionProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class fragment_profile extends Fragment {
    RecyclerView recyclerView350;
    ArrayList<OptionProfile> arrayList350;
    TextView tvLogout350,tvName350;
    LinearLayout loginLayout350,logoutLayout350;
    FirebaseUser user350;
    private FirebaseAuth mAuth350;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        mAuth350 = FirebaseAuth.getInstance();
        addControls(v);
        checkLogin();
        getData();
        loadListOptions();
        return v;
    }

    public void addControls(View v) {
        loginLayout350 = v.findViewById(R.id.login_layout);
        logoutLayout350 = v.findViewById(R.id.logout_layout);
        tvName350 = v.findViewById(R.id.tv_name);
        recyclerView350 = v.findViewById(R.id.recyclerview);
        tvLogout350 = v.findViewById(R.id.tv_logout);
        tvLogout350.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn có chắc chắn muốn đăng xuất");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        checkLogin();
                    }
                });
                builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        logoutLayout350.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_signin.class);
                startActivity(intent);
            }
        });
    }

    public void checkLogin() {
        user350 = mAuth350.getCurrentUser();
        if(user350 != null){
            loginLayout350.setVisibility(View.VISIBLE);
            logoutLayout350.setVisibility(View.GONE);
            tvName350.setText(user350.getDisplayName());
            tvLogout350.setVisibility(View.VISIBLE);
        }else{
            loginLayout350.setVisibility(View.GONE);
            logoutLayout350.setVisibility(View.VISIBLE);
            tvLogout350.setVisibility(View.GONE);
        }
    }

    public void getData() {
        arrayList350 = new ArrayList<>();
        arrayList350.add(new OptionProfile("The Coffee House Rewards", R.drawable.ic_star_outline));
        arrayList350.add(new OptionProfile("Thông tin tài khoản", R.drawable.ic_user));
        arrayList350.add(new OptionProfile("Nhạc đang phát", R.drawable.ic_playlist));
        arrayList350.add(new OptionProfile("Lịch sử", R.drawable.ic_history));
        arrayList350.add(new OptionProfile("Giúp đỡ", R.drawable.ic_help));
        arrayList350.add(new OptionProfile("Cài đặt", R.drawable.ic_settings));
    }

    public void loadListOptions() {
        RecyclerViewProfile myAdapter1 = new RecyclerViewProfile(getActivity(), R.layout.item_option_profile, arrayList350);
        recyclerView350.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView350.setAdapter(myAdapter1);
        recyclerView350.setNestedScrollingEnabled(false);
    }
}