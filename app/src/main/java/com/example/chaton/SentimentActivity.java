package com.example.chaton;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SentimentActivity extends AppCompatActivity {

    ImageView graph, emotion;
    TextView sentiment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentiment);

        graph = findViewById(R.id.graph);
        emotion = findViewById(R.id.emotion);
        sentiment = findViewById(R.id.sentiment);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference("message1");
        DatabaseReference myRef2 = database.getReference("message2");
        DatabaseReference myRef3 = database.getReference("message3");

        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Picasso.get().load(value).into(emotion);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Picasso.get().load(value).into(graph);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                sentiment.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}