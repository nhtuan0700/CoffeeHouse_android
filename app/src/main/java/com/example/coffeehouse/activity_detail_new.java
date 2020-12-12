package com.example.coffeehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeehouse.model.News;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class activity_detail_new extends AppCompatActivity {
    DatabaseReference database350;
    int id350 = -1;
    ImageView btnBack350;
    ImageView imageView350;
    TextView tvTitle1, tvTitle2, tvTime350, tvDescription350;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_new);
        database350 = FirebaseDatabase.getInstance().getReference();

        addControls();
        initUI();
    }

    public void addControls() {
        tvTitle1 = findViewById(R.id.tv_title1);
        tvTitle2 = findViewById(R.id.tv_title2);
        tvTime350 = findViewById(R.id.tv_time);
        tvDescription350 = findViewById(R.id.tv_description);
        imageView350 = findViewById(R.id.image);
        btnBack350 = findViewById(R.id.btn_back);

        btnBack350.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initUI() {
        Intent intent  = getIntent();
        id350 = intent.getIntExtra("ID",-1);
        Toast.makeText(this, id350 + "", Toast.LENGTH_SHORT).show();

        Query query = database350.child("News").orderByChild("id").equalTo(id350);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot news : dataSnapshot.getChildren()) {
                        //Toast.makeText(activity_detail_new.this, news.child("description").getValue().toString(), Toast.LENGTH_SHORT).show();
                        News mNews = new News();
                        mNews.setId(Integer.parseInt(news.child("id").getValue().toString()));
                        mNews.setTitle(news.child("title").getValue().toString());
                        mNews.setTime(news.child("time").getValue().toString());
                        mNews.setDescription(news.child("description").getValue().toString());
                        mNews.setImage(news.child("image").getValue().toString());
                        loadData(mNews);
                        break;
                    }

                } else{
                    Toast.makeText(activity_detail_new.this, "1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void loadData(News news) {
        tvTitle1.setText(news.getTitle());
        tvTitle2.setText(news.getTitle());
        tvTime350.setText(news.getTime());
        tvDescription350.setText(news.getDescription());
        Picasso.get().load(news.getImage()).into(imageView350);
    }
}