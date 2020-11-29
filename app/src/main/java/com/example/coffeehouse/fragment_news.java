package com.example.coffeehouse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeehouse.apdapter.RecyclerViewNews;
import com.example.coffeehouse.model.News;

import java.util.ArrayList;

public class fragment_news extends Fragment {
    RecyclerView rcv1,rcv2;
    ArrayList<News> arrNews;
    LinearLayout btnLogin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news,container,false);
        addControls(v);
        getData();
        loadListNews();
        return v;
    }

    public void addControls(View v){
        rcv1 = v.findViewById(R.id.recycler1);
        rcv2 = v.findViewById(R.id.recycler2);
        ImageView imageMusic = v.findViewById(R.id.image_music);

        RotateAnimation rotate = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotate.setStartOffset(0);
        rotate.setDuration(10000);
        rotate.setRepeatCount(Animation.INFINITE);
        imageMusic.startAnimation(rotate);

        btnLogin = v.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),activity_login.class);
                startActivity(intent);
            }
        });
    }

    public void getData(){
        arrNews = new ArrayList<>();
        arrNews.add(new News("Mua 4 Tặng 2 - Ăn bánh uống trà, kể chuyện Thanh xuân",
                "Nhập THANHXUAN, Nhà mời ngay ưu đãi MUA 4 TẶNG 2 để cả team chúng mình thoải mái kể chuyện thanh xuân, cùng bánh ngon nước mát",
                R.drawable.news1));
        arrNews.add(new News("Mua 4 Tặng 2 - Ăn bánh uống trà, kể chuyện Thanh xuân",
                "Nhập THANHXUAN, Nhà mời ngay ưu đãi MUA 4 TẶNG 2 để cả team chúng mình thoải mái kể chuyện thanh xuân, cùng bánh ngon nước mát",
                R.drawable.news1));
        arrNews.add(new News("Mua 4 Tặng 2 - Ăn bánh uống trà, kể chuyện Thanh xuân",
                "Nhập THANHXUAN, Nhà mời ngay ưu đãi MUA 4 TẶNG 2 để cả team chúng mình thoải mái kể chuyện thanh xuân, cùng bánh ngon nước mát",
                R.drawable.news1));
    }

    public void loadListNews() {
        RecyclerViewNews myAdapter1 = new RecyclerViewNews(getActivity(), R.layout.item_news, arrNews);
        rcv1.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        rcv1.setAdapter(myAdapter1);
        rcv1.setNestedScrollingEnabled(false);

        RecyclerViewNews myAdapter2 = new RecyclerViewNews(getActivity(), R.layout.item_news, arrNews);
        rcv2.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        rcv2.setAdapter(myAdapter2);
        rcv2.setNestedScrollingEnabled(false);
    }


}