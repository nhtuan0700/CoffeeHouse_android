package com.example.coffeehouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeehouse.apdapter.RecyclerViewNews;
import com.example.coffeehouse.model.News;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class fragment_news extends Fragment {
    ArrayList<News> arrNewsSpecial350, arrNewsHome350;
    LinearLayout btnLogin350;
    LinearLayout loginLayout350,logoutLayout350,order350;
    TextView tvName350;
    DatabaseReference database350;
    FirebaseUser user350;
    private FirebaseAuth mAuth350;
    RecyclerView rcv1,rcv2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news,container,false);
        database350 = FirebaseDatabase.getInstance().getReference();
        mAuth350 = FirebaseAuth.getInstance();
        addControls(v);
        checkLogin();
        getData();
        return v;
    }

    public void addControls(View v){
        loginLayout350 = v.findViewById(R.id.login_layout);
        logoutLayout350 = v.findViewById(R.id.logout_layout);
        order350 = v.findViewById(R.id.order);
        tvName350 = v.findViewById(R.id.tv_name);
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

        btnLogin350 = v.findViewById(R.id.btn_login);
        btnLogin350.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_signin.class);
                startActivity(intent);
            }
        });

        order350.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("TabListener", R.id.nav_order);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    public void checkLogin() {
        user350 = mAuth350.getCurrentUser();
        if(user350 != null){
            loginLayout350.setVisibility(View.VISIBLE);
            logoutLayout350.setVisibility(View.GONE);
            tvName350.setText(user350.getDisplayName());
        }else{
            loginLayout350.setVisibility(View.GONE);
            logoutLayout350.setVisibility(View.VISIBLE);
        }
    }

    public void getData(){
        arrNewsSpecial350 = new ArrayList<>();

        //Query query = database.child("News").orderByChild("id").startAt(0).endAt(4);
        Query query = database350.child("News").orderByChild("category").equalTo("special");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                News news = snapshot.getValue(News.class);
                arrNewsSpecial350.add(news);
                loadListNews();
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

        arrNewsHome350 = new ArrayList<>();
        query = database350.child("News").orderByChild("category").equalTo("home");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                News news = snapshot.getValue(News.class);
                arrNewsHome350.add(news);
                loadListNews();
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

    public void loadListNews() {
        RecyclerViewNews myAdapter1 = new RecyclerViewNews(getActivity(), R.layout.item_news, arrNewsSpecial350);
        rcv1.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        rcv1.setAdapter(myAdapter1);
        rcv1.setNestedScrollingEnabled(false);

        RecyclerViewNews myAdapter2 = new RecyclerViewNews(getActivity(), R.layout.item_news, arrNewsHome350);
        rcv2.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        rcv2.setAdapter(myAdapter2);
        rcv2.setNestedScrollingEnabled(false);
    }

}